import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReading {

	/**
	 * Converts the given file to Scanner
	 * 
	 * @param fName
	 *            The String name of a file
	 * @param fileNum
	 *            The file number, used in case of exceptions or errors to tell user
	 *            which file failed
	 * @return A Scanner of the file with the given file name
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
		if (!words.hasNext())
			throw new IllegalArgumentException("Part" + fileNum + ": File is empty");
		return words;

	}

	/**
	 * Creates a file of the given name
	 * 
	 * @param fName
	 *            The name of the file to be created
	 * @return A PrintWriter of the same name as fName which can be manipulated and
	 *         then saved
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
	 * Checks if the braces in a given file are balanced
	 * 
	 * @param toBeChecked
	 *            The name of the file to be checked
	 * @return A boolean on whether or not the braces are balanced
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
	 * Compares two Scanner files to see if they are equal
	 * 
	 * @param f1
	 *            The first file to be checked
	 * @param f2
	 *            The second file to be checked
	 * @return A boolean of whether or not the files are balanced
	 */
	public static boolean compareScanners(Scanner f1, Scanner f2) {
		String s1 = "";
		String s2 = "";
		while (f1.hasNextLine()) {
			s1 = s1 + f1.nextLine();
		}
		while (f2.hasNextLine()) {
			s2 = s2 + f2.nextLine();
		}
		return s1.equals(s2);
	}

	/**
	 * Gets an ArrayList of Strings from the user to replace specific placeholders
	 * in a given Scanner file
	 * 
	 * @param file
	 *            A Scanner with placeholders (noted with &lt; and &gt;) to be
	 *            replaced
	 * @return An ArrayList of Strings made up of the user's responses
	 */
	public static ArrayList<String> getUserWords(Scanner file) {
		ArrayList<String> words = new ArrayList<String>();
		Scanner kb = new Scanner(System.in);
		while (file.hasNextLine()) {
			String line = file.nextLine();

			while (line.indexOf('<') > -1 && line.indexOf('>') > -1) {

				String type = line.substring(line.indexOf('<') + 1, line.indexOf('>'));

				System.out.println("Insert a " + type);

				String userInput = kb.nextLine();
				words.add(userInput);
				line = line.substring(line.indexOf('>') + 1);

			}
		}
		kb.close();
		return words;

	}

	/**
	 * Rewrites a given Scanner file using an ArrayList of Strings to replace
	 * certain placeholders, and prints the result into the given output file
	 * 
	 * @param story
	 *            The Scanner file to be rewritten, removing placeholders
	 * @param words
	 *            An ArrayList of Strings to take the place of given placeholders in
	 *            the Scanner file
	 * @param output
	 *            The output file for the rewritten file to be printed to
	 */
	public static void writeWords(Scanner story, ArrayList<String> words, PrintWriter output) {
		int i = 0;
		while (story.hasNextLine() && i < words.size()) {
			String line = story.nextLine();

			// Checks for input errors such as wrong placement of "<" or ">"
			if (line.indexOf('<') > line.indexOf('>')) {
				throw new IllegalArgumentException("The \">\" is before the \"<\"");
			}

			// Checks for input errors such as a missing < or >
			if ((line.indexOf('<') > -1 && line.indexOf('<') == -1)
					|| (line.indexOf('>') > -1 && line.indexOf('<') == -1)) {
				throw new IllegalArgumentException("Missing a \"<\" or a \">\"");
			}

			// Steps through the file, removing placeholders and replacing them with the
			// next String in the ArrayList
			while (line.indexOf('<') > -1 && line.indexOf('>') > -1) {

				line = line.substring(0, line.indexOf('<')) + words.get(i) + line.substring(line.indexOf('>') + 1);
				i++;
			}
			output.println(line);
		}
	}

	/**
	 * Checks if the number of placeholders is the same as the number of Strings in
	 * an ArrayList (Used to check if user input is necessary)
	 * 
	 * @param story
	 *            The Scanner file to be checked for number of placeholders
	 * @param words
	 *            The ArrayList of Strings which will replace the placeholders,
	 *            checking if its size is enough to replace all the placeholders
	 * @return Whether there are additional lines that have yet to be replaced and
	 *         contain placeholders
	 */
	public static boolean checkWords(Scanner story, ArrayList<String> words) {
		int i = 0;
		while (story.hasNextLine() && i < words.size()) {
			String line = story.nextLine();

			while (line.indexOf('<') > -1 && line.indexOf('>') > -1) {
				line = line.substring(0, line.indexOf('<')) + words.get(i) + line.substring(line.indexOf('>') + 1);
				i++;
			}
		}

		if (story.hasNextLine()) {
			String line = story.nextLine();
			if (line.indexOf('<') > -1 && line.indexOf('>') > -1)
				return true;
		}
		return false;
	}

	/**
	 * Creates an ArrayList of Strings made up of all the lines from a given file
	 * 
	 * @param file
	 *            The Scanner file to be converted to an ArrayList
	 * @return An ArrayList of Strings made up of all the lines from a given file
	 */
	public static ArrayList<String> getArgsWords(Scanner file) {
		ArrayList<String> words = new ArrayList<String>();
		while (file.hasNextLine()) {
			words.add(file.nextLine());

		}

		return words;
	}
}
