package template;

import org.hibernate.Session;

public interface HibernateTemplateInterface {
	Object execute(Session session);
}
