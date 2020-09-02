package ComplianceRequirements.Interaction.SendReceiveMessages;

import java.util.Map;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcooccursN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcooccursN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}

	public boolean TrazaValida() {
		return !this.eventos.containsKey("m") || (this.eventos.containsKey("m") && this.eventos.containsKey("n"));
	}
}
