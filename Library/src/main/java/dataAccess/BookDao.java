package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.Book;
import connection.LibraryConnection;

public class BookDao implements BookDaoInterface {

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

	@Override
	public boolean checkISBN(String isbn) throws SQLException {
		boolean equal = false;
		Connection connection = LibraryConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select count(*) from book where isbn =?");
		preparedStatement.setString(1, isbn);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			if (resultSet.getInt(1) == 0) {
				equal = false;
			}
			else
				equal = true;
		}

		preparedStatement.close();
		LibraryConnection.closeConnection(connection);
		return equal;
	}

	@Override
	public ArrayList<Book> listRegisterBooks() throws SQLException {
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		Book book;
		Connection connection = LibraryConnection.createConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from book");
		while (resultSet.next()) {
		    int id = resultSet.getInt("id");
		    String isbn = resultSet.getString("isbn");
		    String title = resultSet.getString("title");
	        book = new Book(id,isbn,title);
	        listOfBooks.add(book);
		}
		statement.close();
		LibraryConnection.closeConnection(connection);
        return listOfBooks;
	}

}
