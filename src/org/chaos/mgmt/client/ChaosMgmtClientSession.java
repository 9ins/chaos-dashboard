package org.chaos.mgmt.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.MemoryUsage;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.management.AttributeChangeNotification;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.swing.JOptionPane;

import org.chaos.mgmt.agent.ChaosAgent;
import org.chaos.mgmt.agent.MBeanFactory;
import org.chaos.mgmt.common.Logger;
import org.chaos.mgmt.common.ObjectQueue;
import org.chaos.mgmt.common.PropertyHandler;
import org.chaos.mgmt.mbeans.DBConnectionPoolInfo;
import org.chaos.mgmt.mbeans.FileBufferInfo;
import org.chaos.mgmt.mbeans.MessageInfo;
import org.chaos.mgmt.mbeans.ObjectPoolInfo;


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
		
		MemoryUsage mu = this.jmxConnector.getMBeanServerConnection().invoke(new ObjectName("java.lang:type=Memory"), "getHeapMemoryUsage", null, null);
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
