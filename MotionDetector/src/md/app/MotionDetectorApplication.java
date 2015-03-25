package md.app;

import java.util.ArrayList;

import md.channel.Alarm;
import md.channel.AlarmChannel;
import md.detector.MotionDetector;
import md.device.ImageCapturingDevice;
import md.device.ImageDevice;

public class MotionDetectorApplication {

	public static void main(String[] args) {

		ImageCapturingDevice device = new ImageDevice();
		ArrayList<AlarmChannel> alarms = new ArrayList<AlarmChannel>();
		Alarm a1 = new Alarm(1);
		Alarm a2 = new Alarm(2);
		Alarm a3 = new Alarm(3);
		alarms.add(a1);
		alarms.add(a2);
		alarms.add(a3);
		MotionDetector detector = new MotionDetector(device, alarms);
		detector.run();
	}
}
