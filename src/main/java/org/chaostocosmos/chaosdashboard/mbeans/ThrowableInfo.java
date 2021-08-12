package org.chaostocosmos.chaosdashboard.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * ������ ������ �����ϴ� ���� Ŭ����
 * @author 9ins
 *
 */
public class ThrowableInfo extends NotificationBroadcasterSupport implements ThrowableInfoMBean {
	/**
	 * ���� ��ü
	 */
	private ThrowableInfo throwable;
	
	/**
	 * ������
	 */
	private long sequenceNumber;

	/**
	 * Ÿ�ӽ�����
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
