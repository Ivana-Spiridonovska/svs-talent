public class InvalidOptionException extends Exception {
	String message;

	public InvalidOptionException(String message) {

		this.message = message;
	}

	public void printMessage() {
		System.out.println(this.message);
	}

}
