package controller.shopping;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import dataAccess.ShoppingBasketDao;
import app.Reader;
import pojoObjects.Customer;
import pojoObjects.Product;
import pojoObjects.PurchaseOrder;
import printer.Printer;
import shoppingBasket.ShoppingBasket;
import dataAccess.WarehouseHibernateDao;

public class ShoppingController {
	
	private WarehouseHibernateDao warehouseDao = new WarehouseHibernateDao();
	private ShoppingBasketDao basketDao = new ShoppingBasketDao();
	
	public void listProducts() throws SQLException {
		warehouseDao.storeData();
		Printer.print(warehouseDao);
	}
	
	public void addProductToBasket() throws SQLException{
		boolean moreProducts = true;
        ShoppingBasket basket = new ShoppingBasket();
		while (moreProducts) {

			Reader.println("Enter product unique key:");
			String key = Reader.readString();
			int quantity;
			Product product = warehouseDao.getProductWithKey(key);
		    Set<Product> products = new HashSet<Product>();
			if (product != null) {
				Reader.println("Enter quantity:");
				quantity = Integer.parseInt(Reader.readString());

				try {
					if (product.getQuantity() - quantity < 0) {
						throw new QuantityException(
								"Not enough products in the store!");
					} else {
						Product product2 = new Product();
						product2.setUniqueKey(key);
						product2.setName(product.getName());
						product2.setPrice(product.getPrice());
						product2.setQuantity(quantity);
						basket.addProduct(product2);
						products.add(product);
						product.setQuantity(product.getQuantity() - quantity);
						warehouseDao.update(product);
					}
				} catch (QuantityException qe) {
					qe.printMessage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Reader.println("Product with key - " + key	+ " doesn`t exist in the store!");
				continue;
			}
			Reader.println("Continue shopping? Yes/No");
			String more = null;

			while (true) {
				more = Reader.readString();
				try {
					if (more.equalsIgnoreCase("Yes")) {
						moreProducts = true;
						break;
					} else if (more.equalsIgnoreCase("No")) {
						moreProducts = false;
						Customer customer = CustomerAuthentication.authenticate();
						PurchaseOrder order = createOrder(customer, products);
						basketDao.saveOrder(order);
						Printer.printBasket(basket);
						break;
					} else
						throw new InvalidOptionException(
								"Invalid option! Enter yes or no!");
				} catch (InvalidOptionException ioe) {
					ioe.printMessage();
				}
			}
		}	
	}
	
	public static PurchaseOrder createOrder(Customer customer, Set<Product> products){
		PurchaseOrder order = new PurchaseOrder();
		order.setCustomer(customer);
		order.setCard(customer.getCard());
		order.setDate(Calendar.getInstance().getTime());
		order.setProducts(products);
		return order;
	}
}
