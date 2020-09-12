package Model;

import java.util.ArrayList;

public class ComplianceDimension {
	int dimensionid;
	String name;
	ArrayList<ComplianceFactor> compliancefactors;

	public int getDimensionid() {
		return dimensionid;
	}

	public void setDimensionid(int dimensionid) {
		this.dimensionid = dimensionid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ComplianceFactor> getCompliancefactors() {
		return compliancefactors;
	}

	public void setCompliancefactors(ArrayList<ComplianceFactor> compliancefactors) {
		this.compliancefactors = compliancefactors;
	}

	public ComplianceDimension(int dimensionid, String name)
	{
		this.dimensionid= dimensionid;
		this.name = name;
		compliancefactors = new ArrayList<ComplianceFactor>();
	}
	
}
