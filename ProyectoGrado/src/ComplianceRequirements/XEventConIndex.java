package ComplianceRequirements;

import org.deckfour.xes.model.XEvent;

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
	public void setEvent(XEvent event) {
		this.event = event;
	}
	public XEventConIndex(int index, XEvent event) {
		this.value= index;
		this.event= event;
	}

}
