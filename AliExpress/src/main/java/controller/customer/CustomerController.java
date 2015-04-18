package controller.customer;

import java.sql.SQLException;

import dataAccess.CustomerDao;
import pojoObjects.Card;
import pojoObjects.Customer;
import printer.Printer;
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
		Card card = CustomerCardRegistration.cardRegistration(customer);
		customer.setCard(card);
		customerDao.register(customer);
	}

	public void findCustomers() {
		Printer.printCustomers(customerDao);
	}

	public void updateCustomer() throws SQLException {
		Customer customer = new Customer();
		boolean done = false;
		while(!done){
			Reader.println("Enter email of the customer");
			String email = Reader.readString();
			customer = customerDao.getCustomerWithEmail(email);
			if(customer == null){
				Reader.println("Customer with that email doesn`t exist in database!");
				done = false;
			}else{
				Reader.println("Enter new password");
				String newPassword = Reader.readString();
				customer.setPassword(newPassword);
				customerDao.update(customer);
				done = true;
			}
		}
	}

	public void unregisterCustomer() throws SQLException {
		Reader.println("Enter id of the customer you want to unregister:");
		int id = Integer.parseInt(Reader.readString());
		customer = customerDao.getCustomerById(id);
		customerDao.unregister(customer);
	}
	
	public void enterEmail(){
		boolean done = false;
		while (!done) {
			Reader.println("Enter email:");
			try {
				String email = Reader.readString();
				if (validator.validate(email)) {
					if(customerDao.getCustomerWithEmail(email) == null){
						customer.setEmail(email);
						done = true;
					}else{
						Reader.println("That email already exist in database!");
						done = false;
					}
					
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
