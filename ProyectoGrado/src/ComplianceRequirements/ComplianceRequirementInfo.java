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
	protected ArrayList<ControlConfigurationPropertyValue> eventmessageFaltantes;
	protected Map<String,ControlConfigurationPropertyValue> senders;
	protected Map<String,ControlConfigurationPropertyValue> receivers;
	public ComplianceRequirementInfo(ComplianceRequirement complianceRequirement) {
		this.requerimiento= complianceRequirement;
		
		this.eventmessageFaltantes= new ArrayList<ControlConfigurationPropertyValue>();
		this.senders= new HashMap<String,ControlConfigurationPropertyValue>();
		this.receivers= new HashMap<String,ControlConfigurationPropertyValue>();
		this.eventos = new HashMap<String, XEventConIndex>();
		this.Initialize();
		
	}
	
	private void Initialize() {
		for (ControlConfigurationPropertyValue prop : this.requerimiento.getControlconfigurationpropertiesvalue()) {
			if(prop.getControlconfigurationproperty().getName().startsWith("Message"))
			{
				this.eventmessageFaltantes.add(prop);
			}
			else if(prop.getControlconfigurationproperty().getName().startsWith("Sender"))
			{	
				String param=prop.getControlconfigurationproperty().getName();
				this.senders.put(param.substring(7, param.length()), prop);
			}
 			else if(prop.getControlconfigurationproperty().getName().startsWith("Receiver"))
			{	
 				String param=prop.getControlconfigurationproperty().getName();
				this.receivers.put(param.substring(9, param.length()), prop);
			}
		}
	}
	public void UpdateInfo(int index, XEvent event) {
		int existe=-1; 
		String prop= "";
		for (int i = 0; i < this.eventmessageFaltantes.size(); i++) {
			if(event.getAttributes().get("concept:name").toString().equals(this.eventmessageFaltantes.get(i).getValue()))
			{
				String param= this.eventmessageFaltantes.get(i).getControlconfigurationproperty().getName();
				prop= param.substring(8, param.length());
				// asumo nombre de parametro "Message {0}"
				existe= i;
			}
		}
		
		ControlConfigurationPropertyValue senderEsperado= this.senders.get(prop);
		ControlConfigurationPropertyValue receiverEsperado= this.receivers.get(prop);
		if(existe>-1 && 
				senderEsperado.getValue().equals(event.getAttributes().get("collab:fromParticipant").toString())
				&& receiverEsperado.getValue().equals(event.getAttributes().get("collab:toParticipant").toString())
				) {
			this.eventmessageFaltantes.remove(existe);
			XEventConIndex ev = new XEventConIndex(index, event);
			this.eventos.put(prop, ev);
		}
	}

	public boolean ReadyForEval() {
		return this.eventmessageFaltantes.isEmpty();
	}

	public boolean TrazaValida() {
		return true;
	}

	public void CleanData() {
		this.eventos.clear();
		this.eventmessageFaltantes.clear();
		this.Initialize();
	}

}

