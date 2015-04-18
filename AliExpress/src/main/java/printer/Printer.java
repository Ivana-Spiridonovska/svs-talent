package printer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.CustomerDao;
import app.Reader;
import pojoObjects.Customer;
import pojoObjects.Product;
import shoppingBasket.ShoppingBasket;
import dataAccess.WarehouseHibernateDao;
import warehouse_not_used.WarehouseInterface;

public class Printer {

	public static void print(WarehouseInterface warehouse) throws SQLException {
		ArrayList<Product> products = null;
		/*switch (AliExpressApplication.whichWarehouse) {
		case "1":
			products = ((WarehouseInMemory) warehouse).getProducts();
			break;
		case "2":
			products = ((WarehouseJdbc) warehouse).getProducts();
			break;
		case "3":
			products = ((WarehouseHibernate) warehouse).getProducts();
			break;
		}*/
		products = ((WarehouseHibernateDao) warehouse).getProducts();
		System.out.printf("%s %10s %15s %n", "Key", " Name ", " Price");
		for (Product product : products) {
			Reader.println(product.getUniqueKey() + " \t "
					+ product.getName() + " \t " + product.getPrice());
		}
	}
	
	public static void printBasket(ShoppingBasket basket) {
		ArrayList<Product> shoppingBasket = basket.getProductBasket();
		if (shoppingBasket.isEmpty()) {
			Reader.println("Your basket is empty! \n");
		} else {
			System.out
					.printf("%s %15s %15s %n", "Name ", " Price", " Quantity");

			for (Product product : shoppingBasket) {
				Reader.println(product.getName() + " \t "
						+ product.getPrice() + " \t " + " \t "
						+ product.getQuantity());
			}
			Reader.println("\n" + "Total price is: " + basket.totalSum());
		}
	}
	
	public static void printCustomers(CustomerDao customerDao){
		List<Customer> customers = customerDao.listCustomers();
		System.out.printf("%s %10s %10s %n", "ID", "Name", " Email ");
		for (Customer customer : customers) {
			Reader.println(customer.getId() + " \t " + customer.getName()
					+ " \t " + customer.getEmail());
		}
	}
}
