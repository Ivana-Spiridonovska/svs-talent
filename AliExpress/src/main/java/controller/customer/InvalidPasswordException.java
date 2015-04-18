package controller.customer;

public class InvalidPasswordException extends Exception{

	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidPasswordException(String message){
		
		this.message = message;
	}
	public void printMessage(){
		System.out.println(this.message);
	}
}
