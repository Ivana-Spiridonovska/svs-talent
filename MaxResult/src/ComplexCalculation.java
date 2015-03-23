import java.util.Random;
import java.util.concurrent.Callable;

public class ComplexCalculation implements Callable<Integer> {
	private Integer orderNumber;

	public ComplexCalculation(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public Integer call() throws Exception {
		System.out
				.println("Complex calculation " + orderNumber + " started...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		System.out
				.println("Complex calculation " + orderNumber + " completed.");
		return new Random().nextInt(10 + 1);
	}
}
