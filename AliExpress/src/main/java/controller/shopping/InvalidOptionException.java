package controller.shopping;
public class InvalidOptionException extends Exception {
	
	private static final long serialVersionUID = 1L;
	String message;

	public InvalidOptionException(String message) {
		this.message = message;
	}

	public void printMessage() {
		System.out.println(this.message);
	}

}
