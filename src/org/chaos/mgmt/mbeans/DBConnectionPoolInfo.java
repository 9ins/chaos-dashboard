package org.chaos.mgmt.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * DBConnectionPoolInfo 정보 객체
 * @author 9ins
 *
 */
public class DBConnectionPoolInfo extends NotificationBroadcasterSupport implements DBConnectionPoolInfoMBean {
	/**
	 * 토탈 풀 용량
	 */
	private int totalPoolCapacity;
	
	/**
	 * 사용중인 커넥션 수
	 */
	private int usedConnectionCount;
	
	/**
	 * 미사용 커넥션 수
	 */
	private int unusedConnectionCount;
	
	/**
	 * 시퀀스
	 */
	private long sequenceNumber = 1;

	/**
	 * 타임스템프
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
