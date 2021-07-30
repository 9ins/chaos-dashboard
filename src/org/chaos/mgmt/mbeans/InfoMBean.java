package org.chaos.mgmt.mbeans;

import java.io.Serializable;

/**
 * 정보 MBean 인터페이스 
 * @author 9ins
 *
 */
public interface InfoMBean extends Serializable {
	
	/**
	 * 타임스템프를 얻는다.
	 * @return 타임스템프
	 */
	public long getTimeStemp();
}
