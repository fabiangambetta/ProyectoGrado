package Model;

public class ComplianceObject {
	
	int complianceobjectid;
	String name;
	ComplianceObjectType complianceobjecttype;
	
	public int getComplianceobjectid() {
		return complianceobjectid;
	}
	public void setComplianceobjectid(int complianceobjectid) {
		this.complianceobjectid = complianceobjectid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ComplianceObjectType getComplianceobjecttype() {
		return complianceobjecttype;
	}
	public void setComplianceobjecttype(ComplianceObjectType complianceobjecttype) {
		this.complianceobjecttype = complianceobjecttype;
	}
	public ComplianceObject(int complianceobjectid, String name, ComplianceObjectType complianceobjecttype) {
		super();
		this.complianceobjectid = complianceobjectid;
		this.name = name;
		this.complianceobjecttype = complianceobjecttype;
	}
	
	
}
