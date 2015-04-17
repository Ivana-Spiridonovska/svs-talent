package dataAccess.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import pojoObjectsForHibernate.Magazine;
import pojoObjectsForHibernate.Publication;
import template.HibernateTemplate;
import template.HibernateTemplateInterface;

public class PublicationDaoMagazine {
	
	@SuppressWarnings({ "rawtypes" })
	public boolean checkISSN(final String issn) throws SQLException {
		boolean issnExist = (boolean) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				boolean equal = false;
				String hql = "SELECT COUNT(*) FROM Magazine WHERE issn =:issn";
				Query query = session.createQuery(hql);
				query.setParameter("issn", issn);
				List listResult = query.list();
				Number count = (Number) listResult.get(0);
				if (count.intValue() == 0) {
					equal = false;
				} else
					equal = true;
				return equal;
			}
		});
		return issnExist;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public Magazine getMagazineByIssn(final String issn){
		Magazine magazineWithIssn = (Magazine) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				Magazine magazine = new Magazine();
				String hql = "FROM Magazine WHERE issn = :issn";
				Query query = session.createQuery(hql);
				query.setParameter("issn", issn);
				List list = query.list();
				if (list.isEmpty())
					magazine = null;
				else
					magazine = (Magazine) list.get(0);
				return magazine;
			}
		});
		return magazineWithIssn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<Magazine> findMagazines() {
		ArrayList<Magazine> listMagazines = (ArrayList<Magazine>) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				ArrayList<Magazine> magazines = new ArrayList<Magazine>();
				String hql = "FROM Publication WHERE dtype = :dtype";
				Query query = session.createQuery(hql);
				query.setParameter("dtype", "magazine");
				List listMagazines = query.list();
				for (Iterator iterator = listMagazines.iterator(); iterator.hasNext();) {
					Publication magazine = (Publication) iterator.next();
					magazines.add((Magazine) magazine);
				}
				return magazines;
			}
		});
		return listMagazines;
	}
}
