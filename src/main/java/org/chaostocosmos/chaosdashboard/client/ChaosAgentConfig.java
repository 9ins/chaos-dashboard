package org.chaostocosmos.chaosdashboard.client;

import java.io.Serializable;

public class ChaosAgentConfig implements Serializable {
	/**
	 * JMX ���� ���
	 */
	public String serviceUrl;
	
	/**
	 * ������ ����
	 */
	public long retryInterval;

	@Override
	public String toString() {
		return "ChaosAgentConfig [serviceUrl=" + serviceUrl
				+ ", retryInterval=" + retryInterval + "]";
	}
}
