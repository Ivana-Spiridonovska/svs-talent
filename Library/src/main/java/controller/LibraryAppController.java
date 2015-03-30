package controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dataAccess.BookDao;
import book.Book;

public class LibraryAppController {

	Scanner scanner = new Scanner(System.in);
	Book book = new Book();
	BookDao library = new BookDao();
	
	public void firstChoice() throws SQLException{
	
		boolean done = false;
		while (!done) {
			System.out.println("Enter book isbn:");
			String isbn = scanner.next();
			boolean isbnExist = library.checkISBN(isbn);
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
	
	public void secondChoice() throws SQLException {
		ArrayList<Book> bookList = library.listRegisterBooks();
		System.out.println("ID " + " \t " + "ISBN " + " \t    "
				+ "Title");
		for (Book someBook : bookList)
			System.out.println(someBook.getBookID() + " \t "
					+ someBook.getBookISBN() + " \t    "
					+ someBook.getBookTitle());
		System.out.println("\n");
		
	}
	
	public void thirdChoice() throws SQLException{
		boolean done = false;
		while (!done) {
			System.out
					.println("Enter isbn of the book you want to update:");
			String isbn = scanner.next();
			boolean isbnExist = library.checkISBN(isbn);
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
			boolean isbnExist = library.checkISBN(isbn);
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
