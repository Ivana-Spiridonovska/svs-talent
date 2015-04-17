package managers;

import java.sql.SQLException;

import app.Menu;
import controllers.PublicationController;
import controllers.PublicationControllerForListing;
import controllers.PublicationControllerForUpdate;

public class PublicationWorkMenuManager {
	private static PublicationWorkMenuManager instance = new PublicationWorkMenuManager();
	private Menu menu;
	private PublicationController controller = new PublicationController();
	private PublicationControllerForUpdate updateController = new PublicationControllerForUpdate();
	private PublicationControllerForListing listController = new PublicationControllerForListing();

	private PublicationWorkMenuManager() {

	}

	public static PublicationWorkMenuManager getInstance() {
		instance.menu = new Menu();

		instance.menu.addHeader("Publication menu:");
		instance.menu.addOption("Register publications");
		instance.menu.addOption("List register publications");
		instance.menu.addOption("Update publication registation");
		instance.menu.addOption("Unregister publications");
		instance.menu.addOption("Back");
		instance.menu.addTail("Enter your option:");
		return instance;
	}

	public void run() throws SQLException {
		boolean exitMenu = false;
		
		while (!exitMenu) {
			menu.display();
			String choice = menu.getUserSelection();
			switch (choice) {
			case "1":
				controller.register();
				break;
			case "2":
				listController.list();
				break;
			case "3":
				updateController.update();
				break;
			case "4":
				controller.unregister();
				break;
			case "5":
				exitMenu = true;
				break;
			}
		}
	}
}
