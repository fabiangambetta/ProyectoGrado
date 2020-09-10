package ComplianceRequirements.Time.PointInTime;


import ComplianceRequirements.ComplianceRequirementInfo;
import Model.ComplianceRequirement;

public class ComplianceRequirementInfoMoccursbeforeD extends ComplianceRequirementInfo {

	public ComplianceRequirementInfoMoccursbeforeD(ComplianceRequirement complianceRequirement) {
		super(complianceRequirement);
		// TODO Auto-generated constructor stub
	}
	
	public boolean TrazaValida() {
		// TODO Auto-generated method stub CAMBIAR EL LOCALDATE.NOW
		return this.eventos.containsKey("M") &&  
				this.eventos.get("M").getTimestamp().before(this.date);
	}
}
