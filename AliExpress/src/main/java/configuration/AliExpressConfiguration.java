package configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import pojoObjects.*;


public class AliExpressConfiguration {
private static SessionFactory sessionFactory = null;
	
	public static void createSessionFactory() {
		if (sessionFactory == null){
			Configuration configuration = new Configuration();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
			    configuration.getProperties()).build();
			sessionFactory = configuration.addAnnotatedClass(Product.class)
					.addAnnotatedClass(PurchaseOrder.class)
					.addAnnotatedClass(Card.class)
					.addAnnotatedClass(Customer.class)
					.buildSessionFactory(serviceRegistry);
		}
	}
	
	public static void closeSessionFactory(){
		sessionFactory.close();
	}
	
	public static Session createSession(){
		Session session = sessionFactory.openSession();
		return session;
	}
	
	public static void closeSession(Session session){
		session.close();
	}
}
