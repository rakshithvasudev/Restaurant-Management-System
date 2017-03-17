package com.restaurant.rakshith;// Restaurant Homework
// Instructor-provided code; do not modify.

import java.io.*;

/**
 * This class contains useful static methods for reading user input.
 * It makes sure that the input is "valid" for some definition of the term.
 */
public class ValidInputReader {
	// global shared console scanner
	private static final Scanner CONSOLE = new Scanner(System.in);

	/**
	 * Prompts the user to input a real number, reads and returns it,
	 * re-prompting as necessary until the number is in the given range.
	 * @pre prompt != null, min <= max
	 * 
	 * @param prompt message to display while reading input
	 * @param min minimum allowed value, inclusive
	 * @param max maximum allowed value, inclusive
	 * @return the real number that was read; guaranteed to be between min and max inclusive
	 * @throws FileNotFoundException if prompt is null or min > max
	 */
	public static double getValidDouble(String prompt, double min, double max) {
		if (prompt == null || min > max) {
			throw new IllegalArgumentException();
		}
		while (true) {
			System.out.print(prompt + " ");
			if (CONSOLE.hasNextDouble()) {
				double input = CONSOLE.nextDouble();
				CONSOLE.nextLine();
				if (min <= input && input <= max) {
					// a good value; return it
					return input;
				} else {
					// out of range
					System.out.println("Invalid number.  Must be between " + min + " and " + max + ".");
				}
			} else {
				// not a double
				CONSOLE.nextLine();  // throw away the token
				System.out.println("Invalid input.  Must be a real number between " + min + " and " + max + ".");
			}
		}
	}
	
	/**
	 * Prompts the user to input an integer, reads and returns it,
	 * re-prompting as necessary until the number is in the given range.
	 * @pre prompt != null, min <= max
	 * 
	 * @param prompt message to display while reading input
	 * @param min minimum allowed value, inclusive
	 * @param max maximum allowed value, inclusive
	 * @return the integer that was read; guaranteed to be between min and max inclusive
	 * @throws FileNotFoundException if prompt is null or min > max
	 */
	public static int getValidInt(String prompt, int min, int max) {
		if (prompt == null || min > max) {
			throw new IllegalArgumentException();
		}
		while (true) {
			System.out.print(prompt + " ");
			if (CONSOLE.hasNextInt()) {
				int input = CONSOLE.nextInt();
				CONSOLE.nextLine();
				if (min <= input && input <= max) {
					// a good value; return it
					return input;
				} else {
					// out of range
					System.out.println("Invalid number.  Must be between " + min + " and " + max + ".");
				}
			} else {
				CONSOLE.nextLine();  // throw away the non-int token
				System.out.println("Invalid input.  Must be an integer between " + min + " and " + max + ".");
			}
		}
	}
	
	/**
	 * Prompts the user to input a line of input as a string, reads and returns it,
	 * re-prompting as necessary until the line matches the given regular expression.
	 * @pre prompt != null, regex != null
	 * 
	 * @param prompt message to display while reading input
	 * @param regex regular expression that the line must match before returning
	 * @return the integer that was read; guaranteed to match the given regex
	 * @throws FileNotFoundException if prompt or regex is null
	 */
	public static String getValidString(String prompt, String regex) {
		if (prompt == null || regex == null) {
			throw new IllegalArgumentException();
		}
		while (true) {
			System.out.print(prompt + " ");
			String input = CONSOLE.nextLine();
			if (input.matches(regex)) {
				return input;
			} else {
				System.out.println("Invalid input.  Please try again.");
			}
		}
	}
	
	/**
	 * Prompts the user to input "y" or "n" case-insensitively as a string,
	 * re-prompting as necessary until the user has typed "y" or "n".
	 * Returns a boolean value of true if the user typed "y" and false if "n".
	 * @pre prompt != null
	 * 
	 * @param prompt message to display while reading input
	 * @return true if the user typed "y" or "Y"; false if the user typed "n" or "N"
	 * @throws FileNotFoundException if prompt is null
	 */
	public static boolean getYesNo(String prompt) {
		if (prompt == null) {
			throw new IllegalArgumentException();
		}
		String input = getValidString(prompt, "^[yYnN]$");
		return input.equalsIgnoreCase("y");
	}
	
	/**
	 * Prompts the user to input a file name as a line of input as a string,
	 * reads and returns the corresponding File,
	 * re-prompting as necessary until the file exists and can be read.
	 * Reads the file out of a JAR archive as well, if possible.
	 * @pre prompt != null
	 * 
	 * @param prompt message to display while reading input
	 * @param defaultFileName default file name that will be used if the user simply
	 *                        presses Enter  (ignored if null)
	 * @return the file name typed; guaranteed to have existed and be readable at time of call
	 * @throws FileNotFoundException if prompt is null
	 */
	public static File getValidFile(String prompt, String defaultFileName) {
		if (prompt == null) {
			throw new IllegalArgumentException();
		}
		while (true) {
			System.out.print(prompt + " ");
			String fileName = CONSOLE.nextLine();
			if (fileName.length() == 0) {
				fileName = defaultFileName;
			}
			File file = new File(fileName);
			if (file.canRead() || fileExistsInJar(file)) {
				return file;
			} else {
				System.out.println("Invalid file name; not found or unreadable.  Please try again.");
			}
		}
	}
	
	/**
	 * Reads the entire contents of the given file and return them as a String.
	 * Reads the file out of a JAR archive as well, if possible.
	 * @pre file != null
	 * 
	 * @param file the file whose data should be read
	 * @return the entire contents of the file, as a long String
	 * @throws FileNotFoundException if the file passed is null or does not exist
	 */
	public static String readEntireFile(File file) throws FileNotFoundException {
		if (file == null) {
			throw new IllegalArgumentException();
		}
		// try loading from an internal JAR resource (for sample solution)
		InputStream stream = ValidInputReader.class.getResourceAsStream("/" + file.getName());
		if (stream == null) {
			// fallback to loading normally, from a file (for students)
			stream = new FileInputStream(file);
		}
		
		// read the contents of the file into the string builder
		StringBuilder sb = new StringBuilder((int) (file.length() + 10));
		Scanner input = new Scanner(stream);
		while (input.hasNextLine()) {
			sb.append(input.nextLine());
			sb.append('\n');
		}
		return sb.toString();
	}
	
	// helper returns true if we are running inside of a JAR archive that contains
	// the given file inside it
	// pre: file != null
	private static boolean fileExistsInJar(File file) {
		InputStream stream = ValidInputReader.class.getResourceAsStream("/" + file.getName());
		return stream != null;
	}
}
