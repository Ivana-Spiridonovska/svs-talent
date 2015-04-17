package dataAccess.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import template.HibernateTemplate;
import template.HibernateTemplateInterface;
import pojoObjectsForHibernate.Loan;

public class LoanDao {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Loan> listLoans() {
		final List<Loan> listLoans = (List) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				List<Loan> loans = new ArrayList<Loan>();
				String hql = "FROM Loan";
				Query query = session.createQuery(hql);
				List listProducts = query.list();
				for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
					Loan loan = (Loan) iterator.next();
					loans.add(loan);
				}
				return loans;
			}
		});
		return listLoans;
	}

	public void createLoan(final Loan loan) {
		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.save(loan);
				return new Object();
			}
		});
	}
	
	public boolean publicationExistInLoan(final int id){
		boolean publicationExist = (boolean) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				boolean exist = false;
				String hql = "SELECT COUNT(*) FROM Loan WHERE publication_id =:publication_id";
				Query query = session.createQuery(hql);
				query.setParameter("publication_id", id);
				@SuppressWarnings("rawtypes")
				List listResult = query.list();
				Number count = (Number) listResult.get(0);
				if (count.intValue() == 0) {
					exist = false;
				} else
					exist = true;
				return exist;
			}
		});
		return  publicationExist;
	}
}
