package ComplianceRequirements.Time.Interval;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.deckfour.xes.model.impl.XAttributeTimestampImpl;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMcooccursNafterI extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMcooccursNafterI(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	//if M is exchanged, then N must be exchanged after interval I
	public boolean TrazaValida() {
		return !this.eventos.containsKey("M") || 
				(this.eventos.containsKey("N") &&
						Math.abs(Duration.between(
								this.eventos.get("M").getTimestamp().toInstant(), 
								this.eventos.get("N").getTimestamp().toInstant()
								).get(ChronoUnit.MILLIS)) 
						> this.interval);
	}
}
