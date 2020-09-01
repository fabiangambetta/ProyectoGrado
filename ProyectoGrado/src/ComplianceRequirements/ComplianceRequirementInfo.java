package ComplianceRequirements;

import java.util.ArrayList;

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;

import Model.ComplianceRequirement;

public class ComplianceRequirementInfo {

	public ComplianceRequirement requerimiento;
	protected ArrayList<XAttributeMap> eventos; 
	protected ArrayList<Integer> eventosIndex;
	public ComplianceRequirementInfo(ComplianceRequirement complianceRequirement) {
		this.requerimiento= complianceRequirement;
		this.eventos = new ArrayList<XAttributeMap>();
		this.eventosIndex= new ArrayList<Integer>();
	}

	public void UpdateInfo(int index, XAttributeMap eventAttributes) {
		this.eventos.add(eventAttributes);
		this.eventosIndex.add(index);
	}

	public boolean ReadyForEval() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean TrazaValida() {
		// TODO Auto-generated method stub
		return false;
	}

	public void CleanData() {
		this.eventos.clear();
		this.eventosIndex.clear();
	}

}
