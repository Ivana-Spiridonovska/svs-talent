package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import dataAccess.jdbc.BookDao;
import app.Reader;
import bookForJdbc.BookForJdbc;

public class JdbcController {

	BookForJdbc book = new BookForJdbc();
	BookDao library = new BookDao();

	public void registerBook() throws SQLException {
		boolean done = false;
		while (!done) {
			Reader.println("Enter book isbn:");
			String isbn = Reader.readString();
			boolean isbnExist = library.checkISBN(isbn);
			if (!isbnExist) {
				Reader.println("Enter book title:");
				String title = Reader.readString();
				book.setBookISBN(isbn);
				book.setBookTitle(title);
				library.registerBook(book);
				Reader.println("You have successfully register book!");
				done = true;
			} else {
				Reader.println("Book with that isbn already exists in library!");
				done = false;
			}
		}
	}

	public void listBooks() throws SQLException {
		ArrayList<BookForJdbc> bookList = library.listRegisterBooks();
		Reader.println("ID " + " \t " + "ISBN " + " \t    " + "Title");
		for (BookForJdbc someBook : bookList)
			Reader.println(someBook.getBookID() + " \t "
					+ someBook.getBookISBN() + " \t    "
					+ someBook.getBookTitle());
	}

	public void updateBook() throws SQLException {
		boolean done = false;
		while (!done) {
			Reader.println("Enter isbn of the book you want to update:");
			String isbn = Reader.readString();
			boolean isbnExist = library.checkISBN(isbn);
			if (!isbnExist) {
				Reader.println("Book with that isbn doesn`t exist in library!");
			} else {
				Reader.println("Enter new title of the book:");
				String title = Reader.readString();
				book.setBookTitle(title);
				book.setBookISBN(isbn);
				library.updateBookRegistration(book);
				Reader.println("You have successfully update book with isbn "
						+ isbn + "!");
				done = true;
			}
		}
	}

	public void unregisterBook() throws SQLException {
		boolean done = false;
		while (!done) {
			Reader.println("Enter isbn of the book you want to unregister:");
			String isbn = Reader.readString();
			boolean isbnExist = library.checkISBN(isbn);
			if (!isbnExist) {
				Reader.println("Book with that isbn doesn`t exist in library!");
			} else {
				book.setBookISBN(isbn);
				library.unregisterBook(book);
				Reader.println("You have successfully unregister book with isbn "
						+ isbn + "!");
				done = true;
			}
		}
	}
}
