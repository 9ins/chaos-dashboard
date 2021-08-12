package org.chaostocosmos.chaosdashboard.agent;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXPrincipal;
import javax.management.remote.JMXServiceURL;
import javax.security.auth.Subject;

import org.chaostocosmos.chaosdashboard.mbeans.MessageInfo;


/**
 * ���� ����� Ŭ����
 * @author 9ins
 *
 */
public class ChaosAgent { //extends Thread {
	/**
	 * RMI ������Ʈ��
	 */
	Registry rmiReg;
	
	/**
	 * MBean ����
	 */
	MBeanServer mbs;
	
	/**
	 * JMX Ŀ���� ����
	 */
	JMXConnectorServer connectorServer;
	
	/**
	 * ���� URL ��ü
	 */
	JMXServiceURL serviceUrl;
	
	/**
	 * �����
	 */
	String user;
	
	/**
	 * �н�����
	 */
	String passwd;
	
	/**
	 * ���Ῡ��
	 */
	boolean isDone;
	
	/**
	 * ������
	 * @param username ����ڸ�
	 * @param passwd �н�����
	 * @param serviceUrl ���� URL
	 * @param autoStart �ڵ� ���� ����
	 * @throws MalformedURLException
	 */
	public ChaosAgent(String username, String passwd, String serviceUrl, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL(serviceUrl), autoStart);
	}
	
	/**
	 * ������
	 * @param username ����ڸ�
	 * @param passwd �н�����
	 * @param port ������Ʈ
	 * @param autoStart �ڵ� ���� ����
	 * @throws MalformedURLException 
	 */
	public ChaosAgent(String username, String passwd, int port, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL("rmi", "localhost", port, "chaos_agent"), true);
	}
	
	/**
	 * ������
	 * @param username ����ڸ�
	 * @param passwd �н�����
	 * @param port ��Ʈ
	 * @param serviceName ���񽺸�
	 * @param autoStart �ڵ� ���� ����
	 * @throws MalformedURLException
	 */
	public ChaosAgent(String username, String passwd, int port, String serviceName, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL("rmi", "localhost", port, "/jndi/rmi://localhost:"+port+"/"+serviceName), true);
	}
	
	/**
	 * ������
	 * @param username ����ڸ�
	 * @param passwd �н�����
	 * @param protocol ��������
	 * @param host ȣ��Ʈ
	 * @param port ��Ʈ 
	 * @param autoStart �۵� ���� ����
	 * @throws MalformedURLException 
	 */
	public ChaosAgent(String username, String passwd, String protocol, String host, int port, String urlPath, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL(protocol, host, port, urlPath), true);
	}
	
	/**
	 * ������
	 * @param username ����ڸ�
	 * @param passwd �н�����
	 * @param serviceUrl ���� URL
	 * @param autoStart �ڵ� ���� ����
	 */
	public ChaosAgent(String username, String passwd, JMXServiceURL serviceUrl, boolean autoStart) {
		this.serviceUrl = serviceUrl;
		this.user = username;
		this.passwd = passwd;
		if(autoStart)
			startMonitorServer();
	}
	
	/**
	 * ����� ������ �����Ѵ�.
	 */
	public void startMonitorServer() {
		//start();
		run();
		this.isDone = false;
	}
	
	/**
	 * ����� ������ �����Ѵ�.
	 */
	public void stopMonitorServer() {
		try {
			this.connectorServer.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.isDone = true;
	}
	
	/**
	 * ���� MBean�� ����Ѵ�.
	 * @param mbean
	 */
	public void registerMBean(Object mbean) {
		try {
			ObjectName on = new ObjectName(mbean.getClass().getPackage().getName()+":type="+mbean.getClass().getSimpleName());
			ObjectInstance oInstance = new ObjectInstance(on, mbean.getClass().getName());
			if(!this.mbs.isRegistered(on))
				this.mbs.registerMBean(mbean, on);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		}
	}
	
	public void processThrowable(Throwable throwable) {
		
	}
	
	public void run() {
		try {
			this.rmiReg = LocateRegistry.createRegistry(this.serviceUrl.getPort());
	        this.mbs = ManagementFactory.getPlatformMBeanServer();
	        MBeanFactory.getInstance().registerMXBeansToMgmtServer(this);
	        MBeanFactory.getInstance().registerMBeansToMonitorServer(this);
	        
	        Map env = new HashMap();
	        //env.put(Context.SECURITY_AUTHENTICATION, "true");
	        env.put(JMXConnectorServer.AUTHENTICATOR, new JMXAuthenticatorImpl(this.user, this.passwd));
	        //env.put(JMXConnectorServer.AUTHENTICATOR, new String[]{this.user, this.passwd});
	        this.connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(this.serviceUrl, env, mbs);
	        this.connectorServer.start();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		StringBuffer sb = new StringBuffer();
		sb.append("Start monitor server.").append('\n');
		sb.append("Protocol : "+this.serviceUrl.getProtocol()).append('\n');
		sb.append("Host : "+this.serviceUrl.getHost()).append('\n');
		sb.append("Port : "+this.serviceUrl.getPort()).append('\n');
		sb.append("URL path : "+this.serviceUrl.getURLPath()).append('\n');
		echo(sb);
		
		MessageInfo mbean = (MessageInfo)MBeanFactory.getInstance().getMBean("org.chaos.mgmt.mbeans.MessageInfo");

		int i = 0;
		while(true) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mbean.setTotalUpMessageCount(i++);
		}
	}
	
	/**
	 * ������Ʈ ���� Ŭ����
	 * @author 9ins
	 *
	 */
	public static class JMXAuthenticatorImpl implements JMXAuthenticator {	
		/**
		 * �����
		 */
		String user;
		
		/**
		 * ��ȣ
		 */
		String passwd;
		
		/**
		 * ������
		 * @param user �����
		 * @param passwd ��ȣ
		 */
		public JMXAuthenticatorImpl(String user, String passwd) {
			this.user = user;
			this.passwd = passwd;
		}
		
		@Override
		public Subject authenticate(Object credentials) {
            if (!(credentials instanceof String[]))
                throw new SecurityException("bad authentication information. please setting login information");
            String[] creds = (String[]) credentials;
            if (creds.length != 2)
                throw new SecurityException("bad authentication information. please setting login information.");

            String inputUser = creds[0];
            String inputPasswd = creds[1];

            if (inputPasswd == null)
            	inputPasswd = "";

            if (!this.user.equals(inputUser))
                throw new SecurityException("unknown user name : " + inputUser);
            if (!this.passwd.equals(inputPasswd))
                throw new SecurityException("incorrect password");

            Set principals = new HashSet();
            principals.add(new JMXPrincipal(inputUser));
            return new Subject(true, principals, Collections.EMPTY_SET, Collections.EMPTY_SET);
		}		
	}
	
	/**
	 * �ֿܼ� ����Ѵ�.
	 * @param sb ��Ʈ�� ����
	 */
	public static void echo(StringBuffer sb) {
		System.out.println(sb);
	}
	
	/**
	 * ����
	 * @param args �Ķ����
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		ChaosAgent agent = new ChaosAgent("kctc", "kctc", 9292, "chaos_agent", true);
		//ChaosAgent server1 = new ChaosAgent("kctc", "kctc", 9999, "chaos_agent", true);
		//ChaosAgent server = new ChaosAgent("kctc", "kctc", "rmi:jmxrmi", "localhost", 9999, "/jndi/rmi://localhost:", true);
		List<MemoryPoolMXBean> list = ManagementFactory.getMemoryPoolMXBeans();
		for(MemoryPoolMXBean mbean : list) {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("pool name : "+mbean.getName()+"\n");
			sBuffer.append("pool type : "+mbean.getType().toString());
			echo(sBuffer);
		}
	}
}
