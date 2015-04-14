package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import dataAccess.CustomerDao;
import pojoObjects.Card;
import pojoObjects.Customer;
import app.CustomerCardRegistration;
import app.EmailValidator;
import app.InvalidEmailException;
import app.InvalidPasswordException;
import app.PasswordValidator;
import app.Reader;

public class CustomerController {
	private Customer customer = new Customer();
	private EmailValidator validator = new EmailValidator();
	private PasswordValidator passwordValidator = new PasswordValidator();
	private CustomerDao customerDao = new CustomerDao();

	public void registerCustomer() throws SQLException {
		Reader.println("Enter name:");
		String name = Reader.readString();
		customer.setName(name);
        enterEmail();
		enterPassword();
		Set<Card> cards = CustomerCardRegistration.cardRegistration(customer);
		customer.setCards(cards);
		customerDao.register(customer);
	}

	public void findCustomers() {
		List<Customer> customers = customerDao.listCustomers();
		System.out.printf("%s %10s %10s %n", "ID", "Name", " Email ");
		for (Customer customer : customers) {
			System.out.println(customer.getId() + " \t " + customer.getName()
					+ " \t " + customer.getEmail());
		}
	}

	public void updateCustomer() throws SQLException {
		Customer c = new Customer();
		Reader.println("Enter email of the customer");
		String email = Reader.readString();
		Reader.println("Enter new password");
		String newPassword = Reader.readString();
		c = customerDao.getCustomerWithEmail(email);
		c.setPassword(newPassword);
		customerDao.update(c);
	}

	public void unregisterCustomer() throws SQLException {
		Reader.println("Enter id of the customer you want to unregister:");
		int id = Integer.parseInt(Reader.readString());
		customer.setId(id);
		customerDao.unregister(customer);
	}
	
	public void enterEmail(){
		boolean done = false;
		while (!done) {
			Reader.println("Enter email:");
			try {
				String email = Reader.readString();
				if (validator.validate(email)) {
					customer.setEmail(email);
					done = true;
				} else {
					done = false;
					throw new InvalidEmailException(
							"That is not a valid email!");
				}
			} catch (InvalidEmailException ex) {
				ex.printMessage();
			}
		}
	}
	
	public void enterPassword(){
		boolean done = false;
		while (!done) {
			Reader.println("Enter password:");
			try {
				String password = Reader.readString();
				if (passwordValidator.validate(password)) {
					customer.setPassword(password);
					done = true;
				} else {
					done = false;
					throw new InvalidPasswordException(
							"Your password must contain at least one digit, one lowercase character"
									+ " and one special character(@#$%)! \nPassword size - from 5 to 10.");
				}
			} catch (InvalidPasswordException ex) {
				ex.printMessage();
			}
		}
		
	}

}
