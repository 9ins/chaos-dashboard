package org.chaos.mgmt.common;

import java.util.LinkedList;
import java.util.Properties;

import javax.swing.JOptionPane;

public class ObjectQueue extends LinkedList implements IQueue {
	
	IObjectQueueListener[] listeners = new IObjectQueueListener[0]; 
	
	public synchronized void enqueue(Object obj) {
		add(obj);
		dispatchObjectQueueEvent(ENQUEUE_EVENT, obj);
		notify();
	}
	
	public synchronized Object dequeue() {
		Object obj = null;
		if (super.size() == 0) {
			try	{
				wait();
				if(super.size() > 0) {				
					obj = removeFirst();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			obj = removeFirst();
		}
		dispatchObjectQueueEvent(DEQUEUE_EVENT, obj);		
		return	obj;
	}
	
    private void dispatchObjectQueueEvent(int eventFlag, Object obj) {
        for(int i=0; i<listeners.length; i++) {
            listeners[i].objectQueueEventReceived(new ObjectQueueEvent(eventFlag, obj));
        }
    }
    
    public void addObjectQueueListener(IObjectQueueListener listener) {
    	IObjectQueueListener[] tmp = new IObjectQueueListener[listeners.length+1];
    	System.arraycopy(listeners, 0, tmp, 0, listeners.length);
        tmp[listeners.length] = listener;
        listeners = tmp;
    }
    
    public synchronized void clear() {
       	super.clear();
    }
    
    public synchronized void close() {
   		clear();
   		notify();
    }
}