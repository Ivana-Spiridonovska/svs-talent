package configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import pojoObjectsForHibernate.Book;
import pojoObjectsForHibernate.Loan;
import pojoObjectsForHibernate.Magazine;
import pojoObjectsForHibernate.Member;
import pojoObjectsForHibernate.Membership;
import pojoObjectsForHibernate.Publication;


public class LibraryConfiguration {
private static SessionFactory sessionFactory = null;
	
	public static void createSessionFactory() {
		if (sessionFactory == null){
			Configuration configuration = new Configuration();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
			    configuration.getProperties()).build();
			sessionFactory = configuration.addAnnotatedClass(Member.class)
					.addAnnotatedClass(Publication.class)
					.addAnnotatedClass(Book.class)
					.addAnnotatedClass(Magazine.class)
					.addAnnotatedClass(Loan.class)
					.addAnnotatedClass(Membership.class)
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
