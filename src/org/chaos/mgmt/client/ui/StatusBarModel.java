/*
 * Filename : StatusBarModel.java
 * Class : StatusBarModel
 */
package org.chaos.mgmt.client.ui;

/**
 * 상태바 모델
 * @author Kooin
 * @since 2003. 7. 7.
 */
public interface StatusBarModel
{
    /**
     * 상태바 문자열을 설정한다.
     * @param msg
     */
    public void setStatus(String msg);
    
    /**
     * 상태바 문자열을 얻는다.
     * @return String
     */
    public String getStatus();
}
