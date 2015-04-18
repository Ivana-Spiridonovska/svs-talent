package dataAccess;

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
import pojoObjects.Product;
import template.HibernateTemplate;
import template.HibernateTemplateInterface;
import warehouse_not_used.WarehouseInterface;

public class WarehouseHibernateDao  implements WarehouseInterface{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  ArrayList<Product> getProducts() {
	
		ArrayList<Product> productList = (ArrayList<Product>) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				ArrayList<Product> products = new ArrayList<Product>();
				String hql = "FROM Product";
				Query query = session.createQuery(hql);
				List listProducts = query.list();
				for (Iterator iterator = listProducts.iterator(); iterator.hasNext();) {
					Product product = (Product) iterator.next();
					products.add(product);
				}
				return products;
			}
		});
		return productList;
	}
	
	public Product getProductWithKey(final String key) throws SQLException {
		Product productWithKey = (Product) HibernateTemplate.performAction(new HibernateTemplateInterface() {
			
			@Override
			public Object execute(Session session) {
				Product product = new Product();
				String hql = "FROM Product WHERE key = :key";
				Query query = session.createQuery(hql);
				query.setParameter("key", key);
				@SuppressWarnings("rawtypes")
				List list = query.list();
				if (list.isEmpty()){
					product = null;
				}else{
					product = (Product) list.get(0);
				}
				return product;
			}
		});
		return productWithKey;
	}

	@Transactional
	public void update(final Product boughtProduct) throws SQLException {

		HibernateTemplate.performAction(new HibernateTemplateInterface() {
			@Override
			public Object execute(Session session) {
				session.update(boughtProduct);
				return new Object();
			}
		});
	}
	
	public void storeData() throws SQLException {
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
