package ComplianceRequirements.Time.Interval;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcooccursNafterI extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcooccursNafterI(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	//if M is exchanged, then N must be exchanged after interval I
	public boolean TrazaValida() {
		// TODO Auto-generated method stub
		if(!this.eventos.containsKey("M")) return true;
		else
		return this.eventos.containsKey("M") && this.eventos.containsKey("N") && this.eventos.containsKey("I") &&
				(this.eventos.get("N").getIndex() - this.eventos.get("M").getIndex()) > this.interval;
	}
}
