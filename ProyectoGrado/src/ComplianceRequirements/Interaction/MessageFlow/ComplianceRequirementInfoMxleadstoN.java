package ComplianceRequirements.Interaction.MessageFlow;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMxleadstoN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMxleadstoN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}

	// N is immediatily exchanged after M
	public boolean TrazaValida() {
		return this.eventos.containsKey("M") && this.eventos.containsKey("N")
				&& this.eventos.get("M").getIndex() == this.eventos.get("N").getIndex()+1;
	}
}
