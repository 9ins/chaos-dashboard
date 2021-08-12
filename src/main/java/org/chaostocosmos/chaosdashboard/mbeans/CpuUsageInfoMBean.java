package org.chaostocosmos.chaosdashboard.mbeans;

public interface CpuUsageInfoMBean {

	/**
	 * ��� �������� ��Ż CPU Ÿ���� ��´�.
	 * @return ��Ż CPU Ÿ��(�и�������)
	 */
	public long getTotalCpuTime();
	
	/**
	 * ��� �������� ��Ż ����� Ÿ���� ��´�.
	 * @return ��Ż ����� Ÿ��(�и�������)
	 */
	public long getTotalUserTime();
	
	/**
	 * ��� �������� ��Ż CPU ��뷮�� ��´�.
	 * @return ��Ż CPU ��뷮(�и�������)
	 */
	public long getTotalSysTime();
	
	/**
	 * ��Ż CPU ������� ��´�.
	 * @return ��Ż CPU �����
	 */
	public int getTotalCpuUsage();
	
	/**
	 * ��Ż ����� ������� ��´�.
	 * @return ��Ż ����� �����
	 */
	public int getTotalUserUsage();
	
	/**
	 * ��Ż �ý��� ������� ��´�.
	 * @return ��Ż �ý��� �����
	 */
	public int getTotalSysUsage();
	
	/**
	 * ��Ż CPU ����� ��հ��� ���Ѵ�.
	 * @return ��Ż CPU ����� ��հ�
	 */
	public int getTotalCpuUsageAvg();
	
	/**
	 * ��Ż ����� ����� ��հ��� ��´�.
	 * @return ��Ż ����� ����� ��հ�
	 */
	public int getTotalUserUsageAvg();
	
	/**
	 * ��Ż �ý��� ����� ��հ��� ��´�.
	 * @return ��Ż �ý��� ����� ��հ�
	 */
	public int getTotalSysUsageAvg();
	
	/**
	 * Ÿ�� �������� ��´�.
	 * @return Ÿ�� ������
	 */
	public long getTimeStemp();
}
