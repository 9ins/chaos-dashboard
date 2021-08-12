package org.chaostocosmos.chaosdashboard.mbeans;

public interface FileBufferInfoMBean extends InfoMBean {

	/**
	 * �ֱ��Ʒ� �����۹� ������ ��´�.
	 * @return ���Ϲ��� ����
	 */
	public int getCycleFBCount();
	
	/**
	 * �ֱ��Ʒ� �����۹� ������ �����Ѵ�.
	 * @param count �����۹� ����
	 */
	public void setCycleFBCount(int count);
	
	/**
	 * ��Ÿ�ֱ� �����۹� ������ ��´�.
	 * @return �����۹� ����
	 */
	public int getEtcFBCount();
	
	/**
	 * ��Ÿ�ֱ� �����۹� ������ �����Ѵ�.
	 * @param count �����۹� ����
	 */
	public void setEtcFBCount(int count);
	
	/**
	 * �ֱ��Ʒ� �����۹� ����� ��´�.
	 * @return �����۹� ������
	 */
	public long getCycleFBFileSize();
	
	/**
	 * �ֱ��Ʒ� �����۹� ���� ����� �����Ѵ�.
	 * @param size �����۹� ���� ������
	 */
	public void setCycleFBFileSize(long size);
	
	/**
	 * ��Ÿ�ֱ� �����۹� ����� ��´�.
	 * @return �����۹� ������
	 */
	public long getEtcFBFileSize();
	
	/**
	 * ��Ÿ�ֱ� �����۹� ����� �����Ѵ�.
	 * @param size �����۹� ������
	 */
	public void setEtcFBFileSize(long size);
	
	/**
	 * �ֱ��Ʒ� ���Ϲ��� ���� ����� ��´�.
	 * @return �ֱ��Ʒ� ���Ϲ��� ���� ���
	 */
	public long getCycleFBCountAvg();
	
	/**
	 * �ֱ��Ʒ� ���Ϲ��� ���� ����� �����Ѵ�.
	 * @param countAvg �ֱ��Ʒ� ���Ϲ��� ���� ���
	 */
	public void setCycleFBCountAvg(long countAvg);
	
	/**
	 * ��Ÿ�Ʒ� ���Ϲ��� ���� ����� ��´�.
	 * @return ��Ÿ�Ʒ� ���Ϲ��� ���� ���
	 */
	public long getEtcFBCountAvg();
	
	/**
	 * ��Ÿ�Ʒ� ���Ϲ��� ���� ����� �����Ѵ�.
	 * @param countAvg ��Ÿ�Ʒ� ���Ϲ��� ���� ���
	 */
	public void setEtcFBCountAvg(long countAvg);
	
	/**
	 * �ֱ��Ʒ� ���Ϲ��� ����ũ�� ����� ��´�.
	 * @return �ֱ��Ʒ� ���Ϲ��� ����ũ�� ���
	 */
	public long getCycleFBFileSizeAvg();
	
	/**
	 * �ֱ��Ʒ� ���Ϲ��� ����ũ�� ����� �����Ѵ�.
	 * @param sizeAvg �ֱ��Ʒ� ���Ϲ��� ����ũ�� ���
	 */
	public void setCycleFBFilesizeAvg(long sizeAvg);
	
	/**
	 * ��Ÿ�Ʒ� ���Ϲ��� ����ũ�� ����� ��´�.
	 * @return ��Ÿ�Ʒ� ���Ϲ��� ����ũ�� ���
	 */
	public long getEtcFBFileSizeAvg();
	
	/**
	 * ��Ÿ�Ʒ� ���Ϲ��� ����ũ�� ����� �����Ѵ�.
	 * @param sizeAvg ��Ÿ�Ʒ� ���Ϲ��� ����ũ�� ����� �����Ѵ�.
	 */
	public void setEtcFBFileSizeAvg(long sizeAvg);

	/**
	 * Ÿ�� �������� ��´�.
	 * @return Ÿ�� ������
	 */
	public long getTimeStemp();
}
