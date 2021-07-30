/*
 * Filename : SpinFieldEvent.java
 * Class : SpinFieldEvent
 */
package org.chaos.mgmt.client.ui;

import java.util.EventObject;

/**
 * 스핀필드 이벤트 클래스 
 * Copyright: (주)쌍용정보통신
 * @author Kooin
 * @version 1.0
 * @since JDK1.3.1
 */
public class SpinFieldEvent extends EventObject
{
    /** 단위 스트링 */
    String unit;
    
    /** 스핀필드 값 */
    int value;
    
    /**
     * 이벤트 소스, 단위 스트링, 스핀필드 값을 인자로 갖는 생성자
     * @param source
     * @param unit
     * @param value
     */
    public SpinFieldEvent(Object source, String unit, int value)
    {
        super(source);
        this.unit = unit;
        this.value = value;
    }
    
    /**
     * 단위 스트링을 얻는다.
     * @return String
     */   
    public String getUnit()
    {
        return this.unit;
    }
    
    /**
     * 스핀필드 값을 얻는다.
     * @return int
     */
    public int getValue()
    {
        return this.value;
    }
}
