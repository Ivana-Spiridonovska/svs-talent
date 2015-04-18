package controller.shopping;
public class QuantityException extends Exception {
	
	private static final long serialVersionUID = 1L;
	String message;

	public QuantityException(String message) {
		this.message = message;
	}

	public void printMessage() {
		System.out.println(this.message);
	}

}
