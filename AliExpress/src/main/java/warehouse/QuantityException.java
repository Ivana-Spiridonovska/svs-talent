package warehouse;
public class QuantityException extends Exception {
	String message;

	public QuantityException(String message) {
		this.message = message;
	}

	public void printMessage() {
		System.out.println(this.message);
	}

}
