package acme.thermoregulator;

import acme.AcmeHeaterDevice;
import acme.AcmeThermometerDevice;

public class AcmeEfficientThermoregulator extends AcmeStandardThermoregulator{

	public AcmeEfficientThermoregulator(AcmeThermometerDevice td,
			AcmeHeaterDevice hd) {
		super(td, hd);
		seconds = 1;
	}

	

}
