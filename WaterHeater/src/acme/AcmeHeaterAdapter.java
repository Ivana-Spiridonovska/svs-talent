package acme;

import com.ventoelectrics.heater.VentoHeater;

import acme.AcmeHeaterDevice;

public class AcmeHeaterAdapter implements AcmeHeaterDevice{

	private VentoHeater heater;
	
	public AcmeHeaterAdapter(VentoHeater heater) {
		this.heater=heater;
	}
	
	@Override
	public void enable() {
		heater.enable();
		
	}
	@Override
	public void disable() {
		heater.disable();
	}

}
