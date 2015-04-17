package managers;

import java.sql.SQLException;

import app.Menu;
import controllers.LoanController;

public class LendingPublicationManager {

	private static LendingPublicationManager instance = new LendingPublicationManager();
	private Menu menu;
	private LoanController controller = new LoanController();
	
	private LendingPublicationManager(){
		
	}
	
	public static LendingPublicationManager getInstance(){
		instance.menu = new Menu();
		
		instance.menu.addHeader("\nLending publication menu:");
		instance.menu.addOption("Create loan");
		instance.menu.addOption("List loans");
		instance.menu.addOption("Back");
		instance.menu.addTail("Enter your option:");
		return instance;
	}
	
	public void run() throws SQLException{
		boolean finished = false;
		
		while(!finished){
			menu.display();
			String choice = menu.getUserSelection();
			switch(choice){
			case "1":
				controller.create();
				break;
			case "2":
				controller.list();
				break;
			case "3":
				finished = true;
				break;
			}
		}
	}
}
