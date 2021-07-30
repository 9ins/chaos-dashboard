package org.chaos.mgmt.common;

import java.util.EventListener;

public interface IObjectQueueListener extends EventListener {

	public void objectQueueEventReceived(ObjectQueueEvent oqe);
}
