package ComplianceRequirements.Interaction.SendReceiveMessages;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMabsent extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMabsent(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}

	public boolean TrazaValida() {
		return !this.eventos.containsKey("M");
	}
}
