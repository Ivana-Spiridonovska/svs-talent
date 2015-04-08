package warehouse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import configuration.AliExpressConfiguration;
import product.Product;

public class WarehouseHibernate  implements WarehouseInterface{
	private ArrayList<Product> products;

	public  ArrayList<Product> getProducts() {
		products = new  ArrayList<Product>();
		Session session = AliExpressConfiguration.createSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List listProducts = query.list();
		for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			products.add(product);
		}

		AliExpressConfiguration.closeSession(session);
		return products;
	}
	

	public Product getProductWithKey(String key) throws SQLException {
		Product productWithKey;
		Session session = AliExpressConfiguration.createSession();
		String hql = "FROM Product WHERE key = :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", key);
		List product = query.list();
		if (product.isEmpty()){
			productWithKey = null;
		}
		else{
			productWithKey = (Product) product.get(0);
		}
		 
		AliExpressConfiguration.closeSession(session);
		return productWithKey;
	}

	@Transactional
	public void update(Product boughtProduct) throws SQLException {
		Session session = AliExpressConfiguration.createSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "UPDATE Product SET quantity = :quantity WHERE key = :key";
			Query query = session.createQuery(hql);
			query.setParameter("quantity", boughtProduct.getQuantity());
			query.setParameter("key", boughtProduct.getUniqueKey());
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}

		} finally {
			AliExpressConfiguration.closeSession(session);
		}	

	}
	
	public void storeData() throws SQLException {
		products = new  ArrayList<Product>();
		BufferedReader buff = null;
		Product product = new Product();

		try {
			FileReader file = new FileReader(
					"./src/main/resources/smartPhones.txt");
			buff = new BufferedReader(file);

			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					String[] splitLine = line.split("\\|");

					Session session = AliExpressConfiguration.createSession();
					Transaction tx = null;
					try {
						tx = session.beginTransaction();
						product.setUniqueKey(splitLine[0]);
						product.setName(splitLine[1]);
						product.setPrice(Integer.parseInt(splitLine[2]));
						product.setQuantity(Integer.parseInt(splitLine[3]));
						session.saveOrUpdate(product);
						tx.commit();

					} catch (RuntimeException e) {
						if (tx != null) {
							tx.rollback();
						}

					} finally {
						AliExpressConfiguration.closeSession(session);
					}
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	

	

}
