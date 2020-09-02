package ComplianceRequirements.Time.Interval;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMleadstoNwithinI extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMleadstoNwithinI(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
    //N is exchanged after M within interval I
	public boolean TrazaValida() {
		// TODO Auto-generated method stub
		return this.eventos.containsKey("M") && this.eventos.containsKey("N") && this.eventos.containsKey("I") &&
				(this.eventos.get("M").getIndex() - this.eventos.get("M").getIndex()) < this.interval;
	}
}
