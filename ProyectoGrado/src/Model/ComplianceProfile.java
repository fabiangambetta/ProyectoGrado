package Model;

import java.util.ArrayList;

public class ComplianceProfile {
	int profileid;
	ArrayList<ComplianceObjectType> complianceobjectstype;
	ComplianceObject complianceobject;
	
	public ComplianceProfile()
	{
		complianceobjectstype = new ArrayList<ComplianceObjectType>();
	}
}
