package managers;

import java.sql.SQLException;

import app.Menu;
import controllers.JdbcController;

public class JdbcMenuManager {
	private static JdbcMenuManager instance = new JdbcMenuManager();
	private Menu menu;
	JdbcController controller = new JdbcController();

	private JdbcMenuManager() {

	}

	public static JdbcMenuManager getInstance() {
		instance.menu = new Menu();

		instance.menu.addHeader("\nJDBC main menu:");
		instance.menu.addOption("Register books");
		instance.menu.addOption("List register books");
		instance.menu.addOption("Update book registation");
		instance.menu.addOption("Unregister books");
		instance.menu.addOption("Back");
		instance.menu.addTail("Enter your option:");
		return instance;

	}

	public void run() throws SQLException {
		boolean finished = false;

		while (!finished) {
			menu.display();
			String choice = menu.getUserSelection();
			switch (choice) {
			case "1":
				controller.registerBook();
				break;
			case "2":
				controller.listBooks();
				break;
			case "3":
				controller.updateBook();
				break;
			case "4":
				controller.unregisterBook();
				break;
			case "5":
				MainMenuManager.getInstance().run();
				finished = true;
				break;
			}
		}
	}
}
