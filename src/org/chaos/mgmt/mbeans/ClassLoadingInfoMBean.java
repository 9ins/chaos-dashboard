package org.chaos.mgmt.mbeans;
/**
 * JVM에 로딩된 클래스 정보 빈
 * @author 9ins
 *
 */
public interface ClassLoadingInfoMBean extends InfoMBean {

	/**
	 * 총 로딩된 클래스 수를 얻는다.
	 * @return 총 로딩된 클래스 수
	 */
	public long getTotalLoadedClassCount();
	
	/**
	 * 현재 로딩된 클래스 수를 얻는다.
	 * @return 현재 로딩된 클래스 수
	 */
	public long getLoadedClassCount();
	
	/**
	 * 현재 로딩되지 않은 클래스 수를 얻는다.
	 * @return 현재 로딩되지 않은 클래스 수
	 */
	public long getUnloadedClassCount();
}
