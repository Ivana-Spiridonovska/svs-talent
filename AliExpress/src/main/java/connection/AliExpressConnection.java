package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class AliExpressConnection {
	public static Connection createConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/aliExpress", "postgres",
				"postgres");
		return connection;
	}
	
	public static void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}

}
