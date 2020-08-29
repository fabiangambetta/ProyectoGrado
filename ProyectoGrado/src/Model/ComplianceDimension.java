package Model;

import java.util.ArrayList;

public class ComplianceDimension {
	int dimensionid;
	String name;
	ArrayList<ComplianceFactor> compliancefactors;

	public ComplianceDimension()
	{
		compliancefactors = new ArrayList<ComplianceFactor>();
	}
	
}
