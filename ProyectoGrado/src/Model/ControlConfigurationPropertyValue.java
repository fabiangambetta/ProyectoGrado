package Model;

import java.util.ArrayList;

public class ControlConfigurationPropertyValue {
	String value;
	ControlConfigurationProperty controlconfigurationproperty;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ControlConfigurationProperty getControlconfigurationproperty() {
		return controlconfigurationproperty;
	}

	public void setControlconfigurationproperties(ControlConfigurationProperty controlconfigurationproperty) {
		this.controlconfigurationproperty = controlconfigurationproperty;
	}

	public ControlConfigurationPropertyValue(String value, ControlConfigurationProperty controlconfigurationproperty)
	{
		this.value= value;
		this.controlconfigurationproperty = controlconfigurationproperty;
	}
}
