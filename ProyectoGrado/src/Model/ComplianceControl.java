package Model;

import java.util.ArrayList;

public class ComplianceControl {
	int controlid;
	String name;
	ArrayList<ControlConfigurationProperty> controlconfigurationproperties;
	ArrayList<ComplianceObjectType> complianceobjecttypes;
	
	public int getControlid() {
		return controlid;
	}

	public void setControlid(int controlid) {
		this.controlid = controlid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ControlConfigurationProperty> getControlconfigurationproperties() {
		return controlconfigurationproperties;
	}

	public void setControlconfigurationproperties(ArrayList<ControlConfigurationProperty> controlconfigurationproperties) {
		this.controlconfigurationproperties = controlconfigurationproperties;
	}

	public ArrayList<ComplianceObjectType> getComplianceobjecttypes() {
		return complianceobjecttypes;
	}

	public void setComplianceobjecttypes(ArrayList<ComplianceObjectType> complianceobjecttypes) {
		this.complianceobjecttypes = complianceobjecttypes;
	}

	public ComplianceControl()
	{
		controlconfigurationproperties = new ArrayList<ControlConfigurationProperty>();
		complianceobjecttypes = new ArrayList<ComplianceObjectType>();
	}
}
