package managers;

import java.sql.SQLException;

import app.Menu;
import controllers.MemberController;

public class MemberMenuManager {
	private static MemberMenuManager instance = new MemberMenuManager();
	private Menu menu;
	private MemberController controller = new MemberController();

	private MemberMenuManager() {

	}

	public static MemberMenuManager getInstance() {
		instance.menu = new Menu();

		instance.menu.addHeader("\nMember menu:");
		instance.menu.addOption("Register member");
		instance.menu.addOption("Unregister member");
		instance.menu.addOption("List members");
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
				controller.register();
				break;
			case "2":
				controller.unregister();
				break;
			case "3":
				controller.findMembers();
				break;
			case "4":
				finished = true;
				break;
			}
		}
	}
}
