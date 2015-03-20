import java.util.Scanner;


public class AliExpressApplication {

	public static void main(String[] args) {
		System.out.println("=====>AliExpress<===== \n");
		Warehouse warehouse = new Warehouse();
		WarehousePrinter warehousePrinter = new WarehousePrinter();
		ShoppingBasket shoppingBasket = new ShoppingBasket();

		Scanner userChoice = new Scanner(System.in);
		boolean finished = false;

		while (!finished) {
			showMenu();
			String choice = null;
			choice = userChoice.next();

			switch (choice) {
			case "1":
				warehousePrinter.print();
				System.out.println("");
				break;

			case "2":
				boolean moreProducts = true;
				while (moreProducts) {

					System.out.println("Enter product unique key:");
					String key = userChoice.next();
					int quantity = 0;
					if (warehouse.containsProductWithKey(key)) {
						System.out.println("Enter quantity:");
						quantity = userChoice.nextInt();
						try {
							Product boughtProducts = warehouse.update(key, quantity);
							shoppingBasket.addProduct(boughtProducts);
						} catch (QuantityException qe) {
							qe.printMessage();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else{
						System.out
								.println("Product with key - " + key + " doesn`t exist in the store!");
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
				break;

			case "3":
				shoppingBasket.printBasket();
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
}
