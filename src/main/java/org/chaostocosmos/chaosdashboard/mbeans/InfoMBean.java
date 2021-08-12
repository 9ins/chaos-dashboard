package org.chaostocosmos.chaosdashboard.mbeans;

import java.io.Serializable;

/**
 * ���� MBean �������̽� 
 * @author 9ins
 *
 */
public interface InfoMBean extends Serializable {
	
	/**
	 * Ÿ�ӽ������� ��´�.
	 * @return Ÿ�ӽ�����
	 */
	public long getTimeStemp();
}
