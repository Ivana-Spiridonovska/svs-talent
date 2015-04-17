package app;
import java.sql.SQLException;

import controllers.InvalidEmailException;
import managers.MainMenuManager;

public class LibraryApp {

	public static void main(String[] args) throws SQLException, InvalidEmailException {
		MainMenuManager mainMenuManager = MainMenuManager.getInstance(); 
        mainMenuManager.run(); 

	}
}
