package warehouse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import product.Product;
import connection.AliExpressConnection;

public class WarehouseJdbc implements WarehouseInterface {
	private Vector<Product> products;

	public WarehouseJdbc() {

	}

	public Vector<Product> getProducts() throws SQLException {
		Product product;
		products = new Vector<Product>();
		Connection connection = AliExpressConnection.createConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from product");
		while (resultSet.next()) {
			String key = resultSet.getString("key");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			int quantity = resultSet.getInt("quantity");

			product = new Product(key, name, price, quantity);
			products.add(product);
		}
		statement.close();
		AliExpressConnection.closeConnection(connection);
		return products;
	}

	@Override
	public boolean containsProductWithKey(String key) throws SQLException {
		boolean productInWarehouse = false;
		Connection connection = AliExpressConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select count(*) from product where key =?");
		preparedStatement.setString(1, key);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			if (resultSet.getInt(1) == 0) {
				productInWarehouse = false;
			} else
				productInWarehouse = true;
		}

		preparedStatement.close();
		AliExpressConnection.closeConnection(connection);
		return productInWarehouse;
	}

	@Override
	public Product update(String productKey, int quantityRequestedByBuyer)
			throws QuantityException, SQLException {

		Product soldProduct = null;

		Connection connection = AliExpressConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from product where key =?");
		preparedStatement.setString(1, productKey);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int quantity = resultSet.getInt("quantity");
			if (quantity - quantityRequestedByBuyer < 0) {
				throw new QuantityException("Not enough products in the store!");
			} else {
				PreparedStatement preparedStatement1 = connection
						.prepareStatement("update product set quantity=? where key=? ");

				preparedStatement1.setInt(1, quantity
						- quantityRequestedByBuyer);
				preparedStatement1.setString(2, resultSet.getString("key"));
				preparedStatement1.executeUpdate();
				preparedStatement1.close();
			}
			soldProduct = new Product(resultSet.getString("key"),
					resultSet.getString("name"), resultSet.getInt("price"),
					quantityRequestedByBuyer);
		}

		preparedStatement.close();
		AliExpressConnection.closeConnection(connection);

		return soldProduct;
	}

	@Override
	public void storeData() throws SQLException {
		products = new Vector<Product>();
		BufferedReader buff = null;

		try {
			FileReader file = new FileReader(
					"./src/main/resources/smartPhones.txt");
			buff = new BufferedReader(file);

			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					String[] splitLine = line.split("\\|");
					Connection connection = AliExpressConnection
							.createConnection();
					PreparedStatement preparedStatement = connection
							.prepareStatement("insert into product(key, name, price, quantity) values (?, ?, ?, ?)");

					preparedStatement.setString(1, splitLine[0]);
					preparedStatement.setString(2, splitLine[1]);
					preparedStatement.setInt(3, Integer.parseInt(splitLine[2]));
					preparedStatement.setInt(4, Integer.parseInt(splitLine[3]));
					preparedStatement.addBatch();
					preparedStatement.executeBatch();
					preparedStatement.close();

					AliExpressConnection.closeConnection(connection);
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
