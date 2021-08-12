package org.chaostocosmos.chaosdashboard.client;

import java.io.Serializable;


/**
 * ����� ���� ���� 
 * @author 9ins
 *
 */
public class ChaosMgmtConfig implements Serializable {
	/**
	 * bean ���̵�
	 */
	public String beanId;
	
	/**
	 * bean �� Ÿ��
	 */
	public String beanType;
	
	/**
	 * bean class �̸�
	 */
	public String className;
	
	/**
	 * bean object �̸�
	 */
	public String objectName;
	
	/**
	 * ����͸� ����
	 */
	public boolean isMonitoring = true;
	
	/**
	 * ����͸� ����
	 */
	public long monitoringInterval = 1000;
	
	/**
	 * ������Ʈ ���� 
	 */
	public ChaosAgentConfig agentConfig;
	
	/**
	 * �׷��� ����
	 */
	public ChaosGraphConfig graphConfig;
	
	/**
	 * Swing ���̺� ����
	 */
	public ChaosTableConfig tableConfig;

	@Override
	public String toString() {
		return "ChaosMgmtConfig [beanId=" + beanId + ", beanType=" + beanType
				+ ", className=" + className + ", objectName=" + objectName
				+ ", isMonitoring=" + isMonitoring + ", monitoringInterval="
				+ monitoringInterval + ", agentConfig=" + agentConfig
				+ ", graphConfig=" + graphConfig + ", tableConfig="
				+ tableConfig + "]";
	}
}
