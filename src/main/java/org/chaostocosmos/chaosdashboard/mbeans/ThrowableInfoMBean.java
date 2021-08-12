package org.chaostocosmos.chaosdashboard.mbeans;

public interface ThrowableInfoMBean extends InfoMBean {
	/**
	 * ���� ��ü�� ��´�.
	 * @return ���� ��ü
	 */
	public ThrowableInfo getThrowable();
	
	/**
	 * ���� ��ü�� �����Ѵ�.
	 * @param e
	 */	
	public void setThrowable(ThrowableInfo e);

	/**
	 * Ÿ�� �������� ��´�.
	 * @return Ÿ�� ������
	 */
	public long getTimeStemp();
}
