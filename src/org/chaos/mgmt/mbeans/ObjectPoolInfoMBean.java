package org.chaos.mgmt.mbeans;

public interface ObjectPoolInfoMBean extends InfoMBean {

	/**
	 * 토탈 객체 갯수를 얻는다.
	 * @return 토탈 객체 갯수
	 */
	public int getTotalObjectCount();
	
	/**
	 * 토탈 객체 갯수를 설정한다.
	 * @param count 토탈 객체 갯수
	 */
	public void setTotalObjectCount(int count);
	
	/**
	 * 사용된 객체 갯수를 얻는다.
	 * @return 사용된 객체 갯수
	 */
	public int getUsedObjectCount();

	/**
	 * 사용된 객체 갯수를 설정한다.
	 * @param count 사용된 객체 갯수
	 */
	public void setUsedObjectCount(int count);
	
	/**
	 * 미사용 객체 갯수를 얻는다.
	 * @return 미사용 객체 갯수
	 */
	public int getUnusedObjectCount();
	
	/**
	 * 미사용 객체 갯수를 설정한다.
	 * @param count 미사용 객체 갯수
	 */
	public void setUnusedObjectCount(int count);

	/**
	 * 타임 스템프를 얻는다.
	 * @return 타임 스템프
	 */
	public long getTimeStemp();
}
