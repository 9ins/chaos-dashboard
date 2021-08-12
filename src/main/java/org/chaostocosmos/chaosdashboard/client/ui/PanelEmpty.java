/*
 * Filename : PanelEmpty.java
 * Class : PanelEmpty
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 * PanelEmpty Ŭ����
 * DefaultPanel�� ���� �� �ִ� ���� ������Ʈ Ŭ����
 * 
 * Copyright: (��)�ֿ��������
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class PanelEmpty extends JPanel
{
    /** ���� �ڽ� */
    public static final int HORIZONTAL = 1;
    
    /** ���� �ڽ� */
    public static final int VERTICAL = 2;
    
    /** Box�� �� Component��ü */
    Component c;
    
    /**
     * ����Ʈ ������
     * Default constructor
     * @see java.lang.Object#Object()
     */
    public PanelEmpty()
    {
        this(10);
    }
    
    /**
     * ���̸� ���ڷ� ���� ������
     * @param size ����
     */
    public PanelEmpty(int size)
    {
        this(size, 1);
    }
    
    /**
     * ���̸��� ���Ÿ���� ���ڷ� ������ ������
     * @param size ����
     * @param type ���Ÿ��
     */
    public PanelEmpty(int size, int type)
    {
        if(type == HORIZONTAL)
        {
            this.c = Box.createHorizontalStrut(size);
        }
        else if(type == VERTICAL)
        {
            this.c = Box.createVerticalStrut(size);
        }
        setting(c);
        make(c);
    }
    
    /**
     * �������̵��� �޼���
     * @param c Component ��ü
     */
    public void setting(Component c)
    {
    }
    
    /**
     * �������̵��� �޼���
     * @param c Component ��ü
     */
    public void make(Component c)
    {
        add(c);
    }
    
    /**
     * ������Ʈ Ȱ��ȭ ����
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */    
    public void doEnabled(boolean is)
    {
    }
}
