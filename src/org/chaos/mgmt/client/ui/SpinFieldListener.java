/*
 * Filename : SpinFieldListener.java
 * Class : SpinFieldListener
 */
package org.chaos.mgmt.client.ui;

import java.util.EventListener;

/**
 * 스핀 필드 리스너 인터페이스
 * 
 * Copyright: (주)쌍용정보통신
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public interface SpinFieldListener extends EventListener
{
    /**
     * 스핀필드 이벤트 수신시 호출
     * @param e
     */
    public abstract void spinFieldEventReceived(SpinFieldEvent e);
}
