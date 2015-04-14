package managers;
import java.sql.SQLException;

import app.Menu;
import controller.ShoppingController;

public class ShoppingManager {
	
	private static ShoppingManager instance = new ShoppingManager();
	private Menu menu;
	private ShoppingController controller = new ShoppingController();
	
	private ShoppingManager(){
		
	}
	
	public static ShoppingManager getInstance(){
		instance.menu = new Menu();
		
		instance.menu.addHeader("\nShopping menu:");
		instance.menu.addOption("List products");
		instance.menu.addOption("Add product(s) to shopping basket");
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
				controller.listProducts();
				break;
			case "2":
				controller.addProductToBasket();
				break;
			case "3":
				MainMenuManager.getInstance().run();
				finished = true;
				break;
				
			}
		}
	}

}
