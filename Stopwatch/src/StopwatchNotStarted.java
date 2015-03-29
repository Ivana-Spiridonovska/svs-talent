public class StopwatchNotStarted extends Exception {
	String message;

	public StopwatchNotStarted(String message) {

		this.message = message;
	}

	public void printMessage() {
		System.out.println(this.message);
	}

}
