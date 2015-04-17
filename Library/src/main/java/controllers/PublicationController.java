package controllers;

import java.sql.SQLException;

import app.Reader;
import dataAccess.hibernate.PublicationDao;
import pojoObjectsForHibernate.Book;
import pojoObjectsForHibernate.Magazine;
import pojoObjectsForHibernate.Publication;

public class PublicationController {
	private Book book = new Book();
	private Magazine magazine = new Magazine();
	private PublicationDao publicationDao = new PublicationDao();

	public void register() throws SQLException {
		Reader.println("Enter title of the publication you want to register:");
		String title = Reader.readString();
		boolean done = false;
		boolean finished = false;
		while (!done) {
			Reader.println("Enter 1 if publication is book or enter 2 if publication is magazine:");
			String choice = Reader.readString();
			switch (choice) {
			case "1":
				while(!finished){
					Reader.println("Enter isbn of the book you want to register:");
					String isbn = Reader.readString();
					boolean exist = publicationDao.checkISBN(isbn);
					if (exist){
						Reader.println("Book with that isbn already exists!");
					}
					else {
						book.setIsbn(isbn);
						book.setTitle(title);
						publicationDao.register(book);
						Reader.println("You have successfully register book!\n");
						finished = true;
					}
				}
				done = true;
				break;
			case "2":
				finished = false;
				while(!finished){
					Reader.println("Enter issn of the magazine you want to register:");
					String issn = Reader.readString();
					boolean exist = publicationDao.checkISSN(issn);
					if (exist){
						Reader.println("Magazine with that issn already exists!");
					}
					else{
						magazine.setIssn(issn);
						magazine.setTitle(title);
						publicationDao.register(magazine);
						Reader.println("You have successfully register magazine!\n");
						finished = true;
					}
				}
				done = true;
				break;
			default:
				Reader.println("Invalid option!");
				break;
			}
		}	
	}

	public void unregister() throws SQLException {
		Publication publication = new Publication();
		boolean done = false;
		while (!done) {
			Reader.println("Enter id of the publication you want to unregister:");
			try{
				int id = Integer.parseInt(Reader.readString());
				publication = publicationDao.getPublicationById(id);
				if (publication == null)
					Reader.println("Publication with id " + id
							+ " doesn`t exist in database!");
				else {
					publication.setId(id);
					publicationDao.unregister(publication);
					Reader.println("You have successfully unregister publication!");
					done = true;
				}
			}catch (NumberFormatException ex){
				Reader.println("You have to enter number!");
				done = false;
			}
		}
	}
}
