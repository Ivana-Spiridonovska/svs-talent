package managers;

import java.sql.SQLException;

import app.Menu;
import controller.CustomerController;

public class AccountManager {
	private static AccountManager instance = new AccountManager();
	private Menu menu;
	CustomerController controller = new CustomerController();

	private AccountManager() {

	}

	public static AccountManager getInstance() {
		instance.menu = new Menu();

		instance.menu.addHeader("\nCustomer menu:");
		instance.menu.addOption("Register customer");
		instance.menu.addOption("List register customers");
		instance.menu.addOption("Update customer registation");
		//instance.menu.addOption("Unregister customer");
		instance.menu.addOption("Back");
		instance.menu.addTail("Enter your option:");
		return instance;

	}

	public void run() throws SQLException{
		boolean finished = false;
		while (!finished) {
			menu.display();
			String choice = menu.getUserSelection();
			switch (choice) {
			case "1":
				controller.registerCustomer();
				break;
			case "2":
				controller.findCustomers();
				break;
			case "3":
				controller.updateCustomer();
				break;
			/*case "4":
				controller.unregisterCustomer();
				break;*/
			case "4":
				MainMenuManager.getInstance().run();
				finished = true;
				break;
			}
		}
	}

}
