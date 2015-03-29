package controller;
import java.sql.SQLException;


public interface LibraryAppInterface {

	public boolean checkISBN(String isbn) throws SQLException;
	public void firstChoice() throws SQLException;
	public void thirdChoice() throws SQLException;
	public void fourthChoice() throws SQLException;
}
