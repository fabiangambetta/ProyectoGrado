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
	
	public int getRequirementid() {
		return requirementid;
	}

	public void setRequirementid(int requirementid) {
		this.requirementid = requirementid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ComplianceControl getControlcompliance() {
		return controlcompliance;
	}

	public void setControlcompliance(ComplianceControl controlcompliance) {
		this.controlcompliance = controlcompliance;
	}

	public ArrayList<ComplianceObjectType> getComplianceobjecttypes() {
		return complianceobjecttypes;
	}

	public void setComplianceobjecttypes(ArrayList<ComplianceObjectType> complianceobjecttypes) {
		this.complianceobjecttypes = complianceobjecttypes;
	}

	public ArrayList<ControlConfigurationPropertyValue> getControlconfigurationpropertiesvalue() {
		return controlconfigurationpropertiesvalue;
	}

	public void setControlconfigurationpropertiesvalue(
			ArrayList<ControlConfigurationPropertyValue> controlconfigurationpropertiesvalue) {
		this.controlconfigurationpropertiesvalue = controlconfigurationpropertiesvalue;
	}

	public ArrayList<Regulation> getRegulations() {
		return regulations;
	}

	public void setRegulations(ArrayList<Regulation> regulations) {
		this.regulations = regulations;
	}

	public ArrayList<ComplianceObject> getComplianceobjects() {
		return complianceobjects;
	}

	public void setComplianceobjects(ArrayList<ComplianceObject> complianceobjects) {
		this.complianceobjects = complianceobjects;
	}
	
	public ComplianceRequirement(int requirementid, String name, ComplianceControl controlcompliance)
	{
		this.requirementid= requirementid;
		this.name= name;
		this.controlcompliance= controlcompliance;
		this.complianceobjecttypes = new ArrayList<ComplianceObjectType>();
		this.controlconfigurationpropertiesvalue = new ArrayList<ControlConfigurationPropertyValue>();
		this.regulations = new ArrayList<Regulation>();
		this.complianceobjects = new ArrayList<ComplianceObject>();
	}
}
