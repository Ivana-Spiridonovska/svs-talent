package controller.customer;

import java.sql.SQLException;

import app.Reader;
import dataAccess.CustomerDao;
import pojoObjects.Card;
import pojoObjects.Customer;

public class CustomerCardRegistration {

	public static Card cardRegistration(Customer customer) throws SQLException {
	    Card card = new Card();
		String cardType = checkCardType();
	    card.setType(cardType);
		long cardNumber = checkCardNumber();
		card.setCardNumber(cardNumber);
	    card.setCustomer(customer);				
		return card;
	}

	public static long checkCardNumber() {
		boolean done = false;
		CustomerDao customerDao = new CustomerDao();
		long cardNumber = 0;
		while (!done) {
			Reader.println("Enter card number:");
			String cardNumberString = Reader.readString();
			if (cardNumberString.length() == 16) {
				cardNumber = Long.parseLong(cardNumberString);
				if(!customerDao.cardNumberExist(cardNumber)){
					done = true;
				}else{
					Reader.println("Card with that card number already exist in database!");
					done = false;
				}
			} else {
				Reader.println("You have to enter number with 16 bits");
				done = false;
			}
		}
		return cardNumber;
	}

	public static String checkCardType() {
		boolean done = false;
		String cardType = null;
		while (!done) {
			Reader.println("Enter type of credit card (Maestro, MasterCard or Visa):");
			cardType = Reader.readString();
			if (cardType.equalsIgnoreCase("Maestro")
					|| cardType.equalsIgnoreCase("MasterCard")
					|| cardType.equalsIgnoreCase("Visa")) {
				done = true;
			} else {
				Reader.println("That is not a valid card type!");
				done = false;
			}
		}
		return cardType;
	}
}