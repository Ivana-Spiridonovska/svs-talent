package warehouse;

import java.sql.SQLException;

import pojoObjects.Product;

public interface WarehouseInterface {

	public void storeData() throws SQLException;
	public void update(Product product) throws SQLException;
	public Product getProductWithKey(String key) throws SQLException;

}
