/*
 * Filename : LabelDigitField.java
 * Class : LabelDigitField
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;


/**
 * LabelDigitField 클래스
 * 라벨 + 숫자 텍스트 필드 컴포넌트로 이루어진 UI 클래스
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public class LabelDigitField extends PanelElement
{
    /** 숫자 필드 */
    DigitField df;
    
    /**
     * 라벨 이름을 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#PanelElement(String)
     */
    public LabelDigitField(String labelName)
    {
        this(labelName, 4);
    }
    
    /**
     * 라벨 이름, 컬럼 사이즈를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param column 컬럼
     */
    public LabelDigitField(String labelName, int column)
    {
        this(labelName, column, column);
    }
    
    /**
     * 라벨 이름, 컬럼 사이즈, 최대 입력 사이즈를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param column 컬럼
     */    
    public LabelDigitField(String labelName, int column, int limit)
    {
        this(labelName, column, limit, "");
    }
    
    /**
     * 라벨이름, 컬럼사이즈, 최대 입력 사이즈, 단위 스트링을 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param column 컬럼
     * @param limit 한계값
     * @param unit 단위 스트링
     */    
    public LabelDigitField(String labelName, int column, int limit, String unit)
    {
        super(labelName);
        this.df = new DigitField(column, limit, unit);
        setting(this.df);
    }
    
    /**
     * 숫자 필드 값을 long으로 얻는다.
     * @return int 필드값
     */
    public long getLongValue()
    {
        return this.df.getLongValue();
    }
    
    /**
     * 숫자 필드 값을 double로 얻는다.
     * @return double 필드 값
     */
    public double getDoubleValue()
    {
        return this.df.getDoubleValue();
    }
    
    /**
     * 숫자 필드 값을 설정한다.
     * @param val 숫자 필드 값
     */
    public void setDigitFieldValue(long val)
    {
        this.df.setValue(val);
    }
    
    /**
     * 숫자 필드 값을 설정한다.
     * @param val 숫자 필드 값
     */
    public void setDigitFieldValue(double val)
    {
        this.df.setValue(val);
    }
    
    /**
     * 숫자 필드 스트링을 설정한다.
     * @param text 필드값
     */
    public void setText(String text)
    {
        this.df.setText(text);
    }
    
    /**
     * 단위 스트링을 얻는다.
     * @return String 단위 스트링
     */
    public String getUnit()
    {
        return this.df.getUnit();
    }
    
    /**
     * 숫자필드 최대값을 설정한다.
     * @param maxSize 최대값
     */
    public void setDigitFieldLimit(int maxSize)
    {
        this.df.setLimit(maxSize);
    }
    
    /**
     * 숫자 필드를 얻는다.
     * @return DigitField 숫자 필드
     */
    public DigitField getDigitField()
    {
        return this.df;
    }
    
    /**
     * 컴포넌트 활성화 여부를 설정한다.
     * @param enable 활성화 여부
     */
    public void doEnabled(boolean enable)
    {
        label.setEnabled(enable);
        df.setEnabled(enable);
    }
    
    ////////////////////////////// implementing from PanelElement //////////////////////////////

    /**
     * 컴포넌트 설정
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#setting(java.awt.Component)
     */
    public void setting(Component c)
    {
        add(c);
    }
    
    /**
     * 컴포넌트 생성 
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#make(java.awt.Component)
     */
    public void make(Component c)
    {
    }

	/**
     * 컴포넌트 값 설정
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
     * 다중 컴포넌트를 얻는다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
	 */
	public Component getMultiComponentElement()
	{
		return this;
	}

	/**
     * 컴포넌트 값을 얻는다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return new Double(getDoubleValue());
	}
}
