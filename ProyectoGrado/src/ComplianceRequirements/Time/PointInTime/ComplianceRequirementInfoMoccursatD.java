package ComplianceRequirements.Time.PointInTime;

import java.time.LocalDate;

import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMoccursatD extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMoccursatD(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	//M is exchanged from S to R at D
	public boolean TrazaValida() {
		// TODO Auto-generated method stub
		return this.eventos.containsKey("M") &&  
				this.eventos.get("M").getTimestamp().equals(this.date);
	}
}
