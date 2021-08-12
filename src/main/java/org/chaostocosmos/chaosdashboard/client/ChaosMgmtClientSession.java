package org.chaostocosmos.chaosdashboard.client;

import java.io.IOException;
import java.lang.management.MemoryUsage;

import javax.management.AttributeChangeNotification;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;


/**
 * ����� Ŭ���̾�Ʈ ���� Ŭ����
 * @author 9ins
 *
 */
public class ChaosMgmtClientSession {	
	/**
	 * ����� Ŭ���̾�Ʈ �ν��Ͻ�
	 */
	ChaosMgmtClient client;
	
	/**
	 * ����� Ŭ���̾�Ʈ ������ �ν��Ͻ�
	 */
	ChaosMgmtClientFrame mFrame;
	
	/**
	 * JMX Ŀ���� ��ü
	 */
	JMXConnector jmxConnector;
	
	/**
	 * ������	
	 * @throws NullPointerException 
	 * @throws MalformedObjectNameException 
	 * @throws InstanceNotFoundException 
	 * @throws ReflectionException 
	 * @throws IntrospectionException 
	 * @throws MBeanException 
	 */
	public ChaosMgmtClientSession(JMXConnector jmxConnector, ChaosMgmtClient client, ChaosMgmtClientFrame mFrame) throws IOException, InstanceNotFoundException, MalformedObjectNameException, NullPointerException, IntrospectionException, ReflectionException, MBeanException {
		this.jmxConnector = jmxConnector;
		this.client = client;		
		this.mFrame = mFrame;
		this.jmxConnector.getMBeanServerConnection().addNotificationListener(new ObjectName("org.chaos.mgmt.mbeans:type=MessageInfo"), new ChaosMgmtListener(), null, null);
		
		MemoryUsage mu = (MemoryUsage) this.jmxConnector.getMBeanServerConnection().invoke(new ObjectName("java.lang:type=Memory"), "getHeapMemoryUsage", null, null);
		System.out.println(mu.getMax());
		}
	
	/**
	 * ������ �����Ѵ�. 
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.jmxConnector.close();
	}
	
	/**
	 * ���� Ŭ���̾�Ʈ ������
	 * @author 9ins
	 */
    public static class ChaosMgmtListener implements NotificationListener {
    	/**
    	 * �˸� ������ �ڵ鸵�Ѵ�.
    	 */
        public void handleNotification(Notification notification, Object handback) {
        	StringBuffer sb = new StringBuffer();
        	sb.append("Received notification:").append('\n');
        	sb.append("ClassName: " + notification.getClass().getName()).append('\n');
        	sb.append("Source: " + notification.getSource()).append('\n');
        	sb.append("Type: " + notification.getType()).append('\n');
        	sb.append("Message: " + notification.getMessage()).append('\n');
        	
            if (notification instanceof AttributeChangeNotification) {
                AttributeChangeNotification acn = (AttributeChangeNotification) notification;
                sb.append("AttributeName: " + acn.getAttributeName()).append('\n');
                sb.append("AttributeType: " + acn.getAttributeType()).append('\n');
                sb.append("NewValue: " + acn.getNewValue()).append('\n');
                sb.append("OldValue: " + acn.getOldValue()).append('\n');
            }        
            ChaosMgmtClient.echo(sb);
        }
    }
}
