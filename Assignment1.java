import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

public class Assignment1
{
    // returns an InputStream that gets data from the named file
    private static InputStream getFileInputStream(String fileName)
    {
	InputStream inputStream;
		
	try {
	    inputStream = new FileInputStream(new File(fileName));
	}
	catch (FileNotFoundException e) {		// no file with this name exists
	    System.err.println(e.getMessage());
	    inputStream = null;
	}
	return inputStream;
    }
	
    public static void main(String[] args)
    {
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
		
	    System.out.printf("CS261 - Assignment 1 - Your Name%n%n");

	    // Continue getting words until we reach the end of input 

	    while (sc.hasNext()) {  
		word = sc.next();		
		if (!word.equals("---")) {

		    // do something with each word in the input
		    // replace this line with your code (probably more than one line of code)
		    System.out.println(word);
		}
	    }
	    System.out.printf("%nbye...%n");
	}
    }
}
