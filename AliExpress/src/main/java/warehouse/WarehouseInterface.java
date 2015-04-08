package warehouse;

import java.sql.SQLException;

import product.Product;

public interface WarehouseInterface {

	public void storeData() throws SQLException;
	public void update(Product product) throws SQLException;
	public Product getProductWithKey(String key) throws SQLException;

}
