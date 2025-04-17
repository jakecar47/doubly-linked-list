/** 
 * File: DoublyLinkedListDriver.java
 * Name: Jake Caruana
 * Email: jtc94964@uga.edu
 * Class: CSCI2720
 * Date Created: 9/27/2024
 */

package project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DoublyLinkedListDriver {

	public static void main(String[] args) {
		// Detect the list type from file contents
		try {
			// Create input file and scanners for the file and type
			Scanner scanner = new Scanner(System.in); // scanner to find file type
			File file = new File(args[0]);
			Scanner fileScanner = new Scanner(file);

			if (!fileScanner.hasNext()) {
				System.out.println("The file is empty.");
			} else {
				// Take user input on type and process the file based on type
				System.out.print("Enter list type (i - int, d - double, s - string): ");
				char type = scanner.next().charAt(0);
				switch (type) {
				case 'i':
					processIntFile(fileScanner, new DoublyLinkedList<Integer>(), type);
					break;
				case 'd':
					processDoubleFile(fileScanner, new DoublyLinkedList<Double>(), type);
					break;
				case 's':
					processStringFile(fileScanner, new DoublyLinkedList<String>(), type);
					break;
				default:
					System.out.println("Invalid list type.");
					break;
				}
			}
			// Close scanners to save memory
			fileScanner.close();
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}

	// Method to process integer files
	private static void processIntFile(Scanner fileScanner, DoublyLinkedList<Integer> list, char type) {
		// Read through file and insert items into the list
		while (fileScanner.hasNext()) {
			try {
				int value = fileScanner.nextInt();
				list.insertItem(value);
			} catch (Exception e) {
				System.out.println("Error reading value from file: " + e.getMessage());
			}
		}
		// After reading from the file, interact with the list via commands
		interactWithList(list, type);
	}

	// Method to process double files
	private static void processDoubleFile(Scanner fileScanner, DoublyLinkedList<Double> list, char type) {
		// Read through file and insert items into the list
		while (fileScanner.hasNext()) {
			try {
				double value = fileScanner.nextDouble();
				list.insertItem(value);
			} catch (Exception e) {
				System.out.println("Error reading value from file: " + e.getMessage());
			}
		}
		// After reading from the file, interact with the list via commands
		interactWithList(list, type);
	}

	// Method to process String files
	private static void processStringFile(Scanner fileScanner, DoublyLinkedList<String> list, char type) {
		// Read through file and insert items into the list
		while (fileScanner.hasNext()) {
			try {
				String value = fileScanner.next();
				list.insertItem(value);
			} catch (Exception e) {
				System.out.println("Error reading value from file: " + e.getMessage());
			}
		}
		// After reading from the file, interact with the list via commands
		interactWithList(list, type);
	}

	// Generic method to handle user commands after the list is populated
	private static <T extends Comparable<T>> void interactWithList(DoublyLinkedList<T> list, char type) {
		// Create scanner to read command being entered
		Scanner scanner = new Scanner(System.in);
		String command;
		System.out.printf("%nCommands:%n(i) - Insert value%n(d) - Delete value%n(p) - Print list%n(l) - Length%n(t) - Print reverse%n(r) - Reverse list%n(b) - Delete subsection%n(s) - Swap Alternate%n(q) - Quit program%n");
		do {
			System.out.print("\nEnter a command: ");
			command = scanner.next();

			switch (command) {
			case "i": // Insert item
				System.out.print("Enter a value to insert: ");
				try {
					T value = readValue(scanner, type); // Reading based on type
					list.insertItem(value);
					System.out.print("The list is: ");
					list.print();
					System.out.println();
				} catch (ClassCastException e) {
					System.out.println("Invalid input for list type.");
				}
				break;
			case "d": // Delete item
				System.out.print("Enter a value to delete: ");
				try {
					T value = readValue(scanner, type); // Reading based on type
					list.deleteItem(value);
					System.out.print("The list is: ");
					list.print();
					System.out.println();
				} catch (ClassCastException e) {
					System.out.println("Invalid input for list type.");
				}
				break;
			case "p": // Print list
				System.out.print("The list is: ");
				list.print();
				System.out.println();
				break;
			case "t": // Print reverse
				System.out.print("The reverse is: ");
				list.printReverse();
				System.out.println();
				break;
			case "r": // Reverse list without printing
				list.reverseList();
				System.out.println();
				break;
			case "b": // Delete subsection
				System.out.print("Enter the lower bound: ");
				try {
					T lowerBound = readValue(scanner, type); // Reading based on type
					System.out.print("Enter the upper bound: ");
					T upperBound = readValue(scanner, type); // Reading based on type
					list.deleteSubsection(lowerBound, upperBound);
					System.out.print("The list is: ");
					list.print();
					System.out.println();
				} catch (ClassCastException e) {
					System.out.println("Invalid input for list type.");
				}
				break;
			case "s": // Swap alternate nodes
				System.out.print("The original list: ");
				list.print();
				System.out.println();
				list.swapAlternate();
				System.out.print("The modified list: ");
				list.print();
				System.out.println();
				break;
			case "q": // Exit the program
				System.out.println("Exiting the program...");
				break;
			default: // Invalid command error
				System.out.println("Invalid command.");
				break;
			}

		} while (!command.equals("q"));
		scanner.close(); // Close command string to save resources
	}

	// Helper method to read and parse values based on the type
	private static <T> T readValue(Scanner scanner, char type) {
		switch (type) {
		case 'i':
			return (T) Integer.valueOf(scanner.nextInt()); // For int type
		case 'd':
			return (T) Double.valueOf(scanner.nextDouble()); // For double type
		case 's':
			return (T) scanner.next(); // for String type
		default:
			System.out.println("Invalid input for list type.");
			return null;
		}
	}
}
