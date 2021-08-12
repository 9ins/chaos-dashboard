/*
 * Filename : LabelSpinField.java
 * Class : LabelSpinField
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;


/**
 * LabelSpinField Ŭ����
 * �󺧰� �����ʵ带 �����ϴ� UI ������Ʈ
 * Copyright: (��)�ֿ��������
 * 
 * @author Kooin Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class LabelSpinField extends PanelElement
{
    /** �����ʵ� ��ü */
    SpinField stf;

    /**
     * ������
     * @param labelName �� �̸�
     */
    public LabelSpinField(String labelName)
    {
        this(labelName, 0, Integer.MAX_VALUE);
    }

    /**
     * ������
     * @param labelName ����� ���̸�
     * @param min ���� �ʵ� �ּҰ�
     * @param max ���� �ʵ� �ִ밪
     */
    public LabelSpinField(String labelName, int min, int max)
    {
        this(labelName, min, max, 1);
    }

    /**
     * ������
     * @param labelName ����� ���̸�
     * @param min ���� �ʵ� �ּҰ�
     * @param max ���� �ʵ� �ִ밪
     * @param amount ����ġ
     */
    public LabelSpinField(String labelName, int min, int max, int amount)
    {
        this(labelName, min, max, amount, "");
    }
    
    /**
     * ���̸�, �ּҰ�, �ִ밪, ����ġ, ������Ʈ���� ���ڷ� ���� ������
     * @param labelName ���̸�
     * @param min �ּҰ�
     * @param max �ִ밪
     * @param amount ����ġ
     * @param unit ���� ��Ʈ��
     */    
    public LabelSpinField(String labelName, int min, int max, int amount, String unit)
    {
        this(labelName, min, max, amount, unit, (Math.max(min, max)+unit).length()+1);
    }

    /**
     * ������
     * @param labelName ����� ���̸�
     * @param min ���� �ʵ� �ּҰ�
     * @param max ���� �ʵ� �ִ밪
     * @param amount ����ġ
     * @param unit ���� ��Ʈ��
     * @param column ���� ��Ʈ�� ���Ĺ��
     */
    public LabelSpinField(String labelName, int min, int max, int amount, String unit, int column)
    {
        this(labelName, min, max, amount, unit, column, SpinField.UNIT_ALIGN_RIGHT);
    }
    
    /**
     * ���̸�, �ּҰ�, �ִ밪, ����ġ, ������Ʈ��, �÷� �����, ���� ��Ʈ�� ������ ���ڷ� ���� ������
     * @param labelName
     * @param min
     * @param max
     * @param amount
     * @param unit
     * @param column
     * @param unitAlign
     */    
    public LabelSpinField(String labelName, int min, int max, int amount, String unit, int column, int unitAlign)
    {
        super(labelName);
        stf = new SpinField(min, max, amount, unit, column);
        setting(stf);
        make(stf);
    }

    /**
     * KElement Ŭ������ ���� �������̵� �� �޼���μ� �޺��ڽ��� ���� ������ ����
     * @param jc ������ ������ ������Ʈ
     */
    public void setting(Component jc)
    {
    }

    /**
     * KElement Ŭ������ ���� �������̵� �� �޼���μ� ������Ʈ�� ����� ���̴� ����
     * @param jc ����� ���� ������Ʈ
     */
    public void make(Component jc)
    {
        /*
        javax.swing.JPanel jp = new javax.swing.JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
        jp.add(Box.createHorizontalGlue());
        */
        //add(Box.createHorizontalGlue());
        add(jc);
    }

    /**
     * �����ʵ� ��ü�� ���Ѵ�.
     * return �޺��ڽ� ��ü
     */
    public SpinField getSpinField()
    {
        return this.stf;
    }

    /**
     * �����ʵ忡 ActionListener�� �����Ѵ�.
     * @param al �׼� ������ ��ü
     */
    public void addActionListener(ActionListener listener)
    {
        this.stf.addActionListener(listener);
    }

    /**
     * ��ũ�� �� �����ʸ� ���δ�.
     * @param listener ��ũ�ѹ� ������
     */
    public void addAdjustmentListener(AdjustmentListener listener)
    {
        this.stf.addAdjustmentListener(listener);
    }

    /**
     * Ű �����ʸ� ���δ�.
     * @param listener Ű ������
     */
    public void addKeyListener(KeyListener listener)
    {
        this.stf.addKeyListener(listener);
    }

    /**
     * �����ʵ� ���� ��´�.
     * @return ��
     */
    public int getSpinFieldValue()
    {
        return this.stf.getSpinFieldValue();
    }

    /**
     * �����ʵ� ���� ��Ʈ������ ��´�.
     * @param String ��
     */
    public String getStringValue()
    {
        return this.stf.getText();
    }
    
    /**
     * ������Ʈ Ȱ��ȭ ���θ� �����Ѵ�.
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */
    public void doEnabled(boolean is)
    {
        stf.setEnabled(is);
    }
    
    /**
     * �����ʵ� ���� �����Ѵ�.
     * @param value ��
     */
    public void setSpinFieldValue(int value)
    {
        stf.setSpinFieldValue(value);
    }
    
    /**
     * �����ʵ� ���� ��Ʈ���� �����Ѵ�.
     * @param unit ���� ��Ʈ��
     */
    public void setSpinFieldUnit(String unit)
    {
        stf.setUnit(unit);
    }
    
    ////////////////////////////// implements from MultiComponentElement //////////////////////////////

	/**
     * ���� �����Ѵ�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
        stf.setSpinFieldValue(value);
	}

	/**
     * ���� ��´�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return new Integer(stf.getSpinFieldValue());
	}

	/**
     * ��Ƽ ������Ʈ�� ��´�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
	 */
	public Component getMultiComponentElement()
	{
		return this;
	}
}
