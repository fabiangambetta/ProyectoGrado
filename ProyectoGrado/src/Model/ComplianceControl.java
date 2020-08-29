package Model;

import java.util.ArrayList;

public class ComplianceControl {
	int controlid;
	String name;
	ArrayList<ControlConfigurationProperty> controlconfigurationproperties;
	ArrayList<ComplianceControl> compliancecontrols;
	
	public ComplianceControl()
	{
		controlconfigurationproperties = new ArrayList<ControlConfigurationProperty>();
		compliancecontrols = new ArrayList<ComplianceControl>();
	}
}
