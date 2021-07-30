package org.chaos.mgmt.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * °´Ã¼ Ç® Á¤º¸ °´Ã¼
 * @author 9ins
 *
 */
public class ObjectPoolInfo extends NotificationBroadcasterSupport implements ObjectPoolInfoMBean {
	/**
	 * °´Ã¼Ç®ÀÇ ÅäÅ» °¹¼ö
	 */
	private int totalObjectCount;
	
	/**
	 * »ç¿ëµÈ °´Ã¼ °¹¼ö
	 */
	private int usedObjectCount;
	
	/**
	 * »ç¿ëµÇÁö ¾Ê´Â °´Ã¼ °¹¼ö
	 */
	private int unusedObjectCount;
	
	/**
	 * ½ÃÄö½º
	 */
	private long sequenceNumber = 1;
	
	/**
	 * Å¸ÀÓ½ºÅÛÇÁ
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
