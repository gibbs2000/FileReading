import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tests the FileReading class according to Homework Assignment 1
 * @author gibbonss
 *
 */
public class FileReadingTester {

	/**
	 * Tests the FileReading class
	 * 
	 * @param args The String file names of the files to be checked. 
	 *            The first file is checked if braces are correctly balanced. The second is checked if matches
	 *            the first file. The third provides a story with placeholders to be removed
	 *            by a given list or the user. The optional fourth file is a list of words to fill
	 *            placeholders (if absent or incomplete, asks the user for required
	 *            input)
	 */

	public static void main(String[] args) {

		// Checks if there is enough files as args
		if (args.length < 3) {
			System.out.println("You did not input enough files");
			System.exit(1);
		}

		// Creates the 3 files to be checked
		Scanner file1 = FileReading.fileToScanner(args[0], 1);
		Scanner file2 = FileReading.fileToScanner(args[1], 2);
		Scanner file3 = FileReading.fileToScanner(args[2], 3);

		PrintWriter out = FileReading.outputFile("output.txt");

		// Step 1: check if braces are balanced
		if (FileReading.checkBraces(file1))
			out.println("Braces Balanced");
		else
			out.println("Braces Not Balanced");

		// Step 2: Display a blank line and resets the file
		out.println();
		file1 = FileReading.fileToScanner(args[0], 1);

		// Step 3: Compare Files

		if (FileReading.compareScanners(file1, file2))
			out.println("Files Identical");
		else
			out.println("Files Not Identical");

		// Step 4: Display a blank line and resets the files
		out.println();
		file1 = FileReading.fileToScanner(args[0], 1);
		file2 = FileReading.fileToScanner(args[1], 2);

		// Step 5: Storytime

		// If less than 4 command line args are input, asks for user-submitted words to
		// complete the story
		if (args.length < 4) {
			ArrayList<String> words = FileReading.getUserWords(file3);
			file3.close();
			file3 = FileReading.fileToScanner(args[2], 3);
			FileReading.writeWords(file3, words, out);
		}

		// If 4 or more command line args are supplied, uses arg[3] to replace words in
		// the story
		//
		if (args.length >= 4) {
			Scanner file4 = FileReading.fileToScanner(args[3], 4);
			ArrayList<String> words = FileReading.getArgsWords(file4);

			// If the given file does not completely fill the placeholders, asks the user
			// for more words
			if (FileReading.checkWords(file3, words)) {
				System.out.println("Not enough words supplied, please enter more");
				ArrayList<String> moreWords = FileReading.getUserWords(file3);
				words.addAll(moreWords);
			}

			file3 = FileReading.fileToScanner(args[2], 3);
			FileReading.writeWords(file3, words, out);
			file4.close();
		}

		// Closes all open files
		file1.close();
		file2.close();
		file3.close();
		out.close();
	}
}
