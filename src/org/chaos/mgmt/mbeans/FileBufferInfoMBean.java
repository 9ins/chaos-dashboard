package org.chaos.mgmt.mbeans;

public interface FileBufferInfoMBean extends InfoMBean {

	/**
	 * 주기훈련 파일퍼버 갯수를 얻는다.
	 * @return 파일버퍼 갯수
	 */
	public int getCycleFBCount();
	
	/**
	 * 주기훈련 파일퍼버 갯수를 설정한다.
	 * @param count 파일퍼버 갯수
	 */
	public void setCycleFBCount(int count);
	
	/**
	 * 기타주기 파일퍼버 갯수를 얻는다.
	 * @return 파일퍼버 갯수
	 */
	public int getEtcFBCount();
	
	/**
	 * 기타주기 파일퍼버 갯수를 설정한다.
	 * @param count 파일퍼버 갯수
	 */
	public void setEtcFBCount(int count);
	
	/**
	 * 주기훈련 파일퍼버 사이즈를 얻는다.
	 * @return 파일퍼버 사이즈
	 */
	public long getCycleFBFileSize();
	
	/**
	 * 주기훈련 파일퍼버 파일 사이즈를 설정한다.
	 * @param size 파일퍼버 파일 사이즈
	 */
	public void setCycleFBFileSize(long size);
	
	/**
	 * 기타주기 파일퍼버 사이즈를 얻는다.
	 * @return 파일퍼버 사이즈
	 */
	public long getEtcFBFileSize();
	
	/**
	 * 기타주기 파일퍼버 사이즈를 설정한다.
	 * @param size 파일퍼버 사이즈
	 */
	public void setEtcFBFileSize(long size);
	
	/**
	 * 주기훈련 파일버퍼 갯수 평균을 얻는다.
	 * @return 주기훈련 파일버퍼 갯수 평균
	 */
	public long getCycleFBCountAvg();
	
	/**
	 * 주기훈련 파일버퍼 갯수 평균을 설정한다.
	 * @param countAvg 주기훈련 파일버퍼 갯수 평균
	 */
	public void setCycleFBCountAvg(long countAvg);
	
	/**
	 * 기타훈련 파일버퍼 갯수 평균을 얻는다.
	 * @return 기타훈련 파일버퍼 갯수 평균
	 */
	public long getEtcFBCountAvg();
	
	/**
	 * 기타훈련 파일버퍼 갯수 평균을 설정한다.
	 * @param countAvg 기타훈련 파일버퍼 갯수 평균
	 */
	public void setEtcFBCountAvg(long countAvg);
	
	/**
	 * 주기훈련 파일버퍼 파일크기 평균을 얻는다.
	 * @return 주기훈련 파일버퍼 파일크기 평균
	 */
	public long getCycleFBFileSizeAvg();
	
	/**
	 * 주기훈련 파일버퍼 파일크기 평균을 설정한다.
	 * @param sizeAvg 주기훈련 파일버퍼 파일크기 평균
	 */
	public void setCycleFBFilesizeAvg(long sizeAvg);
	
	/**
	 * 기타훈련 파일버퍼 파일크기 평균을 얻는다.
	 * @return 기타훈련 파일버퍼 파일크기 평균
	 */
	public long getEtcFBFileSizeAvg();
	
	/**
	 * 기타훈련 파일버퍼 파일크기 평균을 설정한다.
	 * @param sizeAvg 기타훈련 파일버퍼 파일크기 평균을 설정한다.
	 */
	public void setEtcFBFileSizeAvg(long sizeAvg);

	/**
	 * 타임 스템프를 얻는다.
	 * @return 타임 스템프
	 */
	public long getTimeStemp();
}
