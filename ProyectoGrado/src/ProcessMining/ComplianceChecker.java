package ProcessMining;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XLogImpl;

import ComplianceRequirements.ComplianceRequirementInfo;
import ComplianceRequirements.Interaction.MessageFlow.ComplianceRequirementInfoMleadstoN;
import ComplianceRequirements.Interaction.MessageFlow.ComplianceRequirementInfoMprecedesN;
import ComplianceRequirements.Interaction.MessageFlow.ComplianceRequirementInfoMxleadstoN;
import ComplianceRequirements.Interaction.MessageFlow.ComplianceRequirementInfoRbetweenMandN;
import ComplianceRequirements.Interaction.SendReceiveMessages.ComplianceRequirementInfoMabsent;
import ComplianceRequirements.Interaction.SendReceiveMessages.ComplianceRequirementInfoMcoabsentN;
import ComplianceRequirements.Interaction.SendReceiveMessages.ComplianceRequirementInfoMcooccursN;
import ComplianceRequirements.Interaction.SendReceiveMessages.ComplianceRequirementInfoMcorequisiteN;
import ComplianceRequirements.Interaction.SendReceiveMessages.ComplianceRequirementInfoMexclusiveN;
import ComplianceRequirements.Interaction.SendReceiveMessages.ComplianceRequirementInfoMmutexchoiceN;
import ComplianceRequirements.Interaction.SendReceiveMessages.ComplianceRequirementInfoMoccurs;
import ComplianceRequirements.Time.Interval.ComplianceRequirementInfoMcooccursNafterI;
import ComplianceRequirements.Time.Interval.ComplianceRequirementInfoMcooccursNwithinI;
import ComplianceRequirements.Time.Interval.ComplianceRequirementInfoMleadstoNafterI;
import ComplianceRequirements.Time.Interval.ComplianceRequirementInfoMleadstoNwithinI;
import ComplianceRequirements.Time.Interval.ComplianceRequirementInfoMprecedesNafterI;
import ComplianceRequirements.Time.Interval.ComplianceRequirementInfoMprecedesNwithinI;
import ComplianceRequirements.Time.PointInTime.ComplianceRequirementInfoMoccursafterD;
import ComplianceRequirements.Time.PointInTime.ComplianceRequirementInfoMoccursatD;
import ComplianceRequirements.Time.PointInTime.ComplianceRequirementInfoMoccursbeforeD;
import Model.ComplianceControl;
import Model.ComplianceObject;
import Model.ComplianceRequirement;
import Model.ControlConfigurationProperty;
import Model.ControlConfigurationPropertyValue;

public class ComplianceChecker {

	public static void main(String[] args) {
		Map<String, ArrayList<ComplianceRequirementInfo>> objetoRequerimiento = new HashMap<String, ArrayList<ComplianceRequirementInfo>>();
		Map<ComplianceRequirement, ArrayList<XTrace>> trazasViolatorias = new HashMap<ComplianceRequirement, ArrayList<XTrace>>();
		ArrayList<ComplianceRequirement> requerimientos = CreateMockComplianceRequirements();
		ArrayList<ComplianceRequirementInfo> requerimientsInfo = new ArrayList<ComplianceRequirementInfo>();
		for (int i = 0; i < requerimientos.size(); i++) {
			ComplianceRequirementInfo cri = CreateComplianceRequirementInfo(requerimientos.get(i));
			requerimientsInfo.add(cri);
			for (int j = 0; j < requerimientos.get(i).getComplianceobjects().size(); j++) {
				ArrayList<ComplianceRequirementInfo> lista = objetoRequerimiento
						.get(requerimientos.get(i).getComplianceobjects().get(j).getName());
				if (lista == null) {
					lista = new ArrayList<ComplianceRequirementInfo>();
					objetoRequerimiento.put(requerimientos.get(i).getComplianceobjects().get(j).getName(), lista);
				}
				lista.add(cri);
			}
		}

		XLog log = loadXML("output118.xes");
		for (XTrace xTrace : log) {
			for (int i = 0; i < xTrace.size(); i++) {
				XAttribute message = xTrace.get(i).getAttributes().get("concept:name");
				ArrayList<ComplianceRequirementInfo> lista = objetoRequerimiento.get(message.toString());
				if (lista != null) {
					for (ComplianceRequirementInfo cri : lista) {
						if (cri != null && !cri.ReadyForEval()) {
							cri.UpdateInfo(i, xTrace.get(i));
						}
					}
				}
			}
			for (ComplianceRequirementInfo c : requerimientsInfo) {
				if (!c.TrazaValida()) {
					ArrayList<XTrace> lista = trazasViolatorias.get(c.requerimiento);
					if (lista == null) {
						lista = new ArrayList<XTrace>();
						trazasViolatorias.put(c.requerimiento, lista);
					} else {
						lista.add(xTrace);
					}
				}
				c.CleanData();
			}
		}
		for (ComplianceRequirement req : requerimientos) {
			ArrayList<XTrace> trazas = trazasViolatorias.get(req);
			System.out.println("req1 " + (trazas != null ? trazas.size() : 0) + " trazas violatorias");
		}

	}

