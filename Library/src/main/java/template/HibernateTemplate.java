package template;

import org.hibernate.Session;

import configuration.LibraryConfiguration;

public class HibernateTemplate {
	public static Object performAction(HibernateTemplateInterface templateInterface) {
		Session session = LibraryConfiguration.createSession();
		session.beginTransaction();
		try {
			Object object = templateInterface.execute(session);
			session.getTransaction().commit();
			return object;
		} catch (Exception exception) {
			session.getTransaction().rollback();
			throw new RuntimeException(exception);
		} finally {
			LibraryConfiguration.closeSession(session);
		}
	}

}
