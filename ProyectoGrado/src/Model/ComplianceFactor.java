package Model;

import java.util.ArrayList;

public class ComplianceFactor {
	int factorid;
	String name;
	ArrayList<ComplianceControl> compliancecontrols;
	
	public int getFactorid() {
		return factorid;
	}


	public void setFactorid(int factorid) {
		this.factorid = factorid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<ComplianceControl> getCompliancecontrols() {
		return compliancecontrols;
	}


	public void setCompliancecontrols(ArrayList<ComplianceControl> compliancecontrols) {
		this.compliancecontrols = compliancecontrols;
	}


	public ComplianceFactor(int factorid,String name)
	{
		this.factorid=factorid;
		this.name=name;
		compliancecontrols = new ArrayList<ComplianceControl>();
	}

}
