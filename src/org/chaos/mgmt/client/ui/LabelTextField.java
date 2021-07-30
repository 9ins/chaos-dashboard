/*
 * Filename : LabelTextField.java
 * Class : LabelTextField
 */
package org.chaos.mgmt.client.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.event.KeyListener;
import javax.swing.Box;

/**
 * LabelTextField 클래스
 * 라벨과 텍스트 필드를 통합하는 UI 컨포넌트 
 * Copyright: (주)쌍용정보통신
 * 
 * @author Kooin Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class LabelTextField extends PanelElement
{
    /** 텍스트 필드 객체 */
    JTextField textField;
    
    /** 도큐먼트 */
    BoundDocument doc;
    
    /** 텍스트 필드 길이 */
    int column;
    
    /** 입력 제한 길이 */
    int limit;
    
    /** 편집가능 여부 */
    boolean editable = true;
    
    /**
     * 라벨이름을 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#PanelElement(String)
     */    
    public LabelTextField(String labelName)
    {
        this(labelName, labelName.length());
    }
    
    /**
     * 라벨이름과 컬럼 넓이를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param column 컬럼 인덱스
     */
    public LabelTextField(String labelName, int column)
    {
        this(labelName, column, column);
    }
    
    /**
     * 라벨이름, 컬럼사이즈, 편집가능여부를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param column 컬럼 인덱스
     * @param editable 편집 여부
     */
    public LabelTextField(String labelName, int column, boolean editable)
    {
        this(labelName, column, column, editable, "");
    }
        
    /**
     * 라벨이름과 컬럼 넓이, 입력 제한 길이를 인자로 갖는 생성자
     * @param labelName 요소라벨 이름
     * @param column 텍스트 필드 넓이
     * @param limit 한계값
     */
    public LabelTextField(String labelName, int column, int limit)
    {
        this(labelName, column, limit, true, "");
    }    
    
    /**
     * 라벨이름, 컬럼 넓이, 입력제한 길이, 편집가능여부를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param column 컬럼 인덱스
     * @param limit 한계값
     * @param editable 편집여부
     * @param validChars 유효문자열
     */    
    public LabelTextField(String labelName, int column, int limit, boolean editable, String validChars)
    {
        super(labelName);
        this.column = column;
        this.editable = editable;
        this.textField = new JTextField(column);
        this.doc = new BoundDocument(limit, this.textField, validChars);
        this.textField.setDocument(doc);
        this.textField.setEditable(editable);
        this.textField.setBackground(Color.white);
        setting(textField);
        make(textField);
        setPreferredSize(new Dimension(column, getPreferredSize().height));
    }
    
    /**
     * KElement 클래스로 부터 오버라이딩 된 메서드 텍스트 필드에 대한 설정을 정의하게 된다.
     * @param jc 설정의 정의할 컨포넌트
     */
    public void setting(Component jc)
    {
    }
    
    /**
     * KElement 클래스로 부터 오버라이딩 된 메서드 컨포넌트를 만드는 역할을 한다.
     */
    public void make(Component jc)
    {
        add(Box.createHorizontalGlue());
        add(jc);
    }
    
    /**
     * 텍스트 필드의 문자열을 얻는다.
     * @return 문자열
     */
    public String getText()
    {
        return this.textField.getText();
    }
    
    /**
     * 텍스트 필드 객체를 가져온다.
     * @return 텍스트 필드 객체
     */
    public JTextField getTextField()
    {
        return this.textField;
    }
    
    /**
     * 컴럼 사이즈를 얻는다.
     * @return int
     */
    public int getColumn()
    {
        return this.column;
    }
    
    /**
     * 최대 입력 사이즈를 얻는다.
     * @return int
     */
    public int getLimit()
    {
        return this.limit;
    }
    
    /**
     * 텍스트 필드에 문자열을 셋팅
     * @param text 문자열
     */
    public void setText(String text)
    {
        this.textField.setText(text);
    }
    
    /**
     * 키 리스너를 설정한다.
     * @param kl 키 리스너
     */
    public void addKeyListener(KeyListener kl)
    {
        this.textField.addKeyListener(kl);
    }
    
    /**
     * 활성화 여부를 설정한다.
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */    
    public void doEnabled(boolean is)
    {
        this.textField.setEnabled(is);
    }
    
    /**
     * 편집가능여부를 설정한다.
     * @param is 편집가능여부
     */
    public void setEditable(boolean is)
    {
        this.textField.setEditable(is);
    }

	/**
     * 컴포넌트 값을 얻는다. 
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return getText();
	}

	/**
     * 컴포넌트 값을 설정한다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
        setText(value+"");
	}

	/**
     * 다중 컴포넌트를 얻는다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
	 */
	public Component getMultiComponentElement()
	{
		return this;
	}
}



