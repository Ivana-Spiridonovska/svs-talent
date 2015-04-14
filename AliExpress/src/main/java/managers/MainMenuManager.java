package managers;

import java.sql.SQLException;

import app.Menu;
import app.Reader;
import configuration.AliExpressConfiguration;

public class MainMenuManager {
	private static MainMenuManager instance = new MainMenuManager();
	private Menu menu;

	private MainMenuManager() {

	}

	public static MainMenuManager getInstance() {
		instance.menu = new Menu();

		instance.menu.addHeader("\n=====>AliExpress<===== \n");
		instance.menu.addOption("Manage account");
		instance.menu.addOption("Buy products");
		instance.menu.addOption("Exit");
		instance.menu.addTail("Enter your option:");
		return instance;

	}

	public void run() throws SQLException {
		boolean exitMenu = false;
		AliExpressConfiguration.createSessionFactory();
		menu.display();
		while (!exitMenu) {
			String choice = menu.getUserSelection();

			switch (choice) {
			case "1":
				AccountManager.getInstance().run();
				break;
			case "2":
				ShoppingManager.getInstance().run();
				break;
			case "3":
				Reader.println("Goodbye! Visit AliExpress again.");
				exitMenu = true;
				break;
			}
		}
		AliExpressConfiguration.closeSessionFactory();
	}
}
