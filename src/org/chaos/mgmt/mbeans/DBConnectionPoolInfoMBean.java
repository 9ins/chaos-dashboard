package org.chaos.mgmt.mbeans;

public interface DBConnectionPoolInfoMBean extends InfoMBean {

	/**
	 * 커넥션 풀의 토탈 용량을 얻는다.
	 * @return 커넥션 풀의 토탈 용량
	 */
	public int getTotalPoolCapacity();

	/**
	 * 토탈 용량을 설정한다.
	 * @param capacity 토탈용량
	 */
	public void setTotalPoolCapacity(int capacity);
	
	/**
	 * 사용중인 커넥션 풀 갯수를 얻는다.
	 * @return  사용중인 커넥션 풀 갯수
	 */
	public int getUsedConnectionCount();
	
	/**
	 * 사용중인 커넥션 풀 갯수를 설정한다.
	 * @param count 사용중인 커넥션 풀 갯수
	 */
	public void setUsedConnectionCount(int count);
	
	/**
	 * 미사용중인 커넥션 풀 갯수를 얻는다.
	 * @return 미사용중인 커넥션 풀 갯수
	 */
	public int getUnusedConnectionCount();
	
	/**
	 * 미사용중인 커넥션 풀 갯수를 설정한다.
	 * @param count 미사용중인 커넥션 풀 갯수
	 */
	public void setUnusedConnectionCount(int count);
	
	/**
	 * 타임 스템프를 얻는다.
	 * @return 타임 스템프
	 */
	public long getTimeStemp();
}
