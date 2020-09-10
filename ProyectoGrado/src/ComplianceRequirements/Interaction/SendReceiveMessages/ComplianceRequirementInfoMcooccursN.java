package ComplianceRequirements.Interaction.SendReceiveMessages;

import java.util.Map;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcooccursN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcooccursN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	// M -> N
	public boolean TrazaValida() {
		return !this.eventos.containsKey("M") || this.eventos.containsKey("N");
	}
}
