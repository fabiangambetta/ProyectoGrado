package ComplianceRequirements.Interaction.SendReceiveMessages;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcorequisiteN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcorequisiteN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	
	public boolean TrazaValida() {
		return this.propsFaltantes.get("Message").isEmpty() || this.propsFaltantes.get("Message").size() == 2;
	}
}
