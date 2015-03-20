public class NumberPrinter {

	public static void main(String[] args) {
		int number = 0;
		long interval = 0;

		try {
			number = Integer.parseInt(args[0]);
			interval = Integer.parseInt(args[1]);
			Thread thread = new Thread(new MyThread(number));
			thread.start();
			System.out.println(Thread.currentThread().getName()
					+ " Waiting on thread to finish");
			thread.join(interval);
			if (thread.isAlive()) {
				thread.interrupt();
			}
			System.out.println(Thread.currentThread().getName()
					+ " Thread finished or timeout exeeded.");
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("You have to enter number and time interval (miliseconds) ");
		} catch (InterruptedException e) {

		}

	}

}

class MyThread implements Runnable {

	private int number;

	public MyThread(int number) {
		this.number = number;
	}

	@Override
	public void run() {

		for (int i = 0; i < number; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			if (Thread.interrupted()) {
				return;
			}

		}
	}
}