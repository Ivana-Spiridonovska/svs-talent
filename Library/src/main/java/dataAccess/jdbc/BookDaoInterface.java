package dataAccess.jdbc;
import java.sql.SQLException;
import java.util.ArrayList;

import bookForJdbc.BookForJdbc;

public interface BookDaoInterface {

	public void registerBook(BookForJdbc book) throws SQLException;
	public void unregisterBook(BookForJdbc book) throws SQLException;
	public void updateBookRegistration(BookForJdbc book) throws SQLException;
	public boolean checkISBN(String isbn) throws SQLException;
	public ArrayList<BookForJdbc> listRegisterBooks() throws SQLException;
}
