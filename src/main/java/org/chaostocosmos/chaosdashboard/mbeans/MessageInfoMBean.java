package org.chaostocosmos.chaosdashboard.mbeans;

public interface MessageInfoMBean extends InfoMBean  {
	
	/**
	 * ���� �޽��� �� ������ ��´�.
	 * @return ���� �޽��� �� ����
	 */
	public long getTotalUpMessageCount();
	
	/**
	 * ���� �޽��� �� ������ �����Ѵ�.
	 * @param count ���� �޽��� �� ����
	 */
	public void setTotalUpMessageCount(long count);
	
	/**
	 * ���� �޽��� �� ������ ��´�.
	 * @return ���� �޽��� �� ����
	 */
	public long getTotalDownMessageCount();
	
	/**
	 * ���� �޽��� �� ������ �����Ѵ�.
	 * @param count ���� �޽��� �� ����
	 */
	public void setTotalDownMessageCount(long count);
	
	/**
	 * ���� �޽��� �ʴ� �Ǽ��� ��´�.
	 * @return ���� �޽��� �ʴ� �Ǽ�
	 */
	public int getUpMessageInSecond();
	
	/**
	 * ���� �޽��� �ʴ� �Ǽ��� �����Ѵ�.
	 * @param second ���� �޽��� �ʴ� �Ǽ�
	 */
	public void setUpMessageInSecond(int second);
	
	/**
	 * ���� �޽��� �ʴ� �Ǽ��� ��´�.
	 * @return ���� �޽��� �ʴ� �Ǽ�
	 */
	public int getDownMessageInSecond();

	/**
	 * ���� �޽��� �ʴ� �Ǽ��� �����Ѵ�.
	 * @param second ���� �޽��� �ʴ� �Ǽ�
	 */
	public void setDownMessageInSecond(int second);

	/**
	 * Ÿ�� �������� ��´�.
	 * @return Ÿ�� ������
	 */
	public long getTimeStemp();
}
