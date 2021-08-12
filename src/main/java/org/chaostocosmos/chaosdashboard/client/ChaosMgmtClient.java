package org.chaostocosmos.chaosdashboard.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.JOptionPane;

import org.chaostocosmos.chaosdashboard.common.Logger;
import org.chaostocosmos.chaosdashboard.common.PropertyHandler;

/**
 * ����� Ŭ���̾�Ʈ
 * @author 9ins
 *
 */
public class ChaosMgmtClient {
	/**
	 * ����� Ŭ���̾�Ʈ �ν��Ͻ�
	 */
	public static ChaosMgmtClient client = null;
	
	/**
	 * ������Ƽ �ڵ鷯
	 */
	PropertyHandler propertyHandler;
	
	/**
	 * ����� Ŭ���̾�Ʈ ������
	 */
	ChaosMgmtClientFrame monitorFrm;
	
	/**
	 * ����ڸ�
	 */
	public final String username;
	
	/**
	 * �н�����
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
	 * agent ���� url
	 */
	public final String serviceUrl;
	
	/**
	 * ������ ����
	 */
	public final long interval;
	
	/**
	 * ���� XML���
	 */
	private static String xmlDir;
	
	/**
	 * ����� ���� �� �ؽ����̺�
	 */
	private static Hashtable<String, ChaosMgmtConfig> configHash;
	
	/**
	 * ���� ��� �ؽ����̺�
	 */
	private static Hashtable<String, ChaosMgmtClientSession> sessionHash;
	
	/**
	 * JMX ������ ��ü
	 */
	private static JMXReconnector reconnector;
	
	/**
	 * ������
	 * @param propertyPath ������Ƽ ���
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
	 * ����� ���������� �ʱ�ȭ �Ѵ�.
	 */
	private void initConfig() {
	}
	
	/**
	 * ����� ���������� ��´�.
	 * @param beanId �� ���̵�
	 * @return ����� ���� ����
	 */
	public static ChaosMgmtConfig getMonitorConfigInfoBean(String beanId) {
		return configHash.get(beanId);
	}
	
	/**
	 * ����� ���� �ؽ����̺��� ��´�.
	 * @return ����� ���� �ؽ����̺�
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
	 * ������Ʈ�� �����Ѵ�.
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
	 * ������Ʈ�� ���� ������ ���´�.	
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
	 * ������Ʈ�� ���� �ش� ������ ���´�.
	 * @param sessionId ���� ���̵�(Ű)
	 */
	public void diconnect(String sessionId) {
		try {
			this.sessionHash.get(sessionId).close();
		} catch(Exception e) {
			Logger.getInstance().println(e, ChaosMgmtClient.class);
		}
	}
	
	/**
	 * ����͸� Ŭ���̾�Ʈ�� �����Ѵ�.
	 */
	public void exit() {
		disconnectAll();
		this.monitorFrm.close();
		System.exit(0);
	}
	
	/**
	 * �ֿܼ� ����Ѵ�.
	 * @param sb ��Ʈ�� ���� ��ü
	 */
	public static void echo(StringBuffer sb) {
		System.out.println(sb);
	}
	
	/**
	 * JMX ������ Ŀ����
	 * @author 9ins
	 *
	 */
	private class JMXReconnector extends Thread {
		/**
		 * ��������
		 */
		String protocol;
		/**
		 * ȣ��Ʈ
		 */
		String host;
		/**
		 * ��Ʈ
		 */
		int port;
		/**
		 * ���� ���
		 */
		String serviceUrl;
		/**
		 * ������
		 * @param protocol ��������
		 * @param host ȣ��Ʈ
		 * @param port ��Ʈ
		 * @param serviceUrl ���� ���
		 */
		public JMXReconnector(String protocol, String host, int port, String serviceUrl) {
			this.protocol = protocol;
			this.host = host;
			this.port = port;
			this.serviceUrl = serviceUrl;
		}
		
		/**
		 * ������ ������ ����
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
	 * ����� Ŭ���̾�Ʈ ����
	 * @param args ���α׷� �Ķ����
	 */
	public static void main(String[] args) {
		if(args.length == 1) {
			ChaosMgmtClient client = new ChaosMgmtClient(args[0]);
		}
	}
}
