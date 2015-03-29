package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LibraryConnection {
	
	public static Connection createConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/library", "postgres",
				"postgres");
		return connection;
	}
	
	public static void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}
}
