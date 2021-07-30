package org.chaos.mgmt.mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * 파일 버퍼 정보 객체
 * @author 9ins
 *
 */
public class FileBufferInfo extends NotificationBroadcasterSupport implements FileBufferInfoMBean {
	/**
	 * 주기훈련 파일버퍼 갯수
	 */
	private int cycleFBCount;
	
	/**
	 * 기타주기 파일버퍼 갯수
	 */
	private int etcFBCount;
	
	/**
	 * 주기훈련 파일버퍼 갯수 평균
	 */
	private long cycleFBCountAvgSum;
	
	/**
	 * 기타주기 파일버퍼 갯수 평균
	 */
	private int etcFBCountAvgSum;
	
	/**
	 * 주기훈련 파일버퍼 사이즈
	 */
	private long cycleFBFileSize;
	
	/**
	 * 기타주기 파일버퍼 사이즈
	 */
	private long etcFBFileSize;
	
	/**
	 * 주기훈련 파일버퍼 사이즈 평균
	 */
	private long cycleFBFileSizeAvgSum;
	
	/**
	 * 기타주기 파일버퍼 사이즈 평균
	 */
	private long etcFBFileSizeAvgSum;
	
	/**
	 * 시퀀스
	 */
	private long sequenceNumber = 1;
	
	@Override
	public int getCycleFBCount() {
		return this.cycleFBCount;
	}

	@Override
	public void setCycleFBCount(int count) {
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(),
				"Cycle FB count changed", "cycleFBCount", "int", this.cycleFBCount, count);
		super.sendNotification(n);
		this.cycleFBCount = count;	
	}

	@Override
	public int getEtcFBCount() {
		return this.etcFBCount;
	}

	@Override
	public void setEtcFBCount(int count) {
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(),
				"Etc FB count changed", "etcFBCount", "int", this.etcFBCount, count);
		super.sendNotification(n);
		this.etcFBCount = count;
	}

	@Override
	public long getCycleFBFileSize() {
		return this.cycleFBFileSize;
	}

	@Override
	public void setCycleFBFileSize(long size) {
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(),
				"Cycle FB file count changed", "cycleFBFileSize", "long", this.cycleFBFileSize, size);
		super.sendNotification(n);
		this.cycleFBFileSize = size;
	}

	@Override
	public long getEtcFBFileSize() {
		return this.etcFBFileSize;
	}

	@Override
	public void setEtcFBFileSize(long size) {
		Notification n = new AttributeChangeNotification(this, sequenceNumber++, System.currentTimeMillis(),
				"Etc FB file count changed", "etcFBFileSize", "long", this.etcFBFileSize, size);
		super.sendNotification(n);
		this.etcFBFileSize = size;
	}

	@Override
	public long getCycleFBCountAvg() {
		return 0;
	}

	@Override
	public void setCycleFBCountAvg(long countAvg) {
	}

	@Override
	public long getEtcFBCountAvg() {
		return 0;
	}

	@Override
	public void setEtcFBCountAvg(long countAvg) {		
	}

	@Override
	public long getCycleFBFileSizeAvg() {
		return 0;
	}

	@Override
	public void setCycleFBFilesizeAvg(long sizeAvg) {
	}

	@Override
	public long getEtcFBFileSizeAvg() {
		return 0;
	}

	@Override
	public void setEtcFBFileSizeAvg(long sizeAvg) {
	}

	@Override
	public long getTimeStemp() {
		return System.currentTimeMillis();
	}
}
