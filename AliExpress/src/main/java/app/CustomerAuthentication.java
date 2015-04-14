package app;

import java.util.Set;

import dataAccess.ShoppingBasketDao;
import pojoObjects.Card;
import pojoObjects.Customer;

public class CustomerAuthentication {
	static ShoppingBasketDao basketDao = new ShoppingBasketDao();
	
	public static CustomerAndCard authenticate(){
		CustomerAndCard customerAndCard = new CustomerAndCard();
		Customer customer = new Customer();
		Card card = new Card();
		Reader.println("In order to continue first you have to authenticate!");
		boolean finished = false;
		while(!finished){
			Reader.println("Enter your email:");
			String email= Reader.readString();
			customer.setEmail(email);
			if(!basketDao.emailExist(customer)){
				Reader.println("Customer with email " + customer.getEmail() + " doesn`t exist in database!");
				finished = false;
			}
			else{
				boolean done = false;
				while (!done){
					Reader.println("Enter you password:");
					String password= Reader.readString();
					String passwordFromDatabase = basketDao.getCustomersPassword(customer);
					boolean validPassword = checkPassword(password, passwordFromDatabase);
					if(validPassword){
						if(getAndShowCards(customer)){
							card = chooseCard();
							customerAndCard.setCard(card);
							customerAndCard.setCustomer(basketDao.getCustomerWithEmail(email));
							//System.out.println(card.getType() + " " + card.getCardNumber() );
						}
						else{
							card = basketDao.getCard(customer);
							customerAndCard.setCard(card);
							customerAndCard.setCustomer(basketDao.getCustomerWithEmail(email));
							//System.out.println(card.getType() + " " + card.getCardNumber() );
						}
						finished = true;
						done = true;
					}
					else{
						Reader.println("Wrong password!");
						done = false;
						continue;
					}
				}
			}
		}
		
		
		return customerAndCard;
		
	}
	
	public static boolean getAndShowCards(Customer customer){
		boolean more = false;
		Set<Card> cards = basketDao.getCustomerCards(customer);
		showCards(cards);
		if (cards.size()>1)
			more = true;
		else
			more = false;
		return more;
		
	}
	
	public static boolean checkPassword(String password1, String password2){
		boolean equal = false;
		if (password1.equalsIgnoreCase(password2))
			equal= true;
		else
			equal = false;
		return equal;
	}

	public static void showCards(Set<Card> cards){
		System.out.printf("%s %20s %n", "Type", " CardNumber ");
		for (Card card : cards) {
			Reader.println(card.getType() + "  \t "
					+ card.getCardNumber());
		}
		
	}
	
	public static Card chooseCard(){
		Card card = new Card();
		Reader.println("Enter number of the card that you want to use:");
		String cardNumberString = Reader.readString();
		card.setCardNumber(Long.parseLong(cardNumberString));
		return card;
	}
}
