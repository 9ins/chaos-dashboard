package org.chaostocosmos.chaosdashboard.mbeans;

public interface PermGenMemoryInfoMBean extends InfoMBean {
	
	/**
	 * �޸�Ǯ ���� ��´�.
	 * @return �޸�Ǯ ��
	 */
	public String getName();
	
	/**
	 * �޸� Ÿ���� ��´�.
	 * @return �޸� Ÿ�� ��
	 */
	public String getType();
	
	/**
	 * ��뷮 �Ӱ�ġ�� ��´�.
	 * @return �Ӱ�ġ��
	 */
	public long getUsageThreshold();

	/**
	 * �ΰ��� �޸𸮷��� ��´�.
	 * @return �ΰ��� �޸𸮷�
	 */
	public long getCommited();
	
	/**
	 * �ʱ�ȭ �޸𸮷��� ��´�.
	 * @return �ʱ�ȭ �޸𸮷�
	 */
	public long getInit();
	
	/**
	 * �ִ� �޸𸮷��� ��´�.
	 * @return �ִ� �޸𸮷�
	 */
	public long getMax();
	
	/**
	 * ��� �޸𸮷��� ��´�.
	 * @return ��� �޸𸮷�
	 */
	public long getUsed();	
}
