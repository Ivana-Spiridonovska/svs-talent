package acme;

import com.ventoelectrics.thermometer.VentoThermometer;

import acme.AcmeThermometerDevice;

public class AcmeThermometerAdapter implements AcmeThermometerDevice{

	private VentoThermometer thermometar;
	
	public AcmeThermometerAdapter(VentoThermometer thermometar) {
		this.thermometar=thermometar;
		
	}

	@Override
	public int getTemperature() {
		return thermometar.getTemperature();
	}
	

}
