package acme;

import com.ventoelectrics.waterheater.VentoThermoregulator;

import acme.thermoregulator.AcmeThermoregulator;

public class AcmeThermoregulatorAdapter implements VentoThermoregulator {

	private AcmeThermoregulator thermoregulator;

	public AcmeThermoregulatorAdapter(AcmeThermoregulator thermoregulator) {
		this.thermoregulator = thermoregulator;
	}

	@Override
	public void enablePower() {
		thermoregulator.enablePower();

	}

	@Override
	public void disablePower() {
		thermoregulator.disablePower();

	}

	@Override
	public void setTemperature(Integer temperature) {
		thermoregulator.setTemperature(temperature);
		
	}

}
