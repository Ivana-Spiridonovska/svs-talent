package dataAccess.hibernate;

import java.sql.SQLException;

import pojoObjectsForHibernate.Publication;

public interface PublicationDaoInterface {
	
	public void register(Publication publication) throws SQLException;
	public void unregister(Publication publication) throws SQLException;
	public void updateRegistration(Publication publication) throws SQLException;
}
