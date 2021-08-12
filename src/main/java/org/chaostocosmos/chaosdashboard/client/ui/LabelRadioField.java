/*
 * Filename : LabelRadioField.java
 * Class : LabelRadioField
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;


/**
 * �� ���� �ʵ�
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public class LabelRadioField extends PanelElement
{
    /** ��ư �׷� */
    ButtonGroup bg;
    
    /** ���� ��ư ��� */
    JRadioButton[] radioList;
    
    /** ���� ��ư ��Ʈ�� ��� */
    String[] selectionList;
    
    /**
     * ���̸��� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#PanelElement(String)
     */
    public LabelRadioField(String labelName)
    {
        this(labelName, new String[]{"  ��  ", "  �ƴϿ�  "});
    }
    
    /**
     * ���̸�, ���� ����� ���ڷ� ���� ������
     * @param labelName ���̸�
     * @param selectionList ���ø��
     */    
    public LabelRadioField(String labelName, String[] selectionList)
    {
        this(labelName, selectionList, 0);
    }
    
    /**
     * ���̸�, ���� ���, ���� �ε����� ���ڷ� ���� ������
     * @param labelName ���̸�
     * @param selectionList ���ø�� 
     * @param selectedIndex ���� �ε���
     */
    public LabelRadioField(String labelName, String[] selectionList, int selectedIndex)
    {
        super(labelName);
        this.selectionList = selectionList;
        radioList = new JRadioButton[selectionList.length];
        bg = new ButtonGroup();
        for(int i=0; i<radioList.length; i++)
        {
            radioList[i] = new JRadioButton(selectionList[i]);
            bg.add(radioList[i]);
            super.add(radioList[i]);
        }
        if(radioList.length != 0 && selectedIndex >= 0 && selectedIndex <radioList.length)
        {
            radioList[selectedIndex].setSelected(true);
        }
    }
    
    /**
     * ���õ� ���� �ε����� ��´�. 
     * @return int ���� �ε���
     */    
    public int getSelectedIndex()
    {
        int idx = -1;        
        for(int i=0; i<radioList.length; i++)
        {
            if(radioList[i].isSelected())
            {
                idx = i;
                break;
            }
        } 
        return idx;
    }
    
    /**
     * ���� ��ư�� ��´�.
     * @param idx
     * @return JRadioButton
     */
    public JRadioButton getRadioButton(int idx)
    {
        return radioList[idx];
    }
    
    /**
     * �ε��� ��ġ�� ������ư�� �����Ѵ�.
     * @param idx ���� �ε���
     */
    public void setSelectedIndex(int idx)
    {        
        radioList[idx].setSelected(true);
    }
    
    /**
     * �ε��� ��ġ�� ���� ��ư�� �׼��� �����Ѵ�.
     * @param radioIdx ���� �ε���
     * @param al �׼� ������
     */
    public void addRadioActionAt(int radioIdx, ActionListener al)
    {
        radioList[radioIdx].addActionListener(al);
    }
    
    //////////////////////////////////////// implementing from PanelElement //////////////////////////////
    
    /**
     * ������Ʈ ����
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#setting(java.awt.Component)
     */
    public void setting(Component c)
    {
    }
    
    /**
     * ������Ʈ ����
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#make(java.awt.Component)
     */
    public void make(Component c)
    {
    }

	/**
     * ������Ʈ ���� ��´�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return new Integer(getSelectedIndex());
	}

	/**
     * ������Ʈ ���� �����Ѵ�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
        if(value instanceof Integer)
            setSelectedIndex(((Integer)value).intValue());
	}

	/**
     * ���� ������Ʈ�� ��´�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
	 */
	public Component getMultiComponentElement()
	{
		return this;
	}

	/**
     * Ȱ��ȭ ���� ����
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#doEnabled(boolean)
	 */
	public void doEnabled(boolean enabled)
	{
        for(int i=0; i<radioList.length; i++)
            radioList[i].setEnabled(enabled);
	}
}




