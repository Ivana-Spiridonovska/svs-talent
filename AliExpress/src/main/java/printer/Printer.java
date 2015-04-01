package printer;
import java.sql.SQLException;
import java.util.Vector;

import app.AliExpressApplication;
import product.Product;
import shoppingBasket.ShoppingBasket;
import warehouse.WarehouseInMemory;
import warehouse.WarehouseInterface;
import warehouse.WarehouseJdbc;

public class Printer {

	public static void print(WarehouseInterface warehouse) throws SQLException {
		Vector<Product> products = null;
		if (AliExpressApplication.whichWarehouse == 1){
			products = ((WarehouseInMemory) warehouse).getProducts();
		}
		else{
			products = ((WarehouseJdbc) warehouse).getProducts();
		}
		System.out.printf("%s %10s %15s %n", "Key", " Name ", " Price");
		for (Product product : products) {
			System.out.println(product.getUniqueKey() + " \t "
					+ product.getName() + " \t " + product.getPrice());
		}
	}
	
	public static void printBasket(ShoppingBasket basket) {
		Vector<Product> shoppingBasket = basket.getProductBasket();
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
