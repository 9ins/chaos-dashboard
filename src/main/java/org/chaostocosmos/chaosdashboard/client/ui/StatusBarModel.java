/*
 * Filename : StatusBarModel.java
 * Class : StatusBarModel
 */
package org.chaostocosmos.chaosdashboard.client.ui;

/**
 * ���¹� ��
 * @author Kooin
 * @since 2003. 7. 7.
 */
public interface StatusBarModel
{
    /**
     * ���¹� ���ڿ��� �����Ѵ�.
     * @param msg
     */
    public void setStatus(String msg);
    
    /**
     * ���¹� ���ڿ��� ��´�.
     * @return String
     */
    public String getStatus();
}
