package Model;

import java.util.ArrayList;

public class ComplianceControl {
	int controlid;
	String name;
	ArrayList<ControlConfigurationProperty> controlconfigurationproperties;
	ArrayList<ComplianceObjectType> complianceobjecttypes;
	
	public ComplianceControl()
	{
		controlconfigurationproperties = new ArrayList<ControlConfigurationProperty>();
		complianceobjecttypes = new ArrayList<ComplianceObjectType>();
	}
}
