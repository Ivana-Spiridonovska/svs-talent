package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import book.Book;
import connection.LibraryConnection;

public class BookPrinter {
	
	public static ArrayList<Book> listRegisterBooks() throws SQLException{
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
