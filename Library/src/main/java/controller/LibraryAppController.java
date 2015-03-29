package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import connection.LibraryConnection;
import book.Book;
import library.Library;

public class LibraryAppController implements LibraryAppInterface{

	Scanner scanner = new Scanner(System.in);
	Book book = new Book();
	Library library = new Library();
	
	public boolean checkISBN(String isbn) throws SQLException {
		boolean equal = false;
		Connection connection = LibraryConnection.createConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select isbn from book");
		while (resultSet.next()) {
			if (isbn.equals(resultSet.getString("isbn"))) {
				equal = true;
				break;
			} else
				equal = false;
		}

		statement.close();
		LibraryConnection.closeConnection(connection);
		return equal;
	}

	public void firstChoice() throws SQLException{
	
		boolean done = false;
		while (!done) {
			System.out.println("Enter book isbn:");
			String isbn = scanner.next();
			boolean isbnExist = checkISBN(isbn);
			if (!isbnExist) {
				System.out.println("Enter book title:");
				String title = scanner.next();

				book.setBookISBN(isbn);
				book.setBookTitle(title);
				library.registerBook(book);
				System.out.println("You have successfully register book!\n");
				done = true;
			} else {
				System.out
						.println("Book with that isbn already exists in library!");
				done = false;
			}
		}
	}
	
	public void thirdChoice() throws SQLException{
		boolean done = false;
		while (!done) {
			System.out
					.println("Enter isbn of the book you want to update:");
			String isbn = scanner.next();
			boolean isbnExist = checkISBN(isbn);
			if (!isbnExist) {
				System.out
						.println("Book with that isbn doesn`t exist in library!");
			} else {
				System.out.println("Enter new title of the book:");
				String title = scanner.next();
				book.setBookTitle(title);
				book.setBookISBN(isbn);
				library.updateBookRegistration(book);
				System.out.println("You have successfully update book with isbn " + isbn + "!\n");
				done = true;
			}
		}
	}
	
	public void fourthChoice() throws SQLException{
		boolean done = false;
		while (!done) {
			System.out
					.println("Enter isbn of the book you want to unregister:");
			String isbn = scanner.next();
			boolean isbnExist = checkISBN(isbn);
			if (!isbnExist) {
				System.out
						.println("Book with that isbn doesn`t exist in library!");
			} else {
				book.setBookISBN(isbn);
				library.unregisterBook(book);
				System.out.println("You have successfully unregister book with isbn " + isbn + "!\n");
				done = true;
			}
		}
	}
}
