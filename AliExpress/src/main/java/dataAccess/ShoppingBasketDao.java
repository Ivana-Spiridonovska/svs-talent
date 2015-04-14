package dataAccess;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.Reader;
import configuration.AliExpressConfiguration;
import pojoObjects.Card;
import pojoObjects.Customer;
import pojoObjects.Product;
import pojoObjects.PurchaseOrder;
import template.HibernateTemplate;
import template.HibernateTemplateInterface;

public class ShoppingBasketDao {
	
	public String getCustomersPassword(Customer customer){
		String password = null;
		Session session = AliExpressConfiguration.createSession();
		String hql = "SELECT password FROM Customer WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", customer.getEmail());
		List list = query.list();
		if (list.isEmpty()){
			password = null;
		}
		else{
			password = (String) list.get(0);
		}
		 
		AliExpressConfiguration.closeSession(session);
		return password;
		
	}
	
	public Customer getCustomerWithEmail(String email){
		Customer customer;
		Session session = AliExpressConfiguration.createSession();
		String hql = "FROM Customer WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		List list = query.list();
		customer = (Customer) list.get(0);
		AliExpressConfiguration.closeSession(session);
		return customer;
		
	}
	
	public boolean emailExist(Customer customer){
		boolean exist = false;
		Session session = AliExpressConfiguration.createSession();
		String hql = "FROM Customer WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", customer.getEmail());
		List list = query.list();
		if (list.isEmpty()){
			exist = false;
		}
		else{
			exist = true;
		}
		 
		AliExpressConfiguration.closeSession(session);
		return exist;
		
	}

	public Set<Card> getCustomerCards(Customer customer){
		Set<Card> cards = new HashSet<Card>();
		Card card;
		Session session = AliExpressConfiguration.createSession();
		String hql = "SELECT cards FROM Customer WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", customer.getEmail());
		List list = query.list();
		for (int i = 0; i < list.size(); i++){
			card = new Card();
			card = (Card) list.get(i);
			cards.add(card);
			
		}
		AliExpressConfiguration.closeSession(session);		
		return cards;
		
	}
	
	public Card getCard(Customer customer){
		Card card;
		Session session = AliExpressConfiguration.createSession();
		String hql = "SELECT cards FROM Customer WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", customer.getEmail());
		List list = query.list();
		card = (Card) list.get(0);
		AliExpressConfiguration.closeSession(session);
		return card;
		
	}
	
	public void saveOrder(final PurchaseOrder order) throws SQLException {

		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.save(order);
				return new Object();
			}
		});

	}
}
