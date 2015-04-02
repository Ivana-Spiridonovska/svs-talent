package app;

import java.sql.SQLException;
import java.util.Scanner;

import printer.Printer;
import product.Product;
import shoppingBasket.ShoppingBasket;
import warehouse.QuantityException;
import warehouse.WarehouseInMemory;
import warehouse.WarehouseInterface;
import warehouse.CheckWarehouseJdbc;
import warehouse.WarehouseJdbc;

public class AliExpressApplication {
	public static int whichWarehouse;
	private static WarehouseInterface warehouse;
	private static Scanner userChoice = new Scanner(System.in);
	private static ShoppingBasket shoppingBasket;

	public static void main(String[] args) throws SQLException {
		System.out.println("=====>AliExpress<===== \n");
		shoppingBasket = new ShoppingBasket();
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
				secondOption();
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
			System.out
					.println("For in-memory warehouse enter 1, for database enter 2.");
			Scanner choice = new Scanner(System.in);
			whichWarehouse = choice.nextInt();
			switch (whichWarehouse) {
			case 1:
				warehouse = new WarehouseInMemory();
				warehouse.storeData();
				done = true;
				break;
			case 2:
				warehouse = new WarehouseJdbc();
				if(CheckWarehouseJdbc.isEmpty((WarehouseJdbc) warehouse))
					warehouse.storeData();
				done = true;
				break;
			default:
				System.out.println("Invalid option!");
				break;
			}
		}

	}

	public static void secondOption() throws SQLException {
		boolean moreProducts = true;
		while (moreProducts) {

			System.out.println("Enter product unique key:");
			String key = userChoice.next();
			int quantity = 0;

			if (warehouse.getProductWithKey(key)!=null) {
				System.out.println("Enter quantity:");
				quantity = userChoice.nextInt();
				try {
					Product boughtProduct = warehouse.getBoughtProduct(key, quantity);
					warehouse.update(boughtProduct);
					shoppingBasket.addProduct(boughtProduct);
				} catch (QuantityException qe) {
					qe.printMessage();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Product with key - " + key
						+ " doesn`t exist in the store!");
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