	private static ArrayList<ComplianceRequirement> CreateMockComplianceRequirements() {
		ArrayList<ComplianceRequirement> reqs = new ArrayList<ComplianceRequirement>();
		reqs.add(CreateMockComplianceRequirement("Get Available Dates AGESIC", "Get Available Dates DNIC", "AGESIC",
				"DNIC", "DNIC", "AGESIC", "M cooccurs N"));
		reqs.add(CreateMockComplianceRequirement("Get Available Dates AGESIC", "Get Available Dates DNIC", "AGESIC",
				"DNIC", "DNIC", "AGESIC", "M coabsent N"));
		reqs.add(CreateMockComplianceRequirement("Get Available Dates AGESIC", "Get Available Dates DNIC", "AGESIC",
				"DNIC", "DNIC", "AGESIC", "M exclusive N"));
		return reqs;
	}

	private static ComplianceRequirement CreateMockComplianceRequirement(String m, String n, String senderm,
			String sendern, String receiverm, String receivern, String control) {
		ComplianceRequirement req = new ComplianceRequirement();
		ArrayList<ComplianceObject> objetosAsociados = new ArrayList<ComplianceObject>();
		ComplianceObject o = new ComplianceObject();
		o.setName(m);
		objetosAsociados.add(o);
		o = new ComplianceObject();
		o.setName(n);
		objetosAsociados.add(o);
		req.setComplianceobjects(objetosAsociados);
		ComplianceControl controlcompliance = new ComplianceControl();
		controlcompliance.setName(control);
		req.setControlcompliance(controlcompliance);
		ArrayList<ControlConfigurationPropertyValue> controlconfigurationpropertiesvalue = new ArrayList<ControlConfigurationPropertyValue>();
		ControlConfigurationPropertyValue e = new ControlConfigurationPropertyValue();
		ControlConfigurationProperty controlconfigurationproperty = new ControlConfigurationProperty();
		controlconfigurationproperty.setName("Message M");
		e.setControlconfigurationproperties(controlconfigurationproperty);
		e.setValue(m);
		controlconfigurationpropertiesvalue.add(e);
		e = new ControlConfigurationPropertyValue();
		controlconfigurationproperty = new ControlConfigurationProperty();
		controlconfigurationproperty.setName("Message N");
		e.setControlconfigurationproperties(controlconfigurationproperty);
		e.setValue(n);
		controlconfigurationpropertiesvalue.add(e);
		e = new ControlConfigurationPropertyValue();
		controlconfigurationproperty = new ControlConfigurationProperty();
		controlconfigurationproperty.setName("Sender M");
		e.setControlconfigurationproperties(controlconfigurationproperty);
		e.setValue(senderm);
		controlconfigurationpropertiesvalue.add(e);
		e = new ControlConfigurationPropertyValue();
		controlconfigurationproperty = new ControlConfigurationProperty();
		controlconfigurationproperty.setName("Receiver M");
		e.setControlconfigurationproperties(controlconfigurationproperty);
		e.setValue(receiverm);
		controlconfigurationpropertiesvalue.add(e);
		e = new ControlConfigurationPropertyValue();
		controlconfigurationproperty = new ControlConfigurationProperty();
		controlconfigurationproperty.setName("Receiver N");
		e.setControlconfigurationproperties(controlconfigurationproperty);
		e.setValue(receivern);
		controlconfigurationpropertiesvalue.add(e);
		e = new ControlConfigurationPropertyValue();
		controlconfigurationproperty = new ControlConfigurationProperty();
		controlconfigurationproperty.setName("Sender N");
		e.setControlconfigurationproperties(controlconfigurationproperty);
		e.setValue(sendern);
		controlconfigurationpropertiesvalue.add(e);
		req.setControlconfigurationpropertiesvalue(controlconfigurationpropertiesvalue);
		return req;
	}

