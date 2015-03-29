import java.util.concurrent.TimeUnit;


public class Stopwatch implements StopwatchInterface{
	
	private boolean started = false;
	private boolean paused = false;
	private int counter=1;
	private Thread thread = new Thread() {
		@Override
		public void run() {
			while (started == true) {
				if (paused) {
					try {
						synchronized (this) {
							wait();
						}
					} catch (InterruptedException ex) {
					}
				}
				System.out.println(counter++);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
				}
			}
		}
	};

	@Override
	public void start() throws StopwatchStarted{
		if (started == false) {
			started = true;
			thread.start();
		}
		else{
			throw new StopwatchStarted("Stopwatch is already started. You can pause or stop it.");
		}
	}

	@Override
	public void stop() throws StopwatchNotStarted{
		if (started == false){
			throw new StopwatchNotStarted("First you have to start the stopwatch!");
		}
		started = false;
	}

	@Override
	public void pause() throws StopwatchNotStarted{
		if (started == false){
			throw new StopwatchNotStarted("First you have to start the stopwatch!");
		}
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
		synchronized (thread) {
			thread.notify();
		}
	}

}
