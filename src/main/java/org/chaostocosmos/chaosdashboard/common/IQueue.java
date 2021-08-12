package org.chaostocosmos.chaosdashboard.common;

public interface IQueue
{
	public static int ENQUEUE_EVENT = 0;
	
	public static int DEQUEUE_EVENT = 1;
	
    public void enqueue(Object object);

    public Object dequeue();
}
