package org.chaos.mgmt.agent;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXPrincipal;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;
import javax.naming.Context;
import javax.security.auth.Subject;

import org.chaos.mgmt.mbeans.FileBufferInfo;
import org.chaos.mgmt.mbeans.InfoMBean;
import org.chaos.mgmt.mbeans.MessageInfo;
import org.chaos.mgmt.mbeans.ObjectPoolInfo;
import org.chaos.mgmt.mbeans.ObjectPoolInfoMBean;


/**
 * 서버 모니터 클래스
 * @author 9ins
 *
 */
public class ChaosAgent { //extends Thread {
	/**
	 * RMI 레지스트리
	 */
	Registry rmiReg;
	
	/**
	 * MBean 서버
	 */
	MBeanServer mbs;
	
	/**
	 * JMX 커넥터 서버
	 */
	JMXConnectorServer connectorServer;
	
	/**
	 * 서비스 URL 객체
	 */
	JMXServiceURL serviceUrl;
	
	/**
	 * 사용자
	 */
	String user;
	
	/**
	 * 패스워드
	 */
	String passwd;
	
	/**
	 * 종료여부
	 */
	boolean isDone;
	
	/**
	 * 생성자
	 * @param username 사용자명
	 * @param passwd 패스워드
	 * @param serviceUrl 서비스 URL
	 * @param autoStart 자동 시작 여부
	 * @throws MalformedURLException
	 */
	public ChaosAgent(String username, String passwd, String serviceUrl, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL(serviceUrl), autoStart);
	}
	
	/**
	 * 생성자
	 * @param username 사용자명
	 * @param passwd 패스워드
	 * @param port 서버포트
	 * @param autoStart 자동 시작 여부
	 * @throws MalformedURLException 
	 */
	public ChaosAgent(String username, String passwd, int port, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL("rmi", "localhost", port, "chaos_agent"), true);
	}
	
	/**
	 * 생성자
	 * @param username 사용자명
	 * @param passwd 패스워드
	 * @param port 포트
	 * @param serviceName 서비스명
	 * @param autoStart 자동 시작 여부
	 * @throws MalformedURLException
	 */
	public ChaosAgent(String username, String passwd, int port, String serviceName, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL("rmi", "localhost", port, "/jndi/rmi://localhost:"+port+"/"+serviceName), true);
	}
	
	/**
	 * 생성자
	 * @param username 사용자명
	 * @param passwd 패스워드
	 * @param protocol 프로토콜
	 * @param host 호스트
	 * @param port 포트 
	 * @param autoStart 작동 시작 여부
	 * @throws MalformedURLException 
	 */
	public ChaosAgent(String username, String passwd, String protocol, String host, int port, String urlPath, boolean autoStart) throws MalformedURLException {
		this(username, passwd, new JMXServiceURL(protocol, host, port, urlPath), true);
	}
	
	/**
	 * 생성자
	 * @param username 사용자명
	 * @param passwd 패스워드
	 * @param serviceUrl 서비스 URL
	 * @param autoStart 자동 시작 여부
	 */
	public ChaosAgent(String username, String passwd, JMXServiceURL serviceUrl, boolean autoStart) {
		this.serviceUrl = serviceUrl;
		this.user = username;
		this.passwd = passwd;
		if(autoStart)
			startMonitorServer();
	}
	
	/**
	 * 모니터 서버를 시작한다.
	 */
	public void startMonitorServer() {
		//start();
		run();
		this.isDone = false;
	}
	
	/**
	 * 모니터 서버를 중지한다.
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
	 * 정보 MBean을 등록한다.
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
	 * 에이전트 인증 클래스
	 * @author 9ins
	 *
	 */
	public static class JMXAuthenticatorImpl implements JMXAuthenticator {	
		/**
		 * 사용자
		 */
		String user;
		
		/**
		 * 암호
		 */
		String passwd;
		
		/**
		 * 생성자
		 * @param user 사용자
		 * @param passwd 암호
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
	 * 콘솔에 출력한다.
	 * @param sb 스트링 버퍼
	 */
	public static void echo(StringBuffer sb) {
		System.out.println(sb);
	}
	
	/**
	 * 메인
	 * @param args 파라미터
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
