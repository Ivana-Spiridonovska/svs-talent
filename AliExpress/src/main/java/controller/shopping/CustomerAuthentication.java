package controller.shopping;

import app.Reader;
import dataAccess.CustomerDao;
import pojoObjects.Customer;

public class CustomerAuthentication {
	
	public static Customer authenticate(){
		Customer customer = new Customer();
		CustomerDao customerDao = new CustomerDao();
		Reader.println("In order to continue first you have to authenticate!");
		boolean finished = false;
		while(!finished){
			Reader.println("Enter your email:");
			String email= Reader.readString();
			customer= customerDao.getCustomerWithEmail(email);
			if(customer == null){
				Reader.println("Customer with email " + email + " doesn`t exist in database!");
				finished = false;
			}
			else{
				boolean done = false;
				while (!done){
					Reader.println("Enter you password:");
					String password= Reader.readString();
					String passwordFromDatabase = customer.getPassword();
					boolean validPassword = checkPassword(password, passwordFromDatabase);
					if(validPassword){
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
		return customer;
	}

	public static boolean checkPassword(String password1, String password2){
		boolean equal = false;
		if (password1.equalsIgnoreCase(password2))
			equal= true;
		else
			equal = false;
		return equal;
	}
}
