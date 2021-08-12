/*
 * Filename : LabelDigitField.java
 * Class : LabelDigitField
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;


/**
 * LabelDigitField Ŭ����
 * �� + ���� �ؽ�Ʈ �ʵ� ������Ʈ�� �̷���� UI Ŭ����
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public class LabelDigitField extends PanelElement
{
    /** ���� �ʵ� */
    DigitField df;
    
    /**
     * �� �̸��� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#PanelElement(String)
     */
    public LabelDigitField(String labelName)
    {
        this(labelName, 4);
    }
    
    /**
     * �� �̸�, �÷� ����� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param column �÷�
     */
    public LabelDigitField(String labelName, int column)
    {
        this(labelName, column, column);
    }
    
    /**
     * �� �̸�, �÷� ������, �ִ� �Է� ����� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param column �÷�
     */    
    public LabelDigitField(String labelName, int column, int limit)
    {
        this(labelName, column, limit, "");
    }
    
    /**
     * ���̸�, �÷�������, �ִ� �Է� ������, ���� ��Ʈ���� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param column �÷�
     * @param limit �Ѱ谪
     * @param unit ���� ��Ʈ��
     */    
    public LabelDigitField(String labelName, int column, int limit, String unit)
    {
        super(labelName);
        this.df = new DigitField(column, limit, unit);
        setting(this.df);
    }
    
    /**
     * ���� �ʵ� ���� long���� ��´�.
     * @return int �ʵ尪
     */
    public long getLongValue()
    {
        return this.df.getLongValue();
    }
    
    /**
     * ���� �ʵ� ���� double�� ��´�.
     * @return double �ʵ� ��
     */
    public double getDoubleValue()
    {
        return this.df.getDoubleValue();
    }
    
    /**
     * ���� �ʵ� ���� �����Ѵ�.
     * @param val ���� �ʵ� ��
     */
    public void setDigitFieldValue(long val)
    {
        this.df.setValue(val);
    }
    
    /**
     * ���� �ʵ� ���� �����Ѵ�.
     * @param val ���� �ʵ� ��
     */
    public void setDigitFieldValue(double val)
    {
        this.df.setValue(val);
    }
    
    /**
     * ���� �ʵ� ��Ʈ���� �����Ѵ�.
     * @param text �ʵ尪
     */
    public void setText(String text)
    {
        this.df.setText(text);
    }
    
    /**
     * ���� ��Ʈ���� ��´�.
     * @return String ���� ��Ʈ��
     */
    public String getUnit()
    {
        return this.df.getUnit();
    }
    
    /**
     * �����ʵ� �ִ밪�� �����Ѵ�.
     * @param maxSize �ִ밪
     */
    public void setDigitFieldLimit(int maxSize)
    {
        this.df.setLimit(maxSize);
    }
    
    /**
     * ���� �ʵ带 ��´�.
     * @return DigitField ���� �ʵ�
     */
    public DigitField getDigitField()
    {
        return this.df;
    }
    
    /**
     * ������Ʈ Ȱ��ȭ ���θ� �����Ѵ�.
     * @param enable Ȱ��ȭ ����
     */
    public void doEnabled(boolean enable)
    {
        label.setEnabled(enable);
        df.setEnabled(enable);
    }
    
    ////////////////////////////// implementing from PanelElement //////////////////////////////

    /**
     * ������Ʈ ����
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#setting(java.awt.Component)
     */
    public void setting(Component c)
    {
        add(c);
    }
    
    /**
     * ������Ʈ ���� 
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#make(java.awt.Component)
     */
    public void make(Component c)
    {
    }

	/**
     * ������Ʈ �� ����
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
        if(value instanceof Long || value instanceof Integer)
        {
            setDigitFieldValue(((Long)value).longValue());
        }
        else if(value instanceof Double)
        {
            setDigitFieldValue(((Double)value).doubleValue());
        }
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
     * ������Ʈ ���� ��´�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return new Double(getDoubleValue());
	}
}
