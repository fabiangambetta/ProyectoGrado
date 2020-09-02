package ComplianceRequirements;

import org.deckfour.xes.model.XEvent;

public class XEventConIndex {

	int index;
	XEvent event;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public XEvent getEvent() {
		return event;
	}
	public void setEvent(XEvent event) {
		this.event = event;
	}
	public XEventConIndex(int index, XEvent event) {
		this.index= index;
		this.event= event;
	}

}
