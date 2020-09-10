package ComplianceRequirements.Time.Interval;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMleadstoNwithinI extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMleadstoNwithinI(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
    //N is exchanged after M within interval I
	public boolean TrazaValida() {
		return !this.eventos.containsKey("M") ||
			    (this.eventos.containsKey("N") &&
				this.eventos.get("M").getIndex() < this.eventos.get("N").getIndex() &&
				Duration.between(
						this.eventos.get("M").getTimestamp().toInstant(), 
						this.eventos.get("N").getTimestamp().toInstant()
						).get(ChronoUnit.MILLIS) < this.interval);
	}
}
