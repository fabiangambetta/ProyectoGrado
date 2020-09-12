package Model;

public class ComplianceObjectType {
	int typeid;
	String name;
	
	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public ComplianceObjectType(int typeid, String name) {
		super();
		this.typeid = typeid;
		this.name = name;
	}

}
