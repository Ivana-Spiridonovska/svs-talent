package pojoObjects;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name = "id") private int id;
	@Column(name = "name") private String name;
	@Column(name = "email", unique = true) private String email;
	@Column(name = "password") private String password;
	
	@OneToMany(mappedBy = "customer", cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<Card> cards;
	
	@OneToMany(mappedBy = "customer", cascade={CascadeType.ALL})
	private Set<PurchaseOrder> orders;
	
	public Customer(){
		
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	
	public Set<Card> getCards() {
		return cards;
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
