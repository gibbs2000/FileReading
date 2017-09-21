import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Checks if there is enough files as args
		if (args.length < 3) {
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

		// If less than 4 command line args are input, asks for user-submitted words to
		// complete story
		if (args.length < 4) {
			out.println(writeStoryUser(file3));
		}

		// If 4 or more command line args are supplied, uses arg[3] to replace words in
		// story
		if (args.length > 4) {
			Scanner file4 = fileToScanner(args[3], 4);
			writeStoryArg(file3, file4);
			file4.close();
		}

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
			String line = toBeChecked.nextLine();
			if (line.contains("{")) {
				numBraces++;
			}
			if (line.contains("}")) {
				numBraces--;

			}

		}
		return numBraces == 0;

	}

	/**
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static boolean compareScanners(Scanner f1, Scanner f2) {
		while (f1.hasNextLine() && f2.hasNextLine()) {
			String l1 = f1.nextLine();
			String l2 = f2.nextLine();
			for (int i = 0; i < l1.length(); i++) {
				if (!(l1.charAt(i) == l2.charAt(i))) {
					return false;
				}
			}
		}
		return true;
	}

	public static String writeStoryUser(Scanner file) {
		ArrayList<String> words = new ArrayList<String>();
		Scanner kb = new Scanner(System.in);

		while (file.hasNextLine()) {
			String line = file.nextLine();

			if (line.contains("<") && line.contains(">")) {

				String type = line.substring(line.indexOf("<") + 1, line.indexOf(">"));

				System.out.println("Insert a " + type);

				String userInput = kb.nextLine();
				words.add(userInput);

			}
		}
		StringBuffer editedStory = new StringBuffer();
		while (file.hasNextLine()) {
			editedStory.append(file.nextLine());
		}

		for (String s : words) {
			if (editedStory.toString().contains("<") && editedStory.toString().contains(">"))
				editedStory.replace(editedStory.indexOf("<"), editedStory.indexOf(">"), s);
		}
		kb.close();

		return new String(editedStory.toString());

	}

	public static void writeStoryArg(Scanner file3, Scanner file4) {
		// TODO Auto-generated method stub

	}
}
