public class StopwatchStarted extends Exception {
	String message;

	public StopwatchStarted(String message) {

		this.message = message;
	}

	public void printMessage() {
		System.out.println(this.message);
	}
}
