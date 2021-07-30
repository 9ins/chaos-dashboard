package org.chaos.mgmt.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * 메시지 정보 객체
 * @author 9ins
 *
 */
public class MessageInfo extends NotificationBroadcasterSupport implements MessageInfoMBean {
	/**
	 * 서버 시작 후 상향 총 메시지 갯수
	 */
	private long totalUpMessageCount;
	
	/**
	 * 서버 시작 후 하향 총 메시지 갯수
	 */
	private long totalDownMessageCount;
	
	/**
	 * 상향 메시지 초당 전송 갯수
	 */
	private int upMessageInSecond;
	
	/**
	 * 하향 메시지 초당 전송 갯수
	 */
	private int downMessageInSecond;
	
	/**
	 * 시퀀스
	 */
	private long sequenceNumber = 1;

	/**
	 * 타임스템프
	 */
	private long timeStemp;
	
	@Override
	public long getTotalUpMessageCount() {
		return this.totalUpMessageCount;
	}

	@Override
	public void setTotalUpMessageCount(long count) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Total up message count changed", "totalUpMessageCount", "long", this.totalUpMessageCount, count);
		super.sendNotification(n);
		this.totalUpMessageCount = count;	
	}

	@Override
	public long getTotalDownMessageCount() {
		return this.totalDownMessageCount;
	}

	@Override
	public void setTotalDownMessageCount(long count) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Total down message count changed", "totalDownMessageCount", "long", this.totalDownMessageCount, count);
		super.sendNotification(n);
		this.totalDownMessageCount = count;
	}

	@Override
	public int getUpMessageInSecond() {
		return this.upMessageInSecond;
	}

	@Override
	public void setUpMessageInSecond(int second) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Up message in second changed", "upMessageInSecond", "int", this.upMessageInSecond, second);
		super.sendNotification(n);
		this.upMessageInSecond = second;
	}

	@Override
	public int getDownMessageInSecond() {
		// TODO Auto-generated method stub
		return this.downMessageInSecond;
	}

	@Override
	public void setDownMessageInSecond(int second) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"Down message in second changed", "downMessageInSecond", "int", this.downMessageInSecond, second);
		super.sendNotification(n);
		this.downMessageInSecond = second;
	}

	@Override
	public long getTimeStemp() {
		return this.timeStemp;
	}	
}
