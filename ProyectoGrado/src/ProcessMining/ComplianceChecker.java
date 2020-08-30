package ProcessMining;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;
import org.deckfour.xes.model.impl.XLogImpl;

import Model.ComplianceRequirement;

public class ComplianceChecker {

	public static void main(String[] args) {
		Map<String, ArrayList<ComplianceRequirementInfo>> objetoRequerimiento = new HashMap<String, ArrayList<ComplianceRequirementInfo>>();
		Map<ComplianceRequirement, ArrayList<XTrace>> trazasViolatorias = new HashMap<ComplianceRequirement, ArrayList<XTrace>>();
		ArrayList<ComplianceRequirement> requerimientos = new ArrayList<ComplianceRequirement>();
		ArrayList<ComplianceRequirementInfo> requerimientsInfo = new ArrayList<ComplianceRequirementInfo>();
		for (int i = 0; i < requerimientos.size(); i++) {
			ComplianceRequirementInfo cri = new ComplianceRequirementInfo(requerimientos.get(i));
			requerimientsInfo.add(cri);
			for (int j = 0; j < requerimientos.get(i).getComplianceobjects().size(); j++) {
				ArrayList<ComplianceRequirementInfo> lista= objetoRequerimiento.get(requerimientos.get(i).getComplianceobjects().get(j).getName());
				if(lista == null)
				{
					lista = new ArrayList<ComplianceRequirementInfo>();
					objetoRequerimiento.put(requerimientos.get(i).getComplianceobjects().get(j).getName(), lista);
				}
				lista.add(cri);
			}
		}

		XLog log = loadXML("output130.xes");
		for (XTrace xTrace : log) {
			for (int i = 0; i < xTrace.size(); i++) {
				XAttribute message = xTrace.get(i).getAttributes().get("concept:name");
				ArrayList<ComplianceRequirementInfo>  lista = objetoRequerimiento.get(message.toString());
				for (ComplianceRequirementInfo cri : lista) {
					if (cri != null && !cri.ReadyForEval()) {
						cri.UpdateInfo(i, xTrace.get(i).getAttributes());
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
		for (ComplianceRequirement req: requerimientos) {
			ArrayList<XTrace> trazas= trazasViolatorias.get(req);			
			System.out.println("req1 " + (trazas!= null? trazas.size(): 0)+" trazas violatorias");
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
