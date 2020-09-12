package Model;

public class Regulation {
	int regulationid;
	String name;
	
	public int getRegulationid() {
		return regulationid;
	}

	public void setRegulationid(int regulationid) {
		this.regulationid = regulationid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public Regulation(int regulationid, String name) {
		super();
		this.regulationid = regulationid;
		this.name = name;
	}

}
