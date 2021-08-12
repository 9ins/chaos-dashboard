package org.chaostocosmos.chaosdashboard.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * ��ü Ǯ ���� ��ü
 * @author 9ins
 *
 */
public class ObjectPoolInfo extends NotificationBroadcasterSupport implements ObjectPoolInfoMBean {
	/**
	 * ��üǮ�� ��Ż ����
	 */
	private int totalObjectCount;
	
	/**
	 * ���� ��ü ����
	 */
	private int usedObjectCount;
	
	/**
	 * ������ �ʴ� ��ü ����
	 */
	private int unusedObjectCount;
	
	/**
	 * ������
	 */
	private long sequenceNumber = 1;
	
	/**
	 * Ÿ�ӽ�����
	 */
	private long timeStemp;
	
	@Override
	public int getTotalObjectCount() {
		return this.totalObjectCount;
	}

	@Override
	public void setTotalObjectCount(int count) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Total Objet count changed", "totalObjectCount", "int", this.totalObjectCount, count);
		super.sendNotification(n);
		this.totalObjectCount = count;	
		System.out.println("send notification.");
	}

	@Override
	public int getUsedObjectCount() {
		return this.usedObjectCount;
	}

	@Override
	public void setUsedObjectCount(int count) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Used Objet count changed", "usedObjectCount", "int", this.usedObjectCount, count);
		super.sendNotification(n);
		this.usedObjectCount = count;	
	}

	@Override
	public int getUnusedObjectCount() {
		return this.unusedObjectCount;
	}

	@Override
	public void setUnusedObjectCount(int count) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Unused Objet count changed", "unusedObjectCount", "int", this.unusedObjectCount, count);
		super.sendNotification(n);
		this.unusedObjectCount = count;	
	}

	@Override
	public long getTimeStemp() {
		return this.timeStemp;
	}
}
