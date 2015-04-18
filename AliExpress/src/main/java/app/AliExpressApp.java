package app;

import java.sql.SQLException;

import managers.MainMenuManager;

public class AliExpressApp {
	public static void main(String[] args) throws SQLException {
		
		 MainMenuManager mainMenuManager = MainMenuManager.getInstance();
		 mainMenuManager.run(); 
	}
}
