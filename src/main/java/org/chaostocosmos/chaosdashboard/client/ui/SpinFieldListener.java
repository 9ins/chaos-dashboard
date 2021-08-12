/*
 * Filename : SpinFieldListener.java
 * Class : SpinFieldListener
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.util.EventListener;

/**
 * ���� �ʵ� ������ �������̽�
 * 
 * Copyright: (��)�ֿ��������
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public interface SpinFieldListener extends EventListener
{
    /**
     * �����ʵ� �̺�Ʈ ���Ž� ȣ��
     * @param e
     */
    public abstract void spinFieldEventReceived(SpinFieldEvent e);
}
