package ComplianceRequirements.Interaction.SendReceiveMessages;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMmutexchoiceN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMmutexchoiceN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}

	public boolean TrazaValida() {
		return this.propsFaltantes.get("Message").size()==1;
	}
}
