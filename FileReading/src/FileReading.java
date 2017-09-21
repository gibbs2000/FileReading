import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReading {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Checks if there is enough files as args
		if (args.length < 4) {
			System.out.println("You did not input enough files");
			System.exit(1);
		}

		// Creates the 3 files to be checked
		Scanner file1 = fileToScanner(args[0], 1);
		Scanner file2 = fileToScanner(args[1], 2);
		Scanner file3 = fileToScanner(args[2], 3);

		PrintWriter out = outputFile("output.txt");

		// Step 1: check if braces are balanced
		if (checkBraces(file1))
			out.println("Braces Balanced");
		else
			out.println("Braces Not Balanced");

		// Step 2: Display a blank line
		out.println();

		// Step 3: Compare Files

		if (compareScanners(file1, file2))
			out.println("Files Identical");
		else
			out.println("Files Not Identical");

		// Step 4: Display a blank line
		out.println();

		// Step 5: Storytime

		// Closes all open files
		file1.close();
		file2.close();
		file3.close();
		out.close();
	}

	/**
	 * @param fName
	 * @param fileNum
	 * @return
	 */
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

	/**
	 * @param fName
	 * @return
	 */
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

	/**
	 * @param toBeChecked
	 * @return
	 */
	public static boolean checkBraces(Scanner toBeChecked) {

		int numBraces = 0;

		// Loops while there are lines left in the Scanner to be checked
		while (numBraces >= 0 && toBeChecked.hasNextLine()) {
			if (toBeChecked.hasNext("\\{")) {
				numBraces++;
			}
			if (toBeChecked.hasNext("\\}")) {
				numBraces--;

			}
			toBeChecked.nextLine();

		}
		if (numBraces != 0)
			return false;
		return true;

	}

	/**
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static boolean compareScanners(Scanner f1, Scanner f2) {
		String s1 = f1.toString();
		String s2 = f2.toString();
		return s1.equals(s2);
	}

}
