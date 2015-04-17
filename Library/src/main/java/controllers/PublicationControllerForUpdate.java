package controllers;

import java.sql.SQLException;

import pojoObjectsForHibernate.Book;
import pojoObjectsForHibernate.Magazine;
import dataAccess.hibernate.PublicationDao;
import app.Reader;

public class PublicationControllerForUpdate {
	private Book book = new Book();
	private Magazine magazine = new Magazine();
	private PublicationDao publicationDao = new PublicationDao();

	public void update() throws SQLException {
		Reader.println("Choose what type of publication you want to update.");
		Reader.println("Enter 1 if publication is book or enter 2 if publication is magazine:");
		String choice = Reader.readString();
		switch (choice) {
		case "1":
			updateBook();
			break;
		case "2":
			updateMagazine();
			break;
		default:
			Reader.println("Invalid option!");
			break;
		}
	}

	public void updateBook() throws SQLException {
		boolean done = false;
		while(!done){
			Reader.println("Enter isbn of the book you want to update:");
			String isbn = Reader.readString();
			boolean exist = publicationDao.checkISBN(isbn);
			if (exist) {
				Reader.println("Enter new title of the book:");
				String newBookTitle = Reader.readString();
				book = publicationDao.getBookByIsbn(isbn);
				book.setTitle(newBookTitle);
				publicationDao.updateRegistration(book);
				done = true;	
			} else {
				Reader.println("Book with that isbn doesn`t exist!");
			}	
		}	
	}

	public void updateMagazine() throws SQLException {
		boolean done = false;
		while(!done){
			Reader.println("Enter issn of the magazine you want to update:");
			String issn = Reader.readString();
			boolean exist = publicationDao.checkISSN(issn);
			if (exist) {
				Reader.println("Enter new title of the magazine:");
				String newTitle = Reader.readString();
				magazine = publicationDao.getMagazineByIssn(issn);
				magazine.setTitle(newTitle);
				publicationDao.updateRegistration(magazine);
				done = true;	
			} else {
				Reader.println("Magazine with that issn doesn`t exist!");
			}	
		}
	}
}
