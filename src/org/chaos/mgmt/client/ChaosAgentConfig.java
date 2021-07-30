package org.chaos.mgmt.client;

import java.io.Serializable;

public class ChaosAgentConfig implements Serializable {
	/**
	 * JMX 서비스 경로
	 */
	public String serviceUrl;
	
	/**
	 * 재접속 간격
	 */
	public long retryInterval;

	@Override
	public String toString() {
		return "ChaosAgentConfig [serviceUrl=" + serviceUrl
				+ ", retryInterval=" + retryInterval + "]";
	}
}
