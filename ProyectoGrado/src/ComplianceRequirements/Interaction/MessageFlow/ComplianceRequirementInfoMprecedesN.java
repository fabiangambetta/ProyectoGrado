package ComplianceRequirements.Interaction.MessageFlow;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMprecedesN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMprecedesN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}

	//N is exchanged after M
	public boolean TrazaValida() {
		return this.eventos.containsKey("M") && this.eventos.containsKey("N") && 
				this.eventos.get("M").getIndex()<this.eventos.get("N").getIndex();
	}
}
