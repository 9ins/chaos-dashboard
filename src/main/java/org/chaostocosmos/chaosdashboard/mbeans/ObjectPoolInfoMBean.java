package org.chaostocosmos.chaosdashboard.mbeans;

public interface ObjectPoolInfoMBean extends InfoMBean {

	/**
	 * ��Ż ��ü ������ ��´�.
	 * @return ��Ż ��ü ����
	 */
	public int getTotalObjectCount();
	
	/**
	 * ��Ż ��ü ������ �����Ѵ�.
	 * @param count ��Ż ��ü ����
	 */
	public void setTotalObjectCount(int count);
	
	/**
	 * ���� ��ü ������ ��´�.
	 * @return ���� ��ü ����
	 */
	public int getUsedObjectCount();

	/**
	 * ���� ��ü ������ �����Ѵ�.
	 * @param count ���� ��ü ����
	 */
	public void setUsedObjectCount(int count);
	
	/**
	 * �̻�� ��ü ������ ��´�.
	 * @return �̻�� ��ü ����
	 */
	public int getUnusedObjectCount();
	
	/**
	 * �̻�� ��ü ������ �����Ѵ�.
	 * @param count �̻�� ��ü ����
	 */
	public void setUnusedObjectCount(int count);

	/**
	 * Ÿ�� �������� ��´�.
	 * @return Ÿ�� ������
	 */
	public long getTimeStemp();
}
