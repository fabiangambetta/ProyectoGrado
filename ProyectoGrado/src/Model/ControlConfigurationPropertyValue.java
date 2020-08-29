package Model;

import java.util.ArrayList;

public class ControlConfigurationPropertyValue {
	String value;
	ArrayList<ControlConfigurationProperty> controlconfigurationproperties;
	
	public ControlConfigurationPropertyValue()
	{
		controlconfigurationproperties = new ArrayList<ControlConfigurationProperty>();
	}
}
