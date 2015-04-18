package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

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
				session.delete(customer);
				return new Object();
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	public Customer getCustomerWithEmail(final String email){
	
		Customer customerWithEmail = (Customer) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				Customer customer = new Customer();
				String hql = "FROM Customer WHERE email = :email";
				Query query = session.createQuery(hql);
				query.setParameter("email", email);
				List list = query.list();
				if (list.isEmpty())
					customer = null;
				else
				    customer = (Customer) list.get(0);
				return customer;
			}
		});
		return customerWithEmail;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Customer> listCustomers(){
	
		List<Customer> listOfCustomers = (List<Customer>) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				List<Customer> customers = new  ArrayList<Customer>();
				String hql = "FROM Customer";
				Query query = session.createQuery(hql);
				List listProducts = query.list();
				for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
					Customer customer = (Customer) iterator.next();
					customers.add(customer);
				}
				return customers;
			}
		});
		return listOfCustomers;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public boolean cardNumberExist(final long cardNumber){
		boolean numberExist = (boolean) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				boolean exist = false;
				String hql = "SELECT COUNT(*) FROM Card WHERE number =:number";
				Query query = session.createQuery(hql);
				query.setParameter("number", cardNumber);
				List listResult = query.list();
				Number count = (Number) listResult.get(0);
				if (count.intValue() == 0) {
					exist = false;
				} else
					exist = true;
				return exist;
			}
		});
		return numberExist;
	}

	public Customer getCustomerById(final int id) {
           Customer customerWithId = (Customer) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				Customer customer = new Customer();
				String hql = "FROM Customer WHERE id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				@SuppressWarnings("rawtypes")
				List list = query.list();
				if (list.isEmpty())
					customer = null;
				else
					customer = (Customer) list.get(0);
				return customer;
			}
		});
		return customerWithId;
	}
}
