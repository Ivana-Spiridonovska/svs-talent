package com.ventoelectrics.thermometer;

import java.util.Random;

import com.ventoelectrics.power_switch.VentoPoweredDevice;
import com.ventoelectrics.waterheater.NoPowerException;

public class VentoThermometer implements VentoPoweredDevice {
	
	private boolean powerEnabled = false;
	private Random random = new Random(System.currentTimeMillis());

	public Integer getTemperature() {
		if (!powerEnabled) {
			throw new NoPowerException();
		}
		return random.nextInt(60);
	}

	@Override
	public void enablePower() {
		powerEnabled = true;
	}

	@Override
	public void disablePower() {
		powerEnabled = false;
	}
}
