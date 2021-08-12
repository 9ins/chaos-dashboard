/*
 * Filename : ButtonPanel.java
 * Class : ButtonPanel
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * Panel��  ��ư�� ����ִ� ��ü �����ڿ��� ��ư�̸��� String�迭�� �Ѱ��ָ� 
 * �迭ũ�⸸ŭ�� ��ư�� �гο� ��ġ�ȴ�.
 * 
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class ButtonPanel extends JPanel
{
    /** �������� */
    public static final int LEFT = 0;

    /** �߾����� */
    public static final int CENTER = 1;

    /** ���������� */
    public static final int RIGHT = 2;
    
    /** ��ư ���� ��� x�� */
    public static final int X_AXIS = 0;

    /** ��ư ���� ��� y�� */
    public static final int Y_AXIS = 1;
    
    /** ���Ĺ�� */
    protected int align = RIGHT;
    
    /** ��ư�̸� �迭 */
    protected String[] btnNames;
    
    /** ��ư��ü �迭 */
    protected JButton[] btn;

    /** �г� ��� */
    protected JPanel[] btnPanelList;
    
    /**
     * ��ư �̸� �迭�� ���ڷ� ���� ������
     * @param btnNames ��ư�̸� �迭
     */
    public ButtonPanel(String[] btnNames)
    {
        this(btnNames, ButtonPanel.RIGHT);
    }
    
    /**
     * ��ư�̸� �迭�� ���Ĺ���� ���ڷ� ���� ������
     * @param btnNames ��ư�̸� �迭
     * @param align ���Ĺ��
     */
    public ButtonPanel(String[] btnNames, int align)
    {
        this(btnNames, align, ButtonPanel.X_AXIS);
    }

    /**
     * ��ư�̸�
     * @param btnNames
     * @param align
     * @param orientation
     */
    public ButtonPanel(String[] btnNames, int align, int orientation)
    {
        if(orientation == 0)
            super.setLayout(new FlowLayout());
        else
            super.setLayout(new GridLayout(btnNames.length, 1));
        this.btnNames = btnNames;
        initComponent(this.btnNames);
        align(align);
    }
    
    /**
     * ������Ʈ �ʱ�ȭ
     * @param names
     */
    private void initComponent(String[] names)
    {
        btn = new JButton[names.length];
        btnPanelList = new JPanel[names.length];        
        for(int i=0; i<btn.length; i++)
        {
            btn[i] = new JButton(names[i]);
            //btn[i].setFont(DecoBox.BUTTON_FONT);
            //btn[i].setForeground(Color.black);
            add(btn[i]);
        }
    }

    /**
     * ���Ĺ������ ���̾ƿ��� �����Ѵ�.
     * @param align ���Ĺ��
     */
    private void align(int align)
    {
        this.align = align;
        LayoutManager lm = super.getLayout();
        int a = FlowLayout.CENTER;
        if(align == LEFT)
            a = FlowLayout.LEFT;
        else if(align == CENTER)
            a = FlowLayout.CENTER;
        else if(align == RIGHT)
            a = FlowLayout.RIGHT;
        if(lm instanceof FlowLayout)
        {
            FlowLayout fl = (FlowLayout)lm;
            fl.setAlignment(a);
        }
        else if(lm instanceof GridLayout)
        {
            for(int i=0; i<this.btnPanelList.length; i++)
            {
                FlowLayout fl = (FlowLayout)btnPanelList[i].getLayout();
                fl.setAlignment(a);
            }
        }
    }
    
    /**
     * ���Ĺ���� �ٲ۴�.
     * @param align ���Ĺ��
     */
    public void setAlign(int align)
    {
        align(align);
    }

    /**
     * �ε����� ��ư��ü�� �����Ѵ�.
     * @param idx ��ư��ü
     * @return Button ��ư ��ü
     */
    public JButton getButton(int idx)
    {
        return btn[idx];
    }
    
    /**
     * ��ư��ü �迭�� �����Ѵ�.
     * @return JButton[] ��ư��ü �迭
     */
    public JButton[] getButtonArray()
    {
        return btn;
    }

    /**
     * ��ư�� ������ �����Ѵ�.
     * @param idx ��ư �ε���
     * @param toolTipText ���� �ؽ�Ʈ
     */
    public void setToolTipAt(int idx, String toolTipText)
    {
        this.btn[idx].setToolTipText(toolTipText);
    }

    /**
     * btnNum��ġ�� ��ư�� �̺�Ʈ�� �����Ѵ�.
     * @param btnNum ��ư �ε���
     * @param al �̺�Ʈ ������
     */
    public void setButtonAction(int btnNum, ActionListener al)
    {
        btn[btnNum].addActionListener(al);
    }
}

