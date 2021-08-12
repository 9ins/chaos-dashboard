/*
 * Filename : PanelElement.java
 * Class : PanelElement
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PanelElement �߻� Ŭ����
 * DefaultPanel�� ��ġ�ϰԵ� ������Ʈ�� ����ؾ� �ϴ� �߻� Ŭ���� *  
 * Copyright: (��)�ֿ��������
 * 
 * @author Kooin Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public abstract class PanelElement extends JPanel implements MultiComponentElement
{
    /** ���� ���� */
    public static final int LEFT = FlowLayout.LEFT;
    
    /** ������ ���� */
    public static final int RIGHT = FlowLayout.RIGHT;
    
    /** �߾� ���� */
    public static final int CENTER = FlowLayout.CENTER;
    
    /** ���̸� */
    String labelName;
    
    /** ����� �� ��ü */
    JLabel label;
    
    /**
     * Default ������
     */
    PanelElement()
    {
        this("");
    }
    
    /**
     * ���̾ƿ��� ���̸��� ���ڷ� ������ ������
     * @param labelName ���̸�
     */
    PanelElement(String labelName)
    {
        super(true);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        label = new JLabel(labelName);
        label.setHorizontalAlignment(JLabel.CENTER);        
        add(label);
        set();
    }
    
    /**
     * ���̸��� �����ϴ� �޼���
     * @param labelName ���̸�
     */
    public void setLabelName(String labelName)
    {
        if( label == null )
        {
            label = new JLabel(labelName);
            label.setHorizontalAlignment(JLabel.CENTER);
            add(label);
        }
        else
        {
            label.setText(labelName);
        }
        set();
    }
    
    /**
     * �� ��ü�� ���ϴ� �޼���
     * return �� ��ü
     */
    public JLabel getLabel()
    {
        return label;
    }
    
    /**
     * ���� ��Ų��. FlowLayout�� ���Ĺ���� �̿��Ѵ�.
     * @param align FlowLayout�� ���Ĺ��
     */
    public void setAlignment(int align)
    {
        setLayout(new FlowLayout(align));
    }
    
    /**
     * �󺧼���
     */
    private void set()
    {
        //label.setFont(DecoBox.LABEL_FONT);
        //label.setForeground(KDecoBox.LABEL_COLOR);
    }
    
    /**
     * ������Ʈ Ȱ��ȭ
     * @param is Ȱ��ȭ ����
     */
    public void doEnabledAll(boolean is)
    {
        Component[] comps = getComponents();
        for(int i=0; i<comps.length; i++)
            comps[i].setEnabled(is);
    }
    
    /**
     * �ٿ����� ������Ʈ�� ���� ������ �����ϴ� �޼���, �߻� �޼��� �̹Ƿ� �� Ŭ������ ����� �޼��忡�� �ݵ�� ������ ���
     * @param jc ������ ������ ������Ʈ
     */
    public abstract void setting(Component c);
    
    /**
     * �ٿ����� ������Ʈ�� ����� ���̴� ������ �ϴ� �޼���, �߻�޵� �̹Ƿ� �� Ŭ������ ����� �޼��忡�� �ݵ�� ������ ���
     * @param jc �ٿ����� ������Ʈ
     */
    public abstract void make(Component c);    
}
