
public interface StopwatchInterface {
	public void start() throws StopwatchStarted;
	public void stop() throws StopwatchNotStarted;
	public void pause() throws StopwatchNotStarted;
	public void resume();

}
