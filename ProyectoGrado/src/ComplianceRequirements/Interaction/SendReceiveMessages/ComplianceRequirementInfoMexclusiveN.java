package ComplianceRequirements.Interaction.SendReceiveMessages;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMexclusiveN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMexclusiveN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
	}

	public boolean TrazaValida() {
		return !this.eventos.containsKey("M") || !this.eventos.containsKey("N");
	}
}
