package library;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import book.Book;
import connection.LibraryConnection;

public class Library implements LibraryInterface {

	public void registerBook(Book book) throws SQLException {
		Connection connection = LibraryConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("insert into book(isbn, title) values (?, ?)");

		preparedStatement.setString(1, book.getBookISBN());
		preparedStatement.setString(2, book.getBookTitle());
		preparedStatement.executeUpdate();
		preparedStatement.close();

		LibraryConnection.closeConnection(connection);
	}

	public void unregisterBook(Book book) throws SQLException {
		Connection connection = LibraryConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("delete from book where isbn=? ");

		preparedStatement.setString(1, book.getBookISBN());
		preparedStatement.executeUpdate();
		preparedStatement.close();

		LibraryConnection.closeConnection(connection);

	}

	public void updateBookRegistration(Book book) throws SQLException {
		Connection connection = LibraryConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("update book set title=? where isbn=? ");

		preparedStatement.setString(1, book.getBookTitle());
		preparedStatement.setString(2, book.getBookISBN());
		preparedStatement.executeUpdate();
		preparedStatement.close();

		LibraryConnection.closeConnection(connection);

	}

}
