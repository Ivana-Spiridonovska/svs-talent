package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import book.Book;
import configuration.LibraryConfiguration;

public class BookDaoHibernate implements BookDaoInterface {

	public void registerBook(Book book) throws SQLException {
		Session session = LibraryConfiguration.createSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			book.setBookISBN(book.getBookISBN());
			book.setBookTitle(book.getBookTitle());
			session.save(book);
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			LibraryConfiguration.closeSession(session);
		}

	}

	@Transactional
	public void unregisterBook(Book book) throws SQLException {
		Session session = LibraryConfiguration.createSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "DELETE FROM Book WHERE isbn = :isbn";
			Query query = session.createQuery(hql);
			query.setParameter("isbn", book.getBookISBN());
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			LibraryConfiguration.closeSession(session);
		}
	}

	@Transactional
	public void updateBookRegistration(Book book) throws SQLException {
		Session session = LibraryConfiguration.createSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "UPDATE Book set title = :title WHERE isbn = :isbn";
			Query query = session.createQuery(hql);
			query.setParameter("title", book.getBookTitle());
			query.setParameter("isbn", book.getBookISBN());
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			LibraryConfiguration.closeSession(session);
		}

	}

	public boolean checkISBN(String isbn) throws SQLException {
		boolean equal = false;
		Session session = LibraryConfiguration.createSession();
		String hql = "SELECT COUNT(*) FROM Book WHERE isbn =:isbn";
		Query query = session.createQuery(hql);
		query.setParameter("isbn", isbn);
		List listResult = query.list();
		Number count = (Number) listResult.get(0);

		if (count.intValue() == 0) {
			equal = false;
		} else
			equal = true;

		LibraryConfiguration.closeSession(session);
		return equal;
	}

	public ArrayList<Book> listRegisterBooks() throws SQLException {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		Session session = LibraryConfiguration.createSession();
		String hql = "FROM Book";
		Query query = session.createQuery(hql);
		List books = query.list();
		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			listOfBooks.add(book);
		}

		LibraryConfiguration.closeSession(session);
        return listOfBooks; 
	}

}
