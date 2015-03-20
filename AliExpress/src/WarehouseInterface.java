public interface WarehouseInterface {

	public boolean containsProductWithKey(String key);

	public Product update(String key, int quantity) throws QuantityException;

}
