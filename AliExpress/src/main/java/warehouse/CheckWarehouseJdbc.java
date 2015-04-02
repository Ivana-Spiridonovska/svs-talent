package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.AliExpressConnection;

public class CheckWarehouseJdbc {
	
	public static boolean isEmpty(WarehouseJdbc warehouseJdbc) throws SQLException {
		boolean empty=false;
		Connection connection = AliExpressConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select count(*) from product");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			if (resultSet.getInt(1) == 0) {
				empty = true;
			}
		}
		preparedStatement.close();
		AliExpressConnection.closeConnection(connection);
		return empty;
	}

}
