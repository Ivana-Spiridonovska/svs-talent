package dataAccess;

import java.sql.SQLException;

import org.hibernate.Session;

import pojoObjects.PurchaseOrder;
import template.HibernateTemplate;
import template.HibernateTemplateInterface;

public class ShoppingBasketDao {
	
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
