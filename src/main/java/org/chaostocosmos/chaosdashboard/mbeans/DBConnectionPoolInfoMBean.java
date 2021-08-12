package org.chaostocosmos.chaosdashboard.mbeans;

public interface DBConnectionPoolInfoMBean extends InfoMBean {

	/**
	 * Ŀ�ؼ� Ǯ�� ��Ż �뷮�� ��´�.
	 * @return Ŀ�ؼ� Ǯ�� ��Ż �뷮
	 */
	public int getTotalPoolCapacity();

	/**
	 * ��Ż �뷮�� �����Ѵ�.
	 * @param capacity ��Ż�뷮
	 */
	public void setTotalPoolCapacity(int capacity);
	
	/**
	 * ������� Ŀ�ؼ� Ǯ ������ ��´�.
	 * @return  ������� Ŀ�ؼ� Ǯ ����
	 */
	public int getUsedConnectionCount();
	
	/**
	 * ������� Ŀ�ؼ� Ǯ ������ �����Ѵ�.
	 * @param count ������� Ŀ�ؼ� Ǯ ����
	 */
	public void setUsedConnectionCount(int count);
	
	/**
	 * �̻������ Ŀ�ؼ� Ǯ ������ ��´�.
	 * @return �̻������ Ŀ�ؼ� Ǯ ����
	 */
	public int getUnusedConnectionCount();
	
	/**
	 * �̻������ Ŀ�ؼ� Ǯ ������ �����Ѵ�.
	 * @param count �̻������ Ŀ�ؼ� Ǯ ����
	 */
	public void setUnusedConnectionCount(int count);
	
	/**
	 * Ÿ�� �������� ��´�.
	 * @return Ÿ�� ������
	 */
	public long getTimeStemp();
}
