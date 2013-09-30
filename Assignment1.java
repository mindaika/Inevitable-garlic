import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

// By default, this code will get its input data from the Java standard input,
// java.lang.System.in. To allow input to come from a file instead, which can be
// useful when debugging your code, you can provide a file name as the first
// command line argument. When you do this, the input data will come from the
// named file instead. If the input file is in the project directory, you will
// not need to provide any path information.
//
// In BlueJ, specify the command line argument when you call main().
//
// In Eclipse, specify the command line argument in the project's "Run Configuration."

public class Assignment1 {

    // Class variables for operations
    private static Map<String, List<String>> result = new TreeMap<String, List<String>>();
    private static List<String> wordList = new ArrayList<String>();

    // returns an InputStream that gets data from the named file
    private static InputStream getFileInputStream(String fileName) {
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {        // no file with this name exists
            System.err.println(e.getMessage());
            inputStream = null;
        }
        return inputStream;
    }

    // Read list of word specified as args into unsorted array
    private static void getInput(String args[]) {
        // Create an input stream for reading the data.  The default is
        // System.in (which is the keyboard).  If there is an arg provided
        // on the command line then we'll use the file instead.

        InputStream in = System.in;
        if (args.length >= 1) {
            in = getFileInputStream(args[0]);
        }

        // Now that we know where the data is coming from we'll start processing.
        // Notice that getFileInputStream could have generated an error and left "in"
        // as null.  We should check that here and avoid trying to process the stream
        // data if there was an error.
        if (in != null) {
            // Using a Scanner object to read one word at a time from the input stream.
            Scanner sc = new Scanner(in);
            String word;

            // Continue getting words until we reach the end of input
            while (sc.hasNext()) {
                word = sc.next();
                if (!word.equals("---")) {
                    // do something with each word in the input
                    wordList.add(word);
                }
            }
        }
    }

    private static void processInput() {

        // Iterate through every word in the input list
        for (int i = 0; i < wordList.size() - 1; i++) {

            // Create references to this word and next word:
            String thisWord = wordList.get(i);
            String nextWord = wordList.get(i + 1);

            // If this word is not in the result Map yet,
            // then add it and create a new empty list for it.
            if (!result.containsKey(thisWord)) {
                result.put(thisWord, new ArrayList<String>());
            }

            // Add nextWord to the list of adjacent words to thisWord:
            result.get(thisWord).add(nextWord);
        }
    }

    private static void generateOutput() {

        // Boilerplate
        System.out.printf("CS261 - Assignment 1 - Randall Sewell%n%n");

        for (Entry e : result.entrySet()) {
            System.out.println(e.getKey() + ":");

            // Count the number of unique instances in the list:
            Map<String, Integer> count = new TreeMap<String, Integer>();
            @SuppressWarnings("unchecked") // This is primarily here to alert users that the generic operation generates a warning
            List<String> words = (List) e.getValue();
            for (String s : words) {
                if (!count.containsKey(s)) {
                    count.put(s, 1);
                } else {
                    count.put(s, count.get(s) + 1);
                }
            }

            // Print the occurrences of following symbols:
            for (Entry f : count.entrySet()) {
                System.out.println("   " + f.getKey() + ", " + f.getValue());
            }
        }
        System.out.println();
        System.out.printf("%nbye...%n");
    }

    public static void main(String[] args) {
        Assignment1.getInput(args);
        Assignment1.processInput();
        Assignment1.generateOutput();
    }
}
