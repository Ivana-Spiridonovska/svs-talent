import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class ComplexCalculationWithLatch implements Callable<Integer> {
	private Integer orderNumber;
	private CountDownLatch latch;

	public ComplexCalculationWithLatch(Integer orderNumber, CountDownLatch latch) {
		this.orderNumber = orderNumber;
		this.latch = latch;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("Complex calculation " + orderNumber + " started...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		System.out.println("Complex calculation " + orderNumber + " completed.");
        latch.countDown();
		return new Random().nextInt(10 + 1);
	}
}