	private static ComplianceRequirementInfo CreateComplianceRequirementInfo(
			ComplianceRequirement complianceRequirement) {
		switch (complianceRequirement.getControlcompliance().getName()) {
		/* Interaction Send / Receive Messages */
		case "M occurs":
			return new ComplianceRequirementInfoMoccurs(complianceRequirement);
		case "M absent":
			return new ComplianceRequirementInfoMabsent(complianceRequirement);
		case "M cooccurs N":
			return new ComplianceRequirementInfoMcooccursN(complianceRequirement);
		case "M coabsent N":
			return new ComplianceRequirementInfoMcoabsentN(complianceRequirement);
		case "M exclusive N":
			return new ComplianceRequirementInfoMexclusiveN(complianceRequirement);
		case "M corequisite N":
			return new ComplianceRequirementInfoMcorequisiteN(complianceRequirement);
		case "M mutex choice N":
			return new ComplianceRequirementInfoMmutexchoiceN(complianceRequirement);
		/* Interaction Message Flow */
		case "M precedes N":
			return new ComplianceRequirementInfoMprecedesN(complianceRequirement);
		case "M leadsto N":
			return new ComplianceRequirementInfoMleadstoN(complianceRequirement);
		case "M xleadsto N":
			return new ComplianceRequirementInfoMxleadstoN(complianceRequirement);
		case "R between M and N":
			return new ComplianceRequirementInfoRbetweenMandN(complianceRequirement);
		/* Time Points in Time */
		case "M occurs at D":
			return new ComplianceRequirementInfoMoccursatD(complianceRequirement);
		case "M occurs before D":
			return new ComplianceRequirementInfoMoccursbeforeD(complianceRequirement);
		case "M occurs after D":
			return new ComplianceRequirementInfoMoccursafterD(complianceRequirement);
		/* Time Interval */
		case "M cooccurs N within I":
			return new ComplianceRequirementInfoMcooccursNwithinI(complianceRequirement);
		case "M cooccurs N after I":
			return new ComplianceRequirementInfoMcooccursNafterI(complianceRequirement);
		case "M precedes N within I":
			return new ComplianceRequirementInfoMprecedesNwithinI(complianceRequirement);
		case "M precedes N after I":
			return new ComplianceRequirementInfoMprecedesNafterI(complianceRequirement);
		case "M leadsto N within I":
			return new ComplianceRequirementInfoMleadstoNwithinI(complianceRequirement);
		case "M leadsto N after I":
			return new ComplianceRequirementInfoMleadstoNafterI(complianceRequirement);
		default:
			return new ComplianceRequirementInfo(complianceRequirement);
		}
	}

	public static XLog loadXML(String pathtext) {
		Path path = Paths.get(pathtext);
		File xmlLogs = new File(path.toString());
		XesXmlParser parser = new XesXmlParser();
		boolean okParser = parser.canParse(xmlLogs);
		ArrayList<XLog> logs;
		try {
			logs = (ArrayList<XLog>) parser.parse(xmlLogs);
			return logs.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return new XLogImpl(null);
			// TODO Auto-generated catch block
		}

	}
}
