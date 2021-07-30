package org.chaos.mgmt.client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import javax.security.auth.Subject;
import javax.swing.JOptionPane;

import org.chaos.mgmt.agent.ChaosAgent;
import org.chaos.mgmt.client.ui.WaitingDialog;
import org.chaos.mgmt.common.Logger;
import org.chaos.mgmt.common.PropertyHandler;
import org.chaos.mgmt.common.UtilBox;
import org.chaos.mgmt.mbeans.ThrowableInfo;

/**
 * 모니터 클라이언트
 * @author 9ins
 *
 */
public class ChaosMgmtClient {
	/**
	 * 모니터 클라이언트 인스턴스
	 */
	public static ChaosMgmtClient client = null;
	
	/**
	 * 프로퍼티 핸들러
	 */
	PropertyHandler propertyHandler;
	
	/**
	 * 모니터 클라이언트 프레임
	 */
	ChaosMgmtClientFrame monitorFrm;
	
	/**
	 * 사용자명
	 */
	public final String username;
	
	/**
	 * 패스워드
	 */
	public final String passwd;
	
	/**
	 * agent protocol
	 */
	public final String protocol;
	
	/**
	 * agent host name
	 */
	public final String host;
	
	/**
	 * agent port
	 */
	public final int port;
	
	/**
	 * agent 서비스 url
	 */
	public final String serviceUrl;
	
	/**
	 * 재접속 간격
	 */
	public final long interval;
	
	/**
	 * 설정 XML경로
	 */
	private static String xmlDir;
	
	/**
	 * 모니터 설정 빈 해쉬테이블
	 */
	private static Hashtable<String, ChaosMgmtConfig> configHash;
	
	/**
	 * 세션 목록 해쉬테이블
	 */
	private static Hashtable<String, ChaosMgmtClientSession> sessionHash;
	
	/**
	 * JMX 제접속 객체
	 */
	private static JMXReconnector reconnector;
	
	/**
	 * 생성자
	 * @param propertyPath 프로퍼티 경로
	 */
	public ChaosMgmtClient(String propertyPath) {
		sessionHash = new Hashtable<String, ChaosMgmtClientSession>();
		this.propertyHandler = new PropertyHandler(propertyPath);
		initConfig();
		xmlDir = PropertyHandler.getProperty("client.properties", "CLIENT_CONFIG_XML_PATH");
		this.username = PropertyHandler.getProperty("client.properties", "AGENT_USER");
		this.passwd = PropertyHandler.getProperty("client.properties", "AGENT_PASSWD");
		this.protocol = PropertyHandler.getProperty("client.properties", "AGENT_PROTOCOL");
		this.host = PropertyHandler.getProperty("client.properties", "AGENT_HOST");
		this.port = Integer.parseInt(PropertyHandler.getProperty("client.properties", "AGENT_PORT"));
		this.serviceUrl = PropertyHandler.getProperty("client.properties", "AGENT_SERVICE_URL");
		this.interval = Long.parseLong(PropertyHandler.getProperty("client.properties", "CLIENT_RECONNECT_INTERVAL"));		
		this.monitorFrm = new ChaosMgmtClientFrame(PropertyHandler.getProperty("client.properties", "CLIENT_TITLE")+" - "+this.host+":"+this.port, this);
		this.monitorFrm.setVisible(true);
		client = this;
		connect();
	}
	
	/**
	 * 모니터 설정정보를 초기화 한다.
	 */
	private void initConfig() {
	}
	
	/**
	 * 모니터 설정정보를 얻는다.
	 * @param beanId 빈 아이디
	 * @return 모니터 설정 정보
	 */
	public static ChaosMgmtConfig getMonitorConfigInfoBean(String beanId) {
		return configHash.get(beanId);
	}
	
	/**
	 * 모니터 설정 해쉬테이블을 얻는다.
	 * @return 모니터 설정 해쉬테이블
	 */
	public static Hashtable<String, ChaosMgmtConfig> getMonitorConfigBeans() {
		try {
			if(configHash == null)
				configHash = XMLChaosMgmtConfigFactory.getChaosMgmtConfig(xmlDir);
		} catch(Exception e) {
			Logger.getInstance().println(e, ChaosMgmtClient.class);
		}
		return configHash;
	}
	
	/**
	 * 에이전트에 접속한다.
	 * @throws IOException 
	 */
	public void connect() {
		if(reconnector == null) {
			reconnector = new JMXReconnector(this.protocol, this.host, this.port, this.serviceUrl);
			reconnector.start();
		} else {
			JOptionPane.showMessageDialog(this.monitorFrm, "Already connected to monitor server...", "Alert!!!", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * 에이전트로 부터 접속을 끊는다.	
	 */
	public void disconnectAll() {
		try {
			if(this.sessionHash.size() > 0) {
				for(ChaosMgmtClientSession session : this.sessionHash.values())
					session.close();
			}
		} catch(Exception e) {
			Logger.getInstance().println(e, ChaosMgmtClient.class);
		}
	}
	
	/**
	 * 에이전트로 부터 해당 세션을 끊는다.
	 * @param sessionId 세션 아이디(키)
	 */
	public void diconnect(String sessionId) {
		try {
			this.sessionHash.get(sessionId).close();
		} catch(Exception e) {
			Logger.getInstance().println(e, ChaosMgmtClient.class);
		}
	}
	
	/**
	 * 모니터링 클라이언트를 종료한다.
	 */
	public void exit() {
		disconnectAll();
		this.monitorFrm.close();
		System.exit(0);
	}
	
	/**
	 * 콘솔에 출력한다.
	 * @param sb 스트링 버퍼 객체
	 */
	public static void echo(StringBuffer sb) {
		System.out.println(sb);
	}
	
	/**
	 * JMX 재접속 커넥터
	 * @author 9ins
	 *
	 */
	private class JMXReconnector extends Thread {
		/**
		 * 프로토콜
		 */
		String protocol;
		/**
		 * 호스트
		 */
		String host;
		/**
		 * 포트
		 */
		int port;
		/**
		 * 서비스 경로
		 */
		String serviceUrl;
		/**
		 * 생성자
		 * @param protocol 프로토콜
		 * @param host 호스트
		 * @param port 포트
		 * @param serviceUrl 서비스 경로
		 */
		public JMXReconnector(String protocol, String host, int port, String serviceUrl) {
			this.protocol = protocol;
			this.host = host;
			this.port = port;
			this.serviceUrl = serviceUrl;
		}
		
		/**
		 * 재접속 쓰레드 시작
		 */
		public void run() {
			while(true) {
				try {
					sleep(interval);
					JMXServiceURL jmxServiceUrl = new JMXServiceURL(this.protocol, this.host, this.port, this.serviceUrl);
					Map env = new HashMap();
					env.put(JMXConnector.CREDENTIALS, new String[]{username, passwd});
					ChaosMgmtClientSession session = new ChaosMgmtClientSession(JMXConnectorFactory.connect(jmxServiceUrl, env), client, monitorFrm);
					String url = jmxServiceUrl.getURLPath();
					sessionHash.put(url, session);
				} catch (Exception e) {
					Logger.getInstance().println(e, ChaosMgmtClient.class);
				}
			}
		}
	}
	
	/**
	 * 모니터 클라이언트 메인
	 * @param args 프로그램 파라미터
	 */
	public static void main(String[] args) {
		if(args.length == 1) {
			ChaosMgmtClient client = new ChaosMgmtClient(args[0]);
		} else {
			ChaosMgmtClient client = new ChaosMgmtClient("./prop/");
		}
	}
}
