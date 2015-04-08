package configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import product.Product;


public class AliExpressConfiguration {
private static SessionFactory sessionFactory;
	
	public static void createSessionFactory() {
		Configuration configuration = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		    configuration.getProperties()).build();
		sessionFactory = configuration.addAnnotatedClass(Product.class).buildSessionFactory(serviceRegistry);

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
