package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.AliExpressConfiguration;
import pojoObjects.Card;
import pojoObjects.Customer;
import template.HibernateTemplate;
import template.HibernateTemplateInterface;

public class CustomerDao {
	
	public void register(final Customer customer) throws SQLException {

		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.save(customer);
				return new Object();
			}
		});

	}
	
	public void update(final Customer customer) throws SQLException {

		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.update(customer);
				return new Object();
			}
		});

	}
	
	public void unregister(final Customer customer) throws SQLException {

		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				/*Set<Card> card = customer.getCards();
				session.delete(card);*/
				session.delete(customer);
				return new Object();
			}
		});

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
	
	public List<Customer> listCustomers(){
		List<Customer> customers = new  ArrayList<Customer>();
		Session session = AliExpressConfiguration.createSession();
		String hql = "FROM Customer";
		Query query = session.createQuery(hql);
		List listProducts = query.list();
		for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			customers.add(customer);
		}

		AliExpressConfiguration.closeSession(session);
		return customers;
	}

}
