package warehouse;

import java.sql.SQLException;

import product.Product;

public interface WarehouseInterface {

	public boolean containsProductWithKey(String key) throws SQLException;
	public Product update(String key, int quantity) throws QuantityException, SQLException;
	public void storeData() throws SQLException;

}
