package org.chaos.mgmt.mbeans;

/**
 * 대부분의 객체가 저장되는 메모리 정보 MBean
 * @author 9ins
 *
 */
public interface EdenSpaceMemoryInfoMBean extends InfoMBean {
	
	/**
	 * 메모리풀 명을 얻는다.
	 * @return 메모리풀 명
	 */
	public String getName();
	
	/**
	 * 메모리 타입을 얻는다.
	 * @return 메모리 타입 명
	 */
	public String getType();
	
	/**
	 * 사용량 임계치를 얻는다.
	 * @return 임계치값
	 */
	public long getUsageThreshold();

	/**
	 * 인가된 메모리량을 얻는다.
	 * @return 인가된 메모리량
	 */
	public long getCommited();
	
	/**
	 * 초기화 메모리량을 얻는다.
	 * @return 초기화 메모리량
	 */
	public long getInit();
	
	/**
	 * 최대 메모리량을 얻는다.
	 * @return 최대 메모리량
	 */
	public long getMax();
	
	/**
	 * 사용 메모리량을 얻는다.
	 * @return 사용 메모리량
	 */
	public long getUsed();
}
