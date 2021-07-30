/*
 * Filename : DigitField.java
 * Class : DigitField
 */
package org.chaos.mgmt.client.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;


/**
 * 숫자 필드 클래스
 *
 * @author Kooin Shin
 * @version 1.0
 */
public class DigitField extends JTextField implements KeyListener
{
    /** 필드값 */
    int value;
    
    /** 컬럼 사이즈 */
    int column;

    /** 컬럼 사이즈 */
    int limit;
    
    /** 단위 스트링 */
    String unit;
    
    /**
     * 디폴트 생성자
     * @see java.lang.Object#Object()
     */
    public DigitField()
    {
        this(4);
    }
    
    /**
     * 생성자
     * @see javax.swing.JTextField#JTextField(int)
     */
    public DigitField(int column)
    {
        this(column, column);
    }
    
    /**
     * 생성자
     * @see javax.swing.JTextField#JTextField(int)
     */
    public DigitField(int column, int limit)
    {
        this(column, limit, "m");
    }
    
    /**
     * 생성자
     * @param column 컬럼 사이즈
     * @param limit 한계 값 
     * @param unit 단위 스트링
     */
    public DigitField(int column, int limit, String unit)
    {
        this(column, limit, unit, "0123456789.m");
    }
    
    /**
     * 생성자
     * @param column 컬럼 사이즈
     * @param limit 한계 값
     * @param unit 단위 스트링
     * @param validChars 유효문자열
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
     * 필드값을 얻는다.
     * @return int int 값
     */
    public long getLongValue()
    {
        return (long)this.value;
    }
    
    /**
     * 필드값을 얻는다.
     * @return double double값을 얻는다.
     */
    public double getDoubleValue()
    {
        return this.value;
    }

    /**
     * 필드값을 설정한다.
     * @param value 필드값
     */
    public void setValue(long value)
    {
        setValue(value+"");
    }
    
    /**
     * 필드값을 설정한다.
     * @param value 필드값
     */
    public void setValue(double value)
    {
        setValue(value+"");
    }

    /**
     * 필드값을 설정한다.
     * @param strValue 필드 값
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
     * 단위 스트링을 얻는다.
     * @return String 단위 스트링
     */
    public String getUnit()
    {
        return this.unit;
    }
    
    /**
     * 단위 스트링을 설정한다.
     * @param unit 단위 스트링
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    
    /**
     * 최대 사이즈를 얻는다.
     * @return int 최대 사이즈
     */
    public int getLimit()
    {
        return this.limit;
    }
    
    /**
     * 최대 사이즈를 설정한다.
     * @param limit 최대 사이즈
     */
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    /**
     * 텍스트 필드 컴럼 사이즈를 설정한다.
     * @see javax.swing.JTextField#setColumns(int)
     */
    public void setColumns(int column)
    {
        super.setColumns(column);
        
    }   
     
	/**
     * 키가 입력되었을시 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e)
	{
	}
    
	/**
     * 키가 눌려졌을시
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e)
	{
	}
    
	/**
     * 키가 놓여 있을시
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









