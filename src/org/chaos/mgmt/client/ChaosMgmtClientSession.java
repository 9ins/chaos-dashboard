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
 * 모니터 클라이언트 세션 클래스
 * @author 9ins
 *
 */
public class ChaosMgmtClientSession {	
	/**
	 * 모니터 클라이언트 인스턴스
	 */
	ChaosMgmtClient client;
	
	/**
	 * 모니터 클라이언트 프레임 인스턴스
	 */
	ChaosMgmtClientFrame mFrame;
	
	/**
	 * JMX 커넥터 객체
	 */
	JMXConnector jmxConnector;
	
	/**
	 * 생성자	
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
	 * 세션을 종료한다. 
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.jmxConnector.close();
	}
	
	/**
	 * 관리 클라이언트 리스너
	 * @author 9ins
	 */
    public static class ChaosMgmtListener implements NotificationListener {
    	/**
    	 * 알림 정보를 핸들링한다.
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
