package Model;

import java.util.ArrayList;

public class ComplianceFactor {
	int factorid;
	String name;
	ArrayList<ComplianceControl> compliancecontrols;
	
	public ComplianceFactor()
	{
		compliancecontrols = new ArrayList<ComplianceControl>();
	}

}
