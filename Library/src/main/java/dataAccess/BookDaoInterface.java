package dataAccess;
import java.sql.SQLException;
import java.util.ArrayList;

import book.Book;


public interface BookDaoInterface {

	public void registerBook(Book book) throws SQLException;
	public void unregisterBook(Book book) throws SQLException;
	public void updateBookRegistration(Book book) throws SQLException;
	public boolean checkISBN(String isbn) throws SQLException;
	public ArrayList<Book> listRegisterBooks() throws SQLException;
}
