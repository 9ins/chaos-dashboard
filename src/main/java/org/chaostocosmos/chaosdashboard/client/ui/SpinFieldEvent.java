/*
 * Filename : SpinFieldEvent.java
 * Class : SpinFieldEvent
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.util.EventObject;

/**
 * �����ʵ� �̺�Ʈ Ŭ���� 
 * Copyright: (��)�ֿ��������
 * @author Kooin
 * @version 1.0
 * @since JDK1.3.1
 */
public class SpinFieldEvent extends EventObject
{
    /** ���� ��Ʈ�� */
    String unit;
    
    /** �����ʵ� �� */
    int value;
    
    /**
     * �̺�Ʈ �ҽ�, ���� ��Ʈ��, �����ʵ� ���� ���ڷ� ���� ������
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
     * ���� ��Ʈ���� ��´�.
     * @return String
     */   
    public String getUnit()
    {
        return this.unit;
    }
    
    /**
     * �����ʵ� ���� ��´�.
     * @return int
     */
    public int getValue()
    {
        return this.value;
    }
}
