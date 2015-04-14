package printer;
import java.sql.SQLException;
import java.util.ArrayList;

import pojoObjects.Product;
import shoppingBasket.ShoppingBasket;
import warehouse.WarehouseInterface;
import warehouse.WarehouseHibernateDao;

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
			System.out.println(product.getUniqueKey() + " \t "
					+ product.getName() + " \t " + product.getPrice());
		}
	}
	
	public static void printBasket(ShoppingBasket basket) {
		ArrayList<Product> shoppingBasket = basket.getProductBasket();
		if (shoppingBasket.isEmpty()) {
			System.out.println("Your basket is empty! \n");
		} else {
			System.out
					.printf("%s %15s %15s %n", "Name ", " Price", " Quantity");

			for (Product product : shoppingBasket) {
				System.out.println(product.getName() + " \t "
						+ product.getPrice() + " \t " + " \t "
						+ product.getQuantity());
			}
			System.out.println("\n" + "Total price is: " + basket.totalSum() + "\n");
		}

	}
}
