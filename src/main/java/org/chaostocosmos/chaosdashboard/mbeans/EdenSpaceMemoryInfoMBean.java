package org.chaostocosmos.chaosdashboard.mbeans;

/**
 * ��κ��� ��ü�� ����Ǵ� �޸� ���� MBean
 * @author 9ins
 *
 */
public interface EdenSpaceMemoryInfoMBean extends InfoMBean {
	
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
