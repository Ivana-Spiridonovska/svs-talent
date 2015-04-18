package pojoObjects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

	@Id
	@GeneratedValue
	@Column(name = "id") private int id;
	@Column(name = "number", unique = true) private long cardNumber;

	@Column(name = "type") private String type;

	@OneToOne(cascade= {CascadeType.ALL})
	private Customer customer;
	
	@OneToMany(mappedBy = "card", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<PurchaseOrder> orders;

	public Card() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Set<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<PurchaseOrder> orders) {
		this.orders = orders;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
}
