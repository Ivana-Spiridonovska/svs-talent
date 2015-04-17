package controllers;

public class InvalidEmailException extends Exception{
	
	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidEmailException(String message){
		
		this.message = message;
	}
	public void printMessage(){
		System.out.println(this.message);
	}
}

