package product;
public class Product {

	private String uniqueKey;
	private String name;
	private int price;
	private int quantity;

	public Product(String uniqueKey, String name, int price, int quantity) {
		this.uniqueKey = uniqueKey;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public void setUniqueKey(String key) {
		this.uniqueKey = key;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUniqueKey() {
		return this.uniqueKey;
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public int getQuantity() {
		return this.quantity;
	}
}
