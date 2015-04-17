package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import app.Reader;
import pojoObjectsForHibernate.Book;
import pojoObjectsForHibernate.Magazine;
import dataAccess.hibernate.PublicationDao;

public class PublicationControllerForListing {
	private PublicationDao publicationDao = new PublicationDao();
	
	public void list() throws SQLException {
		boolean done = false;
		while(!done){
			Reader.println("Choose what type of publication you want to list.");
			Reader.println("Enter 1 if publication is book or enter 2 if publication is magazine:");
			String choice = Reader.readString();
			switch (choice) {
			case "1":
				listBooks();
				done = true;
				break;
			case "2":
				listMagazines();
				done = true;
				break;
			default:
				Reader.println("Invalid option!");
				break;
			}
		}	
	}
	
	public void listBooks() throws SQLException{
		ArrayList<Book> bookList = publicationDao.findBooks();
		Reader.println("ID " + " \t " + "ISBN " + " \t    " + "Title");
		for (Book someBook : bookList)
			Reader.println(someBook.getId() + " \t " + someBook.getIsbn() +" \t    "
					+ someBook.getTitle());
		Reader.println("");
	}
	
	public void listMagazines() throws SQLException{
		ArrayList<Magazine> magazineList = publicationDao.findMagazines();
		Reader.println("ID " + " \t " + "ISSN " + " \t    " + "Title");
		for (Magazine someMagazine : magazineList)
			Reader.println(someMagazine.getId() + " \t " + someMagazine.getIssn() + " \t    "
					+ someMagazine.getTitle());
		Reader.println("");
	}
}
