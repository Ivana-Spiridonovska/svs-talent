import java.util.Scanner;

public class NumberPrinterUsingScanner {

	static int number;
	static int interval;

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number");
		number = input.nextInt();
		System.out.println("Enter time interval in miliseconds");
		interval = input.nextInt();

		Task task = new Task();
		Thread taskThread = new Thread(task);
		taskThread.start();

		System.out.println(Thread.currentThread().getName() + ": Waiting on thread to finish");
		taskThread.join(interval);
		if (Thread.currentThread().isAlive()) {
			taskThread.interrupt();
		}
		System.out.println(Thread.currentThread().getName() + ": Thread finished or timeout exeeded.");
	}

	public static class Task implements Runnable {

		@Override
		public void run() {

			for (int i = 0; i < number; i++) {
				System.out.println(Thread.currentThread().getName() + " : " + i );;
				if (Thread.interrupted()) {
					return;
				}

			}
		}
	}
}
