package app;

import java.sql.SQLException;
import java.util.Scanner;

import configuration.AliExpressConfiguration;
import printer.Printer;
import product.Product;
import shoppingBasket.ShoppingBasket;
import warehouse.WarehouseHibernate;
import warehouse.WarehouseInMemory;
import warehouse.WarehouseInterface;
import warehouse.CheckWarehouseJdbc;
import warehouse.WarehouseJdbc;

public class AliExpressApplication {
	public static String whichWarehouse;
	private static WarehouseInterface warehouse;
	private static Scanner userChoice = new Scanner(System.in);
	private static ShoppingBasket shoppingBasket;

	public static void main(String[] args) throws SQLException {
		System.out.println("=====>AliExpress<===== \n");
		shoppingBasket = new ShoppingBasket();
		AliExpressConfiguration.createSessionFactory();
		chooseWarehouse();

		boolean finished = false;

		while (!finished) {
			showMenu();

			String choice = null;
			choice = userChoice.next();

			switch (choice) {
			case "1":
				Printer.print(warehouse);
				System.out.println("");
				break;

			case "2":
				addProductOption();
				break;

			case "3":
				Printer.printBasket(shoppingBasket);
				break;

			case "4":
				userChoice.close();
				System.out.println("Goodbye! Visit AliExpress again.");
				finished = true;
				break;

			default:
				System.out.println(choice + " is not a valid option! ");
				System.out.println("You have to enter number from 1 to 4! \n");
				break;
			}
		}

		AliExpressConfiguration.closeSessionFactory();
	}

	public static void showMenu() {
		System.out.println("Choose some option: ");
		System.out.println("1. List of products");
		System.out.println("2. Add product to shopping basket");
		System.out.println("3. Show selected products and total sum");
		System.out.println("4. Exit");
	}

	public static void chooseWarehouse() throws SQLException {
		boolean done = false;
		while (!done) {
			System.out.println("Choose which warehouse do you want to use? ");
			System.out.println("1. In-memory warehouse");
			System.out.println("2. Warehouse with JDBC");
			System.out.println("3. Warehouse with Hibernate");
			Scanner choice = new Scanner(System.in);
			whichWarehouse = choice.next();
			switch (whichWarehouse) {
			case "1":
				warehouse = new WarehouseInMemory();
				warehouse.storeData();
				done = true;
				break;
			case "2":
				warehouse = new WarehouseJdbc();
				if (CheckWarehouseJdbc.isEmpty((WarehouseJdbc) warehouse)) {
					warehouse.storeData();
				}
				done = true;
				break;
			case "3":
				warehouse = new WarehouseHibernate();
				warehouse.storeData();

				done = true;
				break;
			default:
				System.out.println("Invalid option!");
				break;
			}
		}

	}

	public static void addProductOption() throws SQLException {
		boolean moreProducts = true;

		while (moreProducts) {

			System.out.println("Enter product unique key:");
			String key = userChoice.next();
			int quantity;
			Product product = warehouse.getProductWithKey(key);
		
			if (product != null) {
				System.out.println("Enter quantity:");
				quantity = userChoice.nextInt();

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
						shoppingBasket.addProduct(product2);
						product.setQuantity(product.getQuantity() - quantity);
						warehouse.update(product);
					}
				} catch (QuantityException qe) {
					qe.printMessage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Product with key - " + key	+ " doesn`t exist in the store!");
				continue;
			}
			System.out.println("Continue shopping? Yes/No");
			String more = null;

			while (true) {
				more = userChoice.next();

				try {
					if (more.equalsIgnoreCase("Yes")) {
						moreProducts = true;
						break;
					} else if (more.equalsIgnoreCase("No")) {
						moreProducts = false;
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

}
