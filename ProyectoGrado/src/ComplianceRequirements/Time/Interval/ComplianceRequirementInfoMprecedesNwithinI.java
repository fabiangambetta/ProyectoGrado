package ComplianceRequirements.Time.Interval;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMprecedesNwithinI extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMprecedesNwithinI(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	//M is exchanged before N within interval I
	public boolean TrazaValida() {
		// TODO Auto-generated method stub
		return this.eventos.containsKey("M") && this.eventos.containsKey("N") && this.eventos.containsKey("I") &&
				(this.eventos.get("N").getIndex() - this.eventos.get("M").getIndex()) < this.interval;
	}
}
