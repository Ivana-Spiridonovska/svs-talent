import java.util.Scanner;

public class StopwatchApp {

	public static void main(String[] args) throws StopwatchNotStarted,
			StopwatchStarted {
		Scanner input = new Scanner(System.in);
		Stopwatch stopwatch = new Stopwatch();
		boolean finished = false;
		boolean stopwatchStarted = false;
		optionMenu();
		while (!finished) {
			String choice = input.next();
			switch (choice) {
			case "1":
				if (stopwatchStarted == true) {
					try {
						stopwatch.start();
					} catch (StopwatchStarted e2) {
						e2.printMessage();
					}
				} else {
					stopwatch.start();
					stopwatchStarted = true;
				}

				break;
			case "2":
				if (stopwatchStarted == false) {
					try {
						stopwatch.stop();
					} catch (StopwatchNotStarted e) {
						e.printMessage();
						finished = false;
					}
				} else {
					stopwatch.stop();
					finished = true;
				}

				break;
			case "3":
				boolean done = false;
				while (!done) {
					if (stopwatchStarted == false) {
						try {
							stopwatch.pause();
						} catch (StopwatchNotStarted e1) {
							e1.printMessage();
							done = true;
						}
					} else {
						stopwatch.pause();
						System.out
								.println("Do you want to resume or to stop the stopwatch?");
						System.out.println("Enter 4 for resume or 5 for stop");
						String choice2 = input.next();
						switch (choice2) {
						case "4":
							stopwatch.resume();
							done = true;
							break;
						case "5":
							stopwatch.stop();
							done = true;
							finished = true;
							break;
						default:
							System.out
									.println("Invalid input you have to enter 4 or 5!");
							break;
						}
					}

				}

				break;
			default:
				System.out
						.println("Invalid input you have to enter number from 1 to 3!");
				break;
			}
		}
		input.close();

	}

	public static void optionMenu() {
		System.out.println("1. Start stopwatch");
		System.out.println("2. Stop stopwatch");
		System.out.println("3. Pause stopwatch");
	}

}
