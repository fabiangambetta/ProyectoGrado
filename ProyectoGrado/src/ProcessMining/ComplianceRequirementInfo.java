package ProcessMining;

import org.deckfour.xes.model.XAttributeMap;

import Model.ComplianceRequirement;

public class ComplianceRequirementInfo {

	public ComplianceRequirement requerimiento;
	
	public ComplianceRequirementInfo(ComplianceRequirement complianceRequirement) {
		requerimiento= complianceRequirement;
	}

	public void UpdateInfo(int index, XAttributeMap eventAttributes) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
	    // borra los datos de los eventos de la traza en proceso
	}

}
