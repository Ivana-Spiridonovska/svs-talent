package app;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import pojoObjects.Card;
import pojoObjects.Customer;

public class CustomerCardRegistration {

	public static Set<Card> cardRegistration(Customer customer) throws SQLException {
		Set<Card> cards = new HashSet<Card>();
		int numberOfCards = 0;
		boolean done = false;
		while (!done) {
			Reader.println("Enter the number of cards you are going to use:");
			try {
				numberOfCards = Integer.parseInt(Reader.readString());
				if (numberOfCards >= 1 && numberOfCards <= 3) {
					for (int i = 0; i < numberOfCards; i++) {
						Card card = new Card();
						String cardType = checkCardType();
						card.setType(cardType);
						long cardNumber = checkCardNumber();
						card.setCardNumber(cardNumber);
						card.setCustomer(customer);
						cards.add(card);
					}
					done = true;
				} else {
					Reader.println("The number must be between 1 and 3!");
					done = false;
				}
			} catch (NumberFormatException ex) {
				Reader.println("You have to enter number!");
				done = false;
			}
		}
		return cards;
	}

	public static long checkCardNumber() {
		boolean done = false;
		long cardNumber = 0;
		while (!done) {
			Reader.println("Enter card number:");
			String cardNumberString = Reader.readString();
			if (cardNumberString.length() == 3) {
				cardNumber = Long.parseLong(cardNumberString);
				done = true;
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