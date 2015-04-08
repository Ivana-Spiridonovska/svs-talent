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
import java.util.ArrayList;

import product.Product;
import connection.AliExpressConnection;

public class WarehouseJdbc implements WarehouseInterface {
	private  ArrayList<Product> products;

	public WarehouseJdbc() {

	}

	public  ArrayList<Product> getProducts() throws SQLException {
		Product product;
		products = new  ArrayList<Product>();
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
	public Product getProductWithKey(String key) throws SQLException {
		Product productWithKey = null;

		Connection connection = AliExpressConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from product where key =?");
		preparedStatement.setString(1, key);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			String uniqueKey = resultSet.getString("key");
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			int quantity = resultSet.getInt("quantity");

			productWithKey = new Product(uniqueKey, name, price, quantity);
		}

		return productWithKey;
	}

	@Override
	public void update(Product boughtProduct) throws SQLException {
		Connection connection = AliExpressConnection.createConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("update product set quantity=? where key=? ");

		preparedStatement.setInt(1, boughtProduct.getQuantity());
		preparedStatement.setString(2, boughtProduct.getUniqueKey());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		AliExpressConnection.closeConnection(connection);

	}

	@Override
	public void storeData() throws SQLException {
		products = new  ArrayList<Product>();
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
