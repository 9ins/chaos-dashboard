package org.chaostocosmos.chaosdashboard.common;

import java.util.EventObject;

public class ObjectQueueEvent extends EventObject {

	private int eventFlag;
	
	public ObjectQueueEvent(int eventFlag, Object obj) {
		super(obj);
		this.eventFlag = eventFlag;
	}	
	
	public int getEventFlag() {
		return this.eventFlag;
	}
}
