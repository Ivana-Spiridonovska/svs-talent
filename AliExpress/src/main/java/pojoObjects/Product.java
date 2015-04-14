package pojoObjects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "id") private int id;
	@Column(unique = true,name = "key") private String uniqueKey;
	@Column(name = "name") private String name;
	@Column(name = "price") private int price;
	@Column(name = "quantity") private int quantity;
	
    @ManyToMany(mappedBy = "products", cascade = {CascadeType.ALL})
    private Set<PurchaseOrder> orders;

    
	public Product(){
		
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<PurchaseOrder> orders) {
		this.orders = orders;
	}
	
}
