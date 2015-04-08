package acme.thermoregulator;

import java.util.concurrent.TimeUnit;

import acme.AcmeHeaterDevice;
import acme.AcmeThermometerDevice;


public class AcmeStandardThermoregulator implements AcmeThermoregulator {

	private AcmeThermometerDevice td;
	private AcmeHeaterDevice hd;
	private int temperature;
	private boolean powerEnabled = false;
	int seconds = 3;
	

	public AcmeStandardThermoregulator(AcmeThermometerDevice td,
			AcmeHeaterDevice hd) {
		this.td = td;
		this.hd = hd;

	}

	@Override
	public void setTemperature(int temp) {
		this.temperature = temp;
	}

	@Override
	public void enablePower() {
		powerEnabled = true;
		final Thread thread = new Thread(new Runnable() {
			public void run() {
				while (powerEnabled) {
					int temp = td.getTemperature();
					if (temp >= temperature) {
						System.out
								.println("Wanted temperature: " + temperature);
						System.out.println("Current temperature: " + temp);
						hd.disable();
					} else {
						System.out
								.println("Wanted temperature: " + temperature);
						System.out.println("Current temperature: " + temp);
						hd.enable();
					}
					try {
						TimeUnit.SECONDS.sleep(seconds);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	@Override
	public void disablePower() {
		powerEnabled = false;
		
	}

}
