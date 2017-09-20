import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReading {

	public static void main(String[] args) {
		// Checks if there is enough files as args
		if (args.length < 4) {
			System.out.println("You did not input enough files");
			System.exit(1);
		}
		
		//Creates the 3 files to be checked
		Scanner file1 = fileToScanner(args[0], 1);
		Scanner file2 = fileToScanner(args[1], 2);
		Scanner file3 = fileToScanner(args[2], 3);

		PrintWriter out = outputFile("output.txt");

		if (checkBraces(file1))
			out.println("Braces Balanced");
		else
			out.println("Braces Not Balanced");
		
		
		
		
		
		
		
		//Closes all files
		file1.close();
		file2.close();
		file3.close();
		out.close();
	}

	public static Scanner fileToScanner(String fName, int fileNum) {

		File fileName = new File(fName);
		Scanner words = null;

		try {
			words = new Scanner(fileName);
		} catch (FileNotFoundException ex) {
			System.out.print("Part " + fileNum + ": Unable to Open File");
			return null;

		}
		return words;

	}

	public static PrintWriter outputFile(String fName) {
		File fileName = new File(fName);

		PrintWriter output = null;

		try {
			output = new PrintWriter(fileName);
		} catch (FileNotFoundException ex) {
			System.out.print("Cannot open " + fName);
			return null;

		}

		return output;
	}

	public static boolean checkBraces(Scanner toBeChecked) {
		int numBraces = 0;
		while (numBraces >= 0) {
			if(toBeChecked.hasNextLine(){
				if(toBeChecked.next())
				numBraces++;
			}
		}
		return false;
	}

}
