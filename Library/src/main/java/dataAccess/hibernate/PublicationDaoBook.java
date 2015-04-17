package dataAccess.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojoObjectsForHibernate.Book;
import pojoObjectsForHibernate.Publication;
import template.HibernateTemplate;
import template.HibernateTemplateInterface;

public class PublicationDaoBook {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Book> findBooks() throws SQLException {

		ArrayList<Book> listBooks = (ArrayList<Book>) HibernateTemplate.performAction( new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				ArrayList<Book> books = new ArrayList<Book>();
				String hql = "FROM Publication WHERE dtype = :dtype";
				Query query = session.createQuery(hql);
				query.setParameter("dtype", "book");
				List listBooks = query.list();
				for (Iterator iterator = listBooks.iterator(); iterator.hasNext();) {
					Publication book = (Publication) iterator.next();
					books.add((Book) book);
				}

				return books;
			}
		});
		return listBooks;
	}

	@SuppressWarnings({ "rawtypes" })
	public boolean checkISBN(final String isbn) throws SQLException {
		
		boolean isbnExist = (boolean) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				boolean equal = false;
				String hql = "SELECT COUNT(*) FROM Book WHERE isbn =:isbn";
				Query query = session.createQuery(hql);
				query.setParameter("isbn", isbn);
				List listResult = query.list();
				Number count = (Number) listResult.get(0);
				if (count.intValue() == 0) {
					equal = false;
				} else
					equal = true;
				return equal;
			}
		});
		return isbnExist;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public Book getBookByIsbn(final String isbn){

		Book bookWithIsbn = (Book) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				Book book = new Book();
				String hql = "FROM Book WHERE isbn = :isbn";
				Query query = session.createQuery(hql);
				query.setParameter("isbn", isbn);
				List list = query.list();
				if (list.isEmpty())
					book = null;
				else
					book = (Book) list.get(0);
				return book;
			}
		});
		return bookWithIsbn;
	}
}
