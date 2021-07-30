/*
 * Filename : LabelRadioField.java
 * Class : LabelRadioField
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;


/**
 * 라벨 선택 필드
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public class LabelRadioField extends PanelElement
{
    /** 버튼 그룹 */
    ButtonGroup bg;
    
    /** 라디오 버튼 목록 */
    JRadioButton[] radioList;
    
    /** 라디오 버튼 스트링 목록 */
    String[] selectionList;
    
    /**
     * 라벨이름을 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#PanelElement(String)
     */
    public LabelRadioField(String labelName)
    {
        this(labelName, new String[]{"  예  ", "  아니오  "});
    }
    
    /**
     * 라벨이름, 선택 목록을 인자로 갖는 생성자
     * @param labelName 라벨이름
     * @param selectionList 선택모드
     */    
    public LabelRadioField(String labelName, String[] selectionList)
    {
        this(labelName, selectionList, 0);
    }
    
    /**
     * 라벨이름, 선택 목록, 선택 인덱스를 인자로 갖는 생성자
     * @param labelName 라벨이름
     * @param selectionList 선택모드 
     * @param selectedIndex 선택 인덱스
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
     * 선택된 라디오 인덱스를 얻는다. 
     * @return int 라디오 인덱스
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
     * 라디오 버튼을 얻는다.
     * @param idx
     * @return JRadioButton
     */
    public JRadioButton getRadioButton(int idx)
    {
        return radioList[idx];
    }
    
    /**
     * 인덱스 위치의 라디오버튼을 선택한다.
     * @param idx 라디오 인덱스
     */
    public void setSelectedIndex(int idx)
    {        
        radioList[idx].setSelected(true);
    }
    
    /**
     * 인덱스 위치의 라디오 버튼에 액션을 정의한다.
     * @param radioIdx 라디오 인덱스
     * @param al 액션 리스너
     */
    public void addRadioActionAt(int radioIdx, ActionListener al)
    {
        radioList[radioIdx].addActionListener(al);
    }
    
    //////////////////////////////////////// implementing from PanelElement //////////////////////////////
    
    /**
     * 컴포넌트 설정
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#setting(java.awt.Component)
     */
    public void setting(Component c)
    {
    }
    
    /**
     * 컴포넌트 생성
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#make(java.awt.Component)
     */
    public void make(Component c)
    {
    }

	/**
     * 컴포넌트 값을 얻는다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return new Integer(getSelectedIndex());
	}

	/**
     * 컴포넌트 값을 설정한다.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
        if(value instanceof Integer)
            setSelectedIndex(((Integer)value).intValue());
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
     * 활성화 여부 설정
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#doEnabled(boolean)
	 */
	public void doEnabled(boolean enabled)
	{
        for(int i=0; i<radioList.length; i++)
            radioList[i].setEnabled(enabled);
	}
}




