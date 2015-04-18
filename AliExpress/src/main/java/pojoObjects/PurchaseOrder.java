package pojoObjects;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchaseOrder")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue
	@Column(name = "id") private int id;
	@Column(name = "date") private Date date;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	private Customer customer;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	private Card card;
	
    @ManyToMany(cascade= {CascadeType.ALL})
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Card getCards() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}	
}
