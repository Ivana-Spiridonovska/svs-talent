package md.detector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import md.channel.AlarmChannel;
import md.device.ImageCapturingDevice;

public class MotionDetector {

	private ImageCapturingDevice device;
	private ArrayList<AlarmChannel> alarms;

	public MotionDetector(ImageCapturingDevice device,
			ArrayList<AlarmChannel> alarms) {
		this.device = device;
		this.alarms = alarms;
	}

	public void run() {
		boolean done = false;
		byte[] firstImage = device.getImage();
		while (!done) {
			byte[] secondImage = device.getImage();
			if (!Arrays.equals(firstImage, secondImage)) {
				for (AlarmChannel alarm : alarms) {
					alarm.sendAlarm();
				}
				System.out.println("\nDo you want to enter another string? yes/no");
				Scanner input = new Scanner(System.in);
				while (true) {
					String answer = input.next();
					if (answer.equals("yes")) {
						done = false;
						break;
					} else if (answer.equals("no")) {
						done = true;
						input.close();
						break;
					} else {
						System.out.println("You have to enter yes or no!");
					}
				}
			}
			firstImage = secondImage;
		}
	}
}
