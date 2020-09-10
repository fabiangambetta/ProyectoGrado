package ComplianceRequirements.Time.Interval;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcooccursNwithinI extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcooccursNwithinI(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	//if M is exchanged, then N must be exchanged within interval I
	public boolean TrazaValida() {
		// TODO Auto-generated method stub
		return !this.eventos.containsKey("M") ||
				(this.eventos.containsKey("N") &&
				Math.abs(Duration.between(
						this.eventos.get("M").getTimestamp().toInstant(), 
						this.eventos.get("N").getTimestamp().toInstant()
						).get(ChronoUnit.MILLIS)) 
				< this.interval);
	}
}
