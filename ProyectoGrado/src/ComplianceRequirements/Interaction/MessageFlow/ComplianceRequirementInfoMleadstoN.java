package ComplianceRequirements.Interaction.MessageFlow;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMleadstoN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMleadstoN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}

	//(i,M) -> (j,N) && i<j
	public boolean TrazaValida() {
		return !this.eventos.containsKey("M") || 
				(this.eventos.containsKey("N") && 
				this.eventos.get("M").getIndex()<this.eventos.get("N").getIndex());
	}
}
