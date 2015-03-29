package library;
import java.sql.SQLException;

import book.Book;


public interface LibraryInterface {

	public void registerBook(Book book) throws SQLException;
	public void unregisterBook(Book book) throws SQLException;
	public void updateBookRegistration(Book book) throws SQLException;
}
