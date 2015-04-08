package com.ventoelectrics.waterheater;

import com.ventoelectrics.power_switch.VentoPoweredDevice;

public interface VentoThermoregulator extends VentoPoweredDevice {

	public void setTemperature(Integer temperature);
}
