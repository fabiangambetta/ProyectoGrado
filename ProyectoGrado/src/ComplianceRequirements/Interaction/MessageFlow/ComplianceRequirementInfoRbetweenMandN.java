package ComplianceRequirements.Interaction.MessageFlow;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoRbetweenMandN extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoRbetweenMandN(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}

	// R is exchanged between M and N
	public boolean TrazaValida() {
		return (!this.eventos.containsKey("M") || !this.eventos.containsKey("N")) || (this.eventos.containsKey("R")
				&& ((this.eventos.get("M").getIndex() < this.eventos.get("R").getIndex()
						&& this.eventos.get("R").getIndex() < this.eventos.get("N").getIndex())
						|| (this.eventos.get("M").getIndex() > this.eventos.get("R").getIndex()
								&& this.eventos.get("R").getIndex() > this.eventos.get("N").getIndex())));
	}
}
