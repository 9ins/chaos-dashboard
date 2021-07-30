package org.chaos.mgmt.mbeans;

public interface CpuUsageInfoMBean {

	/**
	 * 모든 쓰레드의 토탈 CPU 타임을 얻는다.
	 * @return 토탈 CPU 타임(밀리세컨즈)
	 */
	public long getTotalCpuTime();
	
	/**
	 * 모든 쓰레드의 토탈 사용자 타임을 얻는다.
	 * @return 토탈 사용자 타임(밀리세컨즈)
	 */
	public long getTotalUserTime();
	
	/**
	 * 모든 쓰레드의 토탈 CPU 사용량을 얻는다.
	 * @return 토탈 CPU 사용량(밀리세컨즈)
	 */
	public long getTotalSysTime();
	
	/**
	 * 토탈 CPU 사용율을 얻는다.
	 * @return 토탈 CPU 사용율
	 */
	public int getTotalCpuUsage();
	
	/**
	 * 토탈 사용자 사용율을 얻는다.
	 * @return 토탈 사용자 사용율
	 */
	public int getTotalUserUsage();
	
	/**
	 * 토탈 시스템 사용율을 얻는다.
	 * @return 토탈 시스템 사용율
	 */
	public int getTotalSysUsage();
	
	/**
	 * 토탈 CPU 사용율 평균값을 구한다.
	 * @return 토탈 CPU 사용율 평균값
	 */
	public int getTotalCpuUsageAvg();
	
	/**
	 * 토탈 사용자 사용율 평균값을 얻는다.
	 * @return 토탈 사용자 사용율 평균값
	 */
	public int getTotalUserUsageAvg();
	
	/**
	 * 토탈 시스템 사용율 평균값을 얻는다.
	 * @return 토탈 시스템 사용율 평균값
	 */
	public int getTotalSysUsageAvg();
	
	/**
	 * 타임 스템프를 얻는다.
	 * @return 타임 스템프
	 */
	public long getTimeStemp();
}
