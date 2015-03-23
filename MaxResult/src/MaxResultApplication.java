import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MaxResultApplication {
	private static final int NUMBER_OF_OPERATIONS = 10;

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		ExecutorService executorService = Executors
				.newFixedThreadPool(NUMBER_OF_OPERATIONS);
		ArrayList<Future<Integer>> futureObjectList = new ArrayList<Future<Integer>>();
		ArrayList<Integer> resultList = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			futureObjectList.add(executorService.submit(new ComplexCalculation(i)));
		}

		for (Future<Integer> futureResult : futureObjectList) {
			resultList.add(futureResult.get());
		}

		System.out.println("All complex calculations finished.\n");

		//shows results from all complex calculations
		/*System.out.println("Results from complex calculation:");
		for (int i : resultList)
			System.out.println(i);*/

		int max = Collections.max(resultList);
		System.out.println("Max result is: " + max);
		executorService.shutdown();

	}
}