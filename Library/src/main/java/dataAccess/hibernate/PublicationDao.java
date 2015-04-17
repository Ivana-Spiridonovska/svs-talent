package dataAccess.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import template.HibernateTemplate;
import template.HibernateTemplateInterface;
import pojoObjectsForHibernate.Book;
import pojoObjectsForHibernate.Magazine;
import pojoObjectsForHibernate.Publication;

public class PublicationDao implements PublicationDaoInterface {

	private PublicationDaoMagazine publicationDaoMagazine = new PublicationDaoMagazine();
	private PublicationDaoBook publicationDaoBook = new PublicationDaoBook();

	@Override
	public void register(final Publication publication) throws SQLException {
		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.save(publication);
				return new Object();
			}
		});
	}

	@Override
	public void unregister(final Publication publication) throws SQLException {
		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.delete(publication);
				return new Object();
			}
		});
	}

	@Override
	public void updateRegistration(final Publication publication)
			throws SQLException {
		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.update(publication);
				return new Object();
			}
		});
	}

	
	@SuppressWarnings("rawtypes")
	public Publication getPublicationById(final int id) {

		Publication publicationWithId = (Publication) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				Publication publication = new Publication();
				String hql = "FROM Publication WHERE id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				List list = query.list();
				if (list.isEmpty())
					publication = null;
				else
					publication = (Publication) list.get(0);
				return publication;
			}
		});
		return publicationWithId;
	}

	public boolean checkISBN(final String isbn) throws SQLException {
		return publicationDaoBook.checkISBN(isbn);
	}

	public boolean checkISSN(final String issn) throws SQLException {
		return publicationDaoMagazine.checkISSN(issn);
	}

	public Book getBookByIsbn(String isbn) {
		return publicationDaoBook.getBookByIsbn(isbn);
	}

	public Magazine getMagazineByIssn(String issn) {
		return publicationDaoMagazine.getMagazineByIssn(issn);
	}

	public ArrayList<Book> findBooks() throws SQLException {
		return publicationDaoBook.findBooks();
	}

	public ArrayList<Magazine> findMagazines() {
		return publicationDaoMagazine.findMagazines();
	}
}
