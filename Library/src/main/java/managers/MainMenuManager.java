package managers;

import java.sql.SQLException;

import app.Menu;
import app.Reader;

public class MainMenuManager {
	private static MainMenuManager instance = new MainMenuManager();
	private Menu menu;

	private MainMenuManager() {

	}

	public static MainMenuManager getInstance() {
		instance.menu = new Menu();

		instance.menu.addHeader("\nWelcome to Library!");
		instance.menu.addOption("Work with JDBC database");
		instance.menu.addOption("Work with Hibernate database");
		instance.menu.addOption("Exit");
		instance.menu.addTail("Enter your option:");
		return instance;
	}

	public void run() throws SQLException {
		boolean exitMenu = false;
		menu.display();
		while (!exitMenu) {
			
			String choice = menu.getUserSelection();
			switch (choice) {
			case "1":
				JdbcMenuManager.getInstance().run();
				break;
			case "2":
				HibernateMenuManager.getInstance().run();
				break;
			case "3":
				Reader.println("Thank you for your visit! \nVisit Library again!");
				exitMenu = true;
				break;
			}
		}
	}
}
