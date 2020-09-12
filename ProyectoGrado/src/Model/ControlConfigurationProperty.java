package Model;

public class ControlConfigurationProperty {
	String name;
	String type;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ControlConfigurationProperty(String name, String type)
	{
		this.name= name;
		this.type= type;
	}
}
