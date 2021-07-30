package org.chaos.mgmt.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * 원격지 오류를 전송하는 정보 클래스
 * @author 9ins
 *
 */
public class ThrowableInfo extends NotificationBroadcasterSupport implements ThrowableInfoMBean {
	/**
	 * 오류 객체
	 */
	private ThrowableInfo throwable;
	
	/**
	 * 시퀀스
	 */
	private long sequenceNumber;

	/**
	 * 타임스템프
	 */
	private long timeStemp;
	
	@Override
	public ThrowableInfo getThrowable() {
		return this.throwable;
	}

	@Override
	public void setThrowable(ThrowableInfo e) {
		this.timeStemp = System.currentTimeMillis();
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, this.timeStemp,
				"ThrowableInfo changed", "throwable", "ThrowableInfo", this.throwable, e);
		super.sendNotification(n);
		this.throwable = e;	
	}

	@Override
	public long getTimeStemp() {
		return this.timeStemp;
	}
}
