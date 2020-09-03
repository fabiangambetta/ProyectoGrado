package ComplianceRequirements.Interaction.SendReceiveMessages;

import java.util.Map;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcoabsentN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcoabsentN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public boolean TrazaValida() {
		
		return this.eventos.containsKey("M") || (!this.eventos.containsKey("M") && !this.eventos.containsKey("M"));
	}
}
