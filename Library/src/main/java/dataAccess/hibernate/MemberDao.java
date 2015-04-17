package dataAccess.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;

import template.HibernateTemplateInterface;
import template.HibernateTemplate;
import pojoObjectsForHibernate.Member;
import pojoObjectsForHibernate.Membership;

public class MemberDao {

	public void register(final Membership membership) throws SQLException {

		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.save(membership);
				return new Object();
			}
		});
	}

	@Transactional
	public void unregister(final Membership membership) throws SQLException {

		HibernateTemplate.performAction(new HibernateTemplateInterface() {

			@Override
			public Object execute(Session session) {
				session.delete(membership);
				return new Object();
			}
		});
	}

	public Member getMemberById(final int id) {
		
		Member memberWithId = (Member) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				Member member = new Member();
				String hql = "FROM Member WHERE id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id", id);
				@SuppressWarnings("rawtypes")
				List list = query.list();
				if (list.isEmpty())
					member = null;
				else
					member = (Member) list.get(0);
				return member;
			}
		});
		return memberWithId;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Member> listMembers() {
		
	final List<Member> listMembers = (List) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				List<Member> members = new ArrayList<Member>();
				String hql = "FROM Member";
				Query query = session.createQuery(hql);
				List listProducts = query.list();
				for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
					Member member = (Member) iterator.next();
					members.add(member);
				}
				return members;
			}
		});
		return listMembers;
	}
}
