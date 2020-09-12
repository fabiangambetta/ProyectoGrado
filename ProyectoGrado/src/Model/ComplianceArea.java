package Model;

import java.util.ArrayList;

public class ComplianceArea {
	
	int areaid;
	
	String name;
	
	ArrayList<ComplianceDimension> compliancedimensions;
	
	
	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ComplianceDimension> getCompliancedimensions() {
		return compliancedimensions;
	}

	public void setCompliancedimensions(ArrayList<ComplianceDimension> compliancedimensions) {
		this.compliancedimensions = compliancedimensions;
	}

	
	public ComplianceArea(int areaid,String name)
	{
		this.areaid = areaid;
		this.name = name;
		compliancedimensions =  new ArrayList<ComplianceDimension>();
	}
}
