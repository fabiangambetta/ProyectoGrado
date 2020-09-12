package Model;

import java.util.ArrayList;

public class ComplianceModel {
	ArrayList<ComplianceArea> areas;
	ArrayList<ComplianceRequirement> requirements;
	ArrayList<ComplianceObject> objects;
	ArrayList<ComplianceObjectType> objectsTypes;
	ArrayList<Regulation> regulations;
	public ArrayList<ComplianceArea> getAreas() {
		return areas;
	}
	public void setAreas(ArrayList<ComplianceArea> areas) {
		this.areas = areas;
	}
	public ArrayList<ComplianceRequirement> getRequirements() {
		return requirements;
	}
	public void setRequirements(ArrayList<ComplianceRequirement> requirements) {
		this.requirements = requirements;
	}
	public ArrayList<ComplianceObject> getObjects() {
		return objects;
	}
	public void setObjects(ArrayList<ComplianceObject> objects) {
		this.objects = objects;
	}
	public ArrayList<ComplianceObjectType> getObjectsTypes() {
		return objectsTypes;
	}
	public void setObjectsTypes(ArrayList<ComplianceObjectType> objectsTypes) {
		this.objectsTypes = objectsTypes;
	}
	public ArrayList<Regulation> getRegulations() {
		return regulations;
	}
	public void setRegulations(ArrayList<Regulation> regulations) {
		this.regulations = regulations;
	}
	public ComplianceModel(ArrayList<ComplianceArea> areas, ArrayList<ComplianceRequirement> requirements,
			ArrayList<ComplianceObject> objects, ArrayList<ComplianceObjectType> objectsTypes,
			ArrayList<Regulation> regulations) {
		super();
		this.areas = areas;
		this.requirements = requirements;
		this.objects = objects;
		this.objectsTypes = objectsTypes;
		this.regulations = regulations;
	}
	public ComplianceModel() {
		this.areas = new ArrayList<ComplianceArea>();
		this.requirements = new ArrayList<ComplianceRequirement>();
		this.objects = new ArrayList<ComplianceObject>();
		this.objectsTypes = new ArrayList<ComplianceObjectType>();
		this.regulations = new ArrayList<Regulation>();
	}
	
	
}
