package org.chaos.mgmt.mbeans;

public interface ThrowableInfoMBean extends InfoMBean {
	/**
	 * 예외 객체를 얻는다.
	 * @return 예외 객체
	 */
	public ThrowableInfo getThrowable();
	
	/**
	 * 예외 객체를 설정한다.
	 * @param e
	 */	
	public void setThrowable(ThrowableInfo e);

	/**
	 * 타임 스템프를 얻는다.
	 * @return 타임 스템프
	 */
	public long getTimeStemp();
}
