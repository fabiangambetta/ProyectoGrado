package ComplianceRequirements;

import java.util.Date;

import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.impl.XAttributeTimestampImpl;

public class XEventConIndex {

	int value;
	XEvent event;
	public int getIndex() {
		return value;
	}
	public void setIndex(int index) {
		this.value = index;
	}
	public XEvent getEvent() {
		return event;
	}
	
	public Date getTimestamp() {
		return ((XAttributeTimestampImpl)this.event.getAttributes().get("time:timestamp")).getValue();
	}
	
	public void setEvent(XEvent event) {
		this.event = event;
	}
	public XEventConIndex(int index, XEvent event) {
		this.value= index;
		this.event= event;
	}

}
