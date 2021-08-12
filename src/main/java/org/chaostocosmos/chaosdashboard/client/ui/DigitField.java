/*
 * Filename : DigitField.java
 * Class : DigitField
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;


/**
 * ���� �ʵ� Ŭ����
 *
 * @author Kooin Shin
 * @version 1.0
 */
public class DigitField extends JTextField implements KeyListener
{
    /** �ʵ尪 */
    int value;
    
    /** �÷� ������ */
    int column;

    /** �÷� ������ */
    int limit;
    
    /** ���� ��Ʈ�� */
    String unit;
    
    /**
     * ����Ʈ ������
     * @see java.lang.Object#Object()
     */
    public DigitField()
    {
        this(4);
    }
    
    /**
     * ������
     * @see javax.swing.JTextField#JTextField(int)
     */
    public DigitField(int column)
    {
        this(column, column);
    }
    
    /**
     * ������
     * @see javax.swing.JTextField#JTextField(int)
     */
    public DigitField(int column, int limit)
    {
        this(column, limit, "m");
    }
    
    /**
     * ������
     * @param column �÷� ������
     * @param limit �Ѱ� �� 
     * @param unit ���� ��Ʈ��
     */
    public DigitField(int column, int limit, String unit)
    {
        this(column, limit, unit, "0123456789.m");
    }
    
    /**
     * ������
     * @param column �÷� ������
     * @param limit �Ѱ� ��
     * @param unit ���� ��Ʈ��
     * @param validChars ��ȿ���ڿ�
     */
    public DigitField(int column, int limit, String unit, String validChars)
    {
        this.limit = limit;
        this.unit = unit;
        super.setDocument(new BoundDocument(this.limit, this, validChars));
        setColumns(column);
        setHorizontalAlignment(JTextField.RIGHT);
        super.addKeyListener(this);
    }
    
    /**
     * �ʵ尪�� ��´�.
     * @return int int ��
     */
    public long getLongValue()
    {
        return (long)this.value;
    }
    
    /**
     * �ʵ尪�� ��´�.
     * @return double double���� ��´�.
     */
    public double getDoubleValue()
    {
        return this.value;
    }

    /**
     * �ʵ尪�� �����Ѵ�.
     * @param value �ʵ尪
     */
    public void setValue(long value)
    {
        setValue(value+"");
    }
    
    /**
     * �ʵ尪�� �����Ѵ�.
     * @param value �ʵ尪
     */
    public void setValue(double value)
    {
        setValue(value+"");
    }

    /**
     * �ʵ尪�� �����Ѵ�.
     * @param strValue �ʵ� ��
     */
    private int setValue(String strValue)
    {
        int ret = 0;
        if(!strValue.equals(""))
        {
            try
            {
                this.value = (int)Double.parseDouble(strValue.trim());
                if(unit != null && unit.trim().length() != 0)
                    strValue += (strValue+unit);
                setText(strValue);
            }
            catch(NumberFormatException e)
            {
                ret = -1;
            }
        }
        return ret;
    }
    
    /**
     * ���� ��Ʈ���� ��´�.
     * @return String ���� ��Ʈ��
     */
    public String getUnit()
    {
        return this.unit;
    }
    
    /**
     * ���� ��Ʈ���� �����Ѵ�.
     * @param unit ���� ��Ʈ��
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    
    /**
     * �ִ� ����� ��´�.
     * @return int �ִ� ������
     */
    public int getLimit()
    {
        return this.limit;
    }
    
    /**
     * �ִ� ����� �����Ѵ�.
     * @param limit �ִ� ������
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    /**
     * �ؽ�Ʈ �ʵ� �ķ� ����� �����Ѵ�.
     * @see javax.swing.JTextField#setColumns(int)
     */
    public void setColumns(int column)
    {
        super.setColumns(column);
        
    }   
     
	/**
     * Ű�� �ԷµǾ����� 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e)
	{
	}
    
	/**
     * Ű�� ����������
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e)
	{
	}
    
	/**
     * Ű�� ���� ������
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e)
	{
        String strVal = getText().trim();
        int idx = strVal.indexOf(unit);
        if(idx != -1)
            strVal = strVal.substring(0, idx).trim();
        if(!strVal.equals(""))
            this.value = (int)Double.parseDouble(strVal);
	}
}









