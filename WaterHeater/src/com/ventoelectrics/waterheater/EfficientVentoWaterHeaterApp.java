package com.ventoelectrics.waterheater;

import com.ventoelectrics.heater.VentoHeater;
import com.ventoelectrics.power_switch.VentoPowerSwitch;
import com.ventoelectrics.thermometer.VentoThermometer;

import acme.AcmeHeaterAdapter;
import acme.AcmeThermometerAdapter;
import acme.AcmeThermoregulatorAdapter;
import acme.thermoregulator.AcmeEfficientThermoregulator;

public class EfficientVentoWaterHeaterApp {

	public static void main(String[] args) throws Exception {

		final VentoThermometer ventoThermometer = new VentoThermometer();
		final VentoHeater ventoHeater = new VentoHeater();
		final VentoPowerSwitch ventoPowerSwitch = new VentoPowerSwitch();

		final VentoThermoregulator ventoThermoregulator;
		ventoThermoregulator = new AcmeThermoregulatorAdapter(
				new AcmeEfficientThermoregulator(new AcmeThermometerAdapter(
						ventoThermometer), new AcmeHeaterAdapter(ventoHeater)));

		ventoPowerSwitch.controlPowerFor(ventoThermoregulator);
		ventoPowerSwitch.controlPowerFor(ventoHeater);
		ventoPowerSwitch.controlPowerFor(ventoThermometer);

		VentoWaterHeaterApp.run(ventoThermoregulator, ventoPowerSwitch);
	}
}
