package org.chaostocosmos.chaosdashboard.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * DBConnectionPoolInfo ���� ��ü
 * @author 9ins
 *
 */
public class DBConnectionPoolInfo extends NotificationBroadcasterSupport implements DBConnectionPoolInfoMBean {
	/**
	 * ��Ż Ǯ �뷮
	 */
	private int totalPoolCapacity;
	
	/**
	 * ������� Ŀ�ؼ� ��
	 */
	private int usedConnectionCount;
	
	/**
	 * �̻�� Ŀ�ؼ� ��
	 */
	private int unusedConnectionCount;
	
	/**
	 * ������
	 */
	private long sequenceNumber = 1;

	/**
	 * Ÿ�ӽ�����
	 */
	private long timeStemp;
	
	@Override
	public int getTotalPoolCapacity() {
		return this.totalPoolCapacity;
	}

	@Override
	public void setTotalPoolCapacity(int capacity) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Total pool capacity changed", "totalPoolCapacity", "int", this.totalPoolCapacity, capacity);
		super.sendNotification(n);
		this.totalPoolCapacity = capacity;		
	}

	@Override
	public int getUsedConnectionCount() {
		return this.usedConnectionCount;
	}

	@Override
	public void setUsedConnectionCount(int count) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Used connection count changed", "usedConnectionCount", "int", this.usedConnectionCount, count);
		super.sendNotification(n);
		this.usedConnectionCount = count;	
	}

	@Override
	public int getUnusedConnectionCount() {
		return this.unusedConnectionCount;
	}

	@Override
	public void setUnusedConnectionCount(int count) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Unused connection count changed", "unusedConnectionCount", "int", this.unusedConnectionCount, count);
		super.sendNotification(n);
		this.unusedConnectionCount = count;
	}

	@Override
	public long getTimeStemp() {
		return this.timeStemp;
	}

}
