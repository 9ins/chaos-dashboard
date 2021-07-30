package org.chaos.mgmt.client;

import java.io.Serializable;


/**
 * 모니터 설정 정보 
 * @author 9ins
 *
 */
public class ChaosMgmtConfig implements Serializable {
	/**
	 * bean 아이디
	 */
	public String beanId;
	
	/**
	 * bean 빈 타입
	 */
	public String beanType;
	
	/**
	 * bean class 이름
	 */
	public String className;
	
	/**
	 * bean object 이름
	 */
	public String objectName;
	
	/**
	 * 모니터링 여부
	 */
	public boolean isMonitoring = true;
	
	/**
	 * 모니터링 간격
	 */
	public long monitoringInterval = 1000;
	
	/**
	 * 에이전트 설정 
	 */
	public ChaosAgentConfig agentConfig;
	
	/**
	 * 그래프 설정
	 */
	public ChaosGraphConfig graphConfig;
	
	/**
	 * Swing 테이블 설정
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
