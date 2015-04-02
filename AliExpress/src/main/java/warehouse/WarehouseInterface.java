package warehouse;

import java.sql.SQLException;

import product.Product;

public interface WarehouseInterface {

	public Product getBoughtProduct(String key, int quantity) throws QuantityException, SQLException;
	public void storeData() throws SQLException;
	public void update(Product product) throws SQLException;
	public Product getProductWithKey(String key) throws SQLException;

}
