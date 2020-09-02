package ComplianceRequirements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XEvent;

import Model.ComplianceRequirement;
import Model.ControlConfigurationPropertyValue;

public class ComplianceRequirementInfo {

	public ComplianceRequirement requerimiento;
	protected Map<String, XEventConIndex> eventos;
	protected Map<String,ArrayList<ControlConfigurationPropertyValue>> propsFaltantes;
	public ComplianceRequirementInfo(ComplianceRequirement complianceRequirement) {
		this.requerimiento= complianceRequirement;
		
		this.propsFaltantes= new HashMap<String, ArrayList<ControlConfigurationPropertyValue>>();
		this.propsFaltantes.put("Message", new ArrayList<ControlConfigurationPropertyValue>());
		this.propsFaltantes.put("Sender", new ArrayList<ControlConfigurationPropertyValue>());
		this.propsFaltantes.put("Receiver", new ArrayList<ControlConfigurationPropertyValue>());
		this.eventos = new HashMap<String, XEventConIndex>();
		this.Initialize();
		
	}
	
	private void Initialize() {
		for (ControlConfigurationPropertyValue prop : this.requerimiento.getControlconfigurationpropertiesvalue()) {
			if(prop.getControlconfigurationproperty().getName().startsWith("Message"))
			{
				ArrayList<ControlConfigurationPropertyValue> lista =this.propsFaltantes.get("Message");
				lista.add(prop);
			}
			else if(prop.getControlconfigurationproperty().getName().startsWith("Sender"))
			{	
				ArrayList<ControlConfigurationPropertyValue> lista =this.propsFaltantes.get("Sender");
				lista.add(prop);
			}
 			else if(prop.getControlconfigurationproperty().getName().startsWith("Receiver"))
			{	
				ArrayList<ControlConfigurationPropertyValue> lista =this.propsFaltantes.get("Receiver");
				lista.add(prop);
			}
		}
	}
	public void UpdateInfo(int index, XEvent event) {
		Map<String, Integer> existe= new HashMap<String, Integer>();
		existe.put("concept:name", -1);
		existe.put("collab:fromParticipant", -1);
		existe.put("collab:toParticipant", -1);
		ArrayList<ControlConfigurationPropertyValue> mensajesFaltantes= this.propsFaltantes.get("Message");
		String prop= "";
		for (int i = 0; i < mensajesFaltantes.size(); i++) {
			if(event.getAttributes().get("concept:name").toString().equals(mensajesFaltantes.get(i).getValue()))
			{
				String param= mensajesFaltantes.get(i).getControlconfigurationproperty().getName();
				prop= param.substring(8, param.length()-1);
				// asumo nombre de parametro "Message {0}"
				existe.put("concept:name", i);
			}
		}
		ArrayList<ControlConfigurationPropertyValue> senderFaltantes= this.propsFaltantes.get("Sender");
		for (int i = 0; i < senderFaltantes.size(); i++) {
			if(event.getAttributes().get("collab:fromParticipant").toString().equals(senderFaltantes.get(i).getValue()))
			{
				String param= senderFaltantes.get(i).getControlconfigurationproperty().getName();
				// asumo nombre de parametro "Sender {0}"
				existe.put("collab:fromParticipant", param.equals("Sender "+prop)? i:-1);
			}
		}
		ArrayList<ControlConfigurationPropertyValue> receiverFaltantes= this.propsFaltantes.get("Sender");
		for (int i = 0; i < receiverFaltantes.size(); i++) {
			if(event.getAttributes().get("collab:toParticipant").toString().equals(receiverFaltantes.get(i).getValue()))
			{
				String param= receiverFaltantes.get(i).getControlconfigurationproperty().getName();
				// asumo nombre de parametro "Receiver {0}"
				existe.put("collab:toParticipant", param.equals("Receiver "+prop)? i:-1);
			}
		}
		if(!existe.containsValue(-1)) {
			int ind=existe.get("concept:name");
			mensajesFaltantes.remove(ind);
			ind=existe.get("collab:fromParticipant");
			senderFaltantes.remove(ind);
			ind=existe.get("collab:toParticipant");
			receiverFaltantes.remove(ind);
			XEventConIndex ev = new XEventConIndex(index, event);
			this.eventos.put(prop, ev);
		}
	}

	public boolean ReadyForEval() {
		return propsFaltantes.get("Message").isEmpty();
	}

	public boolean TrazaValida() {
		return propsFaltantes.isEmpty();
	}

	public void CleanData() {
		this.eventos.clear();
		this.propsFaltantes.get("Message").clear();
		this.propsFaltantes.get("Sender").clear();
		this.propsFaltantes.get("Receiver").clear();
		this.Initialize();
	}

}

