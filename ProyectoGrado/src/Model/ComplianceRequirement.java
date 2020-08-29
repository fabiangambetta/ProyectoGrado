package Model;

import java.util.ArrayList;

public class ComplianceRequirement {
	int requirementid;
	String name;
	ComplianceControl controlcompliance;
	ArrayList<ComplianceObjectType> complianceobjecttypes;
	ArrayList<ControlConfigurationPropertyValue> controlconfigurationpropertiesvalue;
	ArrayList<Regulation> regulations;
	ArrayList<ComplianceObject> complianceobjects;
	
	public ComplianceRequirement(ComplianceControl pcontrolcompliance)
	{
		controlcompliance =  pcontrolcompliance;
		complianceobjecttypes = new ArrayList<ComplianceObjectType>();
		controlconfigurationpropertiesvalue = new ArrayList<ControlConfigurationPropertyValue>();
		regulations = new ArrayList<Regulation>();
		complianceobjects = new ArrayList<ComplianceObject>();
	}
}
