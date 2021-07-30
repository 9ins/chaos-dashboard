/*
 * Filename : LabelSpinField.java
 * Class : LabelSpinField
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;


/**
 * LabelSpinField 클래스
 * 라벨과 스핀필드를 통합하는 UI 컨포넌트
 * Copyright: (주)쌍용정보통신
 * 
 * @author Kooin Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class LabelSpinField extends PanelElement
{
    /** 스핀필드 객체 */
    SpinField stf;

    /**
     * 생성자
     * @param labelName 라벨 이름
     */
    public LabelSpinField(String labelName)
    {
        this(labelName, 0, Integer.MAX_VALUE);
    }

    /**
     * 생성자
     * @param labelName 요소의 라벨이름
     * @param min 스핀 필드 최소값
     * @param max 스핀 필드 최대값
     */
    public LabelSpinField(String labelName, int min, int max)
    {
        this(labelName, min, max, 1);
    }

    /**
     * 생성자
     * @param labelName 요소의 라벨이름
     * @param min 스핀 필드 최소값
     * @param max 스핀 필드 최대값
     * @param amount 증감치
     */
    public LabelSpinField(String labelName, int min, int max, int amount)
    {
        this(labelName, min, max, amount, "");
    }
    
    /**
     * 라벨이름, 최소값, 최대값, 증가치, 단위스트링을 인자로 갖는 생성자
     * @param labelName 라벨이름
     * @param min 최소값
     * @param max 최대값
     * @param amount 증감치
     * @param unit 단위 스트링
     */    
    public LabelSpinField(String labelName, int min, int max, int amount, String unit)
    {
        this(labelName, min, max, amount, unit, (Math.max(min, max)+unit).length()+1);
    }

    /**
     * 생성자
     * @param labelName 요소의 라벨이름
     * @param min 스핀 필드 최소값
     * @param max 스핀 필드 최대값
     * @param amount 증감치
     * @param unit 단위 스트링
     * @param column 단위 스트링 정렬방식
     */
    public LabelSpinField(String labelName, int min, int max, int amount, String unit, int column)
    {
        this(labelName, min, max, amount, unit, column, SpinField.UNIT_ALIGN_RIGHT);
    }
    
    /**
     * 라벨이름, 최소값, 최대값, 증가치, 단위스트링, 컬럼 사아즈, 단위 스트링 정렬을 인자로 갖는 생성자
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
     * KElement 클래스로 부터 오버라이딩 된 메서드로서 콤보박스에 대한 설정을 정의
     * @param jc 설정을 정의할 컨포넌트
     */
    public void setting(Component jc)
    {
    }

    /**
     * KElement 클래스로 부터 오버라이딩 된 메서드로서 컨포넌트를 만들고 붙이는 역할
     * @param jc 만들고 붙일 컨포넌트
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
     * 스핀필드 객체를 구한다.
     * return 콤보박스 객체
     */
    public SpinField getSpinField()
    {
        return this.stf;
    }

    /**
     * 스핀필드에 ActionListener를 셋팅한다.
     * @param al 액션 리스너 객체
     */
    public void addActionListener(ActionListener listener)
    {
        this.stf.addActionListener(listener);
    }

    /**
     * 스크롤 바 리스너를 붙인다.
     * @param listener 스크롤바 리스너
     */
    public void addAdjustmentListener(AdjustmentListener listener)
    {
        this.stf.addAdjustmentListener(listener);
    }

    /**
     * 키 리스너를 붙인다.
     * @param listener 키 리스너
     */
    public void addKeyListener(KeyListener listener)
    {
        this.stf.addKeyListener(listener);
    }

    /**
     * 스핀필드 값을 얻는다.
     * @return 값
     */
    public int getSpinFieldValue()
    {
        return this.stf.getSpinFieldValue();
    }

    /**
     * 스핀필드 값을 스트링으로 얻는다.
     * @param String 값
     */
    public String getStringValue()
    {
        return this.stf.getText();
    }
    
    /**
     * 컴포넌트 활성화 여부를 설정한다.
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */
    public void doEnabled(boolean is)
    {
        stf.setEnabled(is);
    }
    
    /**
     * 스핀필드 값을 설정한다.
     * @param value 값
     */
    public void setSpinFieldValue(int value)
    {
        stf.setSpinFieldValue(value);
    }
    
    /**
     * 스핀필드 단위 스트링을 설정한다.
     * @param unit 단위 스트링
     */
    public void setSpinFieldUnit(String unit)
    {
        stf.setUnit(unit);
    }
    
    ////////////////////////////// implements from MultiComponentElement //////////////////////////////

	/**
     * 값을 설정한다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
        stf.setSpinFieldValue(value);
	}

	/**
     * 값을 얻는다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return new Integer(stf.getSpinFieldValue());
	}

	/**
     * 멀티 컴포넌트를 얻는다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
	 */
	public Component getMultiComponentElement()
	{
		return this;
	}
}
