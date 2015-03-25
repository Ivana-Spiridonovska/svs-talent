package md.channel;

public class Alarm implements AlarmChannel {

	private int alarmNumber;

	public Alarm(int alarmNumber) {
		setAlarmNumber(alarmNumber);
	}

	public void setAlarmNumber(int alarmNumber) {
		this.alarmNumber = alarmNumber;
	}

	public int getAlarmNumber() {
		return this.alarmNumber;
	}

	@Override
	public void sendAlarm() {
		System.out.println("Alarm " + getAlarmNumber()
				+ " - change between two subsequent images is detected!");

	}

}
