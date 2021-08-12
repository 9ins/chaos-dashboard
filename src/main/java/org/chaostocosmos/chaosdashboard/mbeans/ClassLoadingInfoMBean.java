package org.chaostocosmos.chaosdashboard.mbeans;
/**
 * JVM�� �ε��� Ŭ���� ���� ��
 * @author 9ins
 *
 */
public interface ClassLoadingInfoMBean extends InfoMBean {

	/**
	 * �� �ε��� Ŭ���� ���� ��´�.
	 * @return �� �ε��� Ŭ���� ��
	 */
	public long getTotalLoadedClassCount();
	
	/**
	 * ���� �ε��� Ŭ���� ���� ��´�.
	 * @return ���� �ε��� Ŭ���� ��
	 */
	public long getLoadedClassCount();
	
	/**
	 * ���� �ε����� ���� Ŭ���� ���� ��´�.
	 * @return ���� �ε����� ���� Ŭ���� ��
	 */
	public long getUnloadedClassCount();
}
