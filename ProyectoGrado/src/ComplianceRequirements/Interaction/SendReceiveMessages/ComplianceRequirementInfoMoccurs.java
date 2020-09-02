package ComplianceRequirements.Interaction.SendReceiveMessages;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMoccurs extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMoccurs(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	
	public boolean TrazaValida() {
		return this.propsFaltantes.isEmpty();
	}

}
