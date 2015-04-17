package managers;
import java.sql.SQLException;

import app.Menu;
import configuration.LibraryConfiguration;

public class HibernateMenuManager {
	
	private static HibernateMenuManager instance = new HibernateMenuManager();
	private Menu menu;
	
	private HibernateMenuManager(){
		
	}
	
	public static HibernateMenuManager getInstance(){
		instance.menu = new Menu();
		
		instance.menu.addHeader("\nHibernate main menu:");
		instance.menu.addOption("Work with publications");
		instance.menu.addOption("Work with members");
		instance.menu.addOption("Lending publication");
		instance.menu.addOption("Back");
		instance.menu.addTail("Enter your option:");
		return instance;
	}
	
	public void run() throws SQLException{
		boolean finished = false;
		LibraryConfiguration.createSessionFactory();
		while(!finished){
			menu.display();
			String choice = menu.getUserSelection();
			switch(choice){
			case "1":
				PublicationWorkMenuManager.getInstance().run();
				break;
			case "2":
				MemberMenuManager.getInstance().run();
				break;
			case "3":
				LendingPublicationManager.getInstance().run();
				break;
			case "4":
				MainMenuManager.getInstance().run();
				finished = true;
				break;
			}
		}
		LibraryConfiguration.closeSessionFactory();
	}
}
