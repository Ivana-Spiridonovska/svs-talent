package app;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import library.BookPrinter;
import book.Book;
import controller.LibraryAppController;

public class LibraryApp {

	public static void main(String[] args) throws SQLException {
		System.out.println("Welcome to Library!\n");
		Scanner scanner = new Scanner(System.in);
		boolean finished = false;
		LibraryAppController controller = new LibraryAppController();

		while (!finished) {
			optionMenu();
			String input = scanner.next();
			switch (input) {
			case "1":
				controller.firstChoice();
				break;
			case "2":
				ArrayList<Book> bookList = BookPrinter.listRegisterBooks();
				System.out.println("ID " + " \t " + "ISBN " + " \t    "
						+ "Title");
				for (Book someBook : bookList)
					System.out.println(someBook.getBookID() + " \t "
							+ someBook.getBookISBN() + " \t    "
							+ someBook.getBookTitle());
				System.out.println("\n");
				break;
			case "3":
				controller.thirdChoice();
				break;
			case "4":
				controller.fourthChoice();
				break;
			case "5":
				scanner.close();
				System.out
						.println("Thank you for your visit! \nVisit Library again!");
				finished = true;
				break;
			default:
				System.out.println("Invalid option!");
				System.out.println("You have to enter number from 1 to 5! \n");
				break;
			}
		}

	}

	public static void optionMenu() {

		System.out.println("Choose some of the following options:");
		System.out.println("1. Register books");
		System.out.println("2. List register books");
		System.out.println("3. Update book registation");
		System.out.println("4. Unregister books");
		System.out.println("5. Exit");
	}

}
