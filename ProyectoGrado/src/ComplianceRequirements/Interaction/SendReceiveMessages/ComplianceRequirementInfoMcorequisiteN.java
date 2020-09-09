package ComplianceRequirements.Interaction.SendReceiveMessages;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcorequisiteN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcorequisiteN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	// M sii N
	public boolean TrazaValida() {
		return (this.eventos.containsKey("M") && this.eventos.containsKey("N"))
				|| (!this.eventos.containsKey("M") && !this.eventos.containsKey("N"));
	}
}
