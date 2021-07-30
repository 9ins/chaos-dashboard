package org.chaos.mgmt.mbeans;

public interface MessageInfoMBean extends InfoMBean  {
	
	/**
	 * 상향 메시지 총 갯수를 얻는다.
	 * @return 상향 메시지 총 갯수
	 */
	public long getTotalUpMessageCount();
	
	/**
	 * 상향 메시지 총 갯수를 설정한다.
	 * @param count 상향 메시지 총 갯수
	 */
	public void setTotalUpMessageCount(long count);
	
	/**
	 * 하향 메시지 총 갯수를 얻는다.
	 * @return 하향 메시지 총 갯수
	 */
	public long getTotalDownMessageCount();
	
	/**
	 * 하향 메시지 총 갯수를 설정한다.
	 * @param count 하향 메시지 총 갯수
	 */
	public void setTotalDownMessageCount(long count);
	
	/**
	 * 상향 메시지 초당 건수를 얻는다.
	 * @return 상향 메시지 초당 건수
	 */
	public int getUpMessageInSecond();
	
	/**
	 * 상향 메시지 초당 건수를 설정한다.
	 * @param second 상향 메시지 초당 건수
	 */
	public void setUpMessageInSecond(int second);
	
	/**
	 * 하향 메시지 초당 건수를 얻는다.
	 * @return 하향 메시지 초당 건수
	 */
	public int getDownMessageInSecond();

	/**
	 * 하향 메시지 초당 건수를 설정한다.
	 * @param second 하향 메시지 초당 건수
	 */
	public void setDownMessageInSecond(int second);

	/**
	 * 타임 스템프를 얻는다.
	 * @return 타임 스템프
	 */
	public long getTimeStemp();
}
