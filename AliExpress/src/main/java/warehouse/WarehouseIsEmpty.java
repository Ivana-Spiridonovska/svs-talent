package warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.AliExpressConnection;

public class WarehouseIsEmpty {
	public static void check(WarehouseJdbc warehouseJdbc) throws SQLException {
		Connection connection = AliExpressConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select count(*) from product");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			if (resultSet.getInt(1) == 0) {
				warehouseJdbc.storeData();
			}
		}
		preparedStatement.close();
		AliExpressConnection.closeConnection(connection);
	}

}
