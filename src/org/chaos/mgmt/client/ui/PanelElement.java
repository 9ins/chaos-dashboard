/*
 * Filename : PanelElement.java
 * Class : PanelElement
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PanelElement 추상 클래스
 * DefaultPanel에 배치하게될 컨포넌트가 상속해야 하는 추상 클래스 *  
 * Copyright: (주)쌍용정보통신
 * 
 * @author Kooin Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public abstract class PanelElement extends JPanel implements MultiComponentElement
{
    /** 왼쪽 정렬 */
    public static final int LEFT = FlowLayout.LEFT;
    
    /** 오른쪽 정렬 */
    public static final int RIGHT = FlowLayout.RIGHT;
    
    /** 중앙 정렬 */
    public static final int CENTER = FlowLayout.CENTER;
    
    /** 라벨이름 */
    String labelName;
    
    /** 요소의 라벨 객체 */
    JLabel label;
    
    /**
     * Default 생성자
     */
    PanelElement()
    {
        this("");
    }
    
    /**
     * 레이아웃과 라벨이름을 인자로 가지는 생성자
     * @param labelName 라벨이름
     */
    PanelElement(String labelName)
    {
        super(true);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        label = new JLabel(labelName);
        label.setHorizontalAlignment(JLabel.CENTER);        
        add(label);
        set();
    }
    
    /**
     * 라벨이름을 셋팅하는 메서드
     * @param labelName 라벨이름
     */
    public void setLabelName(String labelName)
    {
        if( label == null )
        {
            label = new JLabel(labelName);
            label.setHorizontalAlignment(JLabel.CENTER);
            add(label);
        }
        else
        {
            label.setText(labelName);
        }
        set();
    }
    
    /**
     * 라벨 객체를 구하는 메서드
     * return 라벨 객체
     */
    public JLabel getLabel()
    {
        return label;
    }
    
    /**
     * 정렬 시킨다. FlowLayout의 정렬방식을 이용한다.
     * @param align FlowLayout의 정렬방식
     */
    public void setAlignment(int align)
    {
        setLayout(new FlowLayout(align));
    }
    
    /**
     * 라벨셋팅
     */
    private void set()
    {
        //label.setFont(DecoBox.LABEL_FONT);
        //label.setForeground(KDecoBox.LABEL_COLOR);
    }
    
    /**
     * 컴포넌트 활성화
     * @param is 활성화 여부
     */
    public void doEnabledAll(boolean is)
    {
        Component[] comps = getComponents();
        for(int i=0; i<comps.length; i++)
            comps[i].setEnabled(is);
    }
    
    /**
     * 붙여넣을 컨포넌트에 대한 설정을 정의하는 메서드, 추상 메서드 이므로 이 클래스를 상속한 메서드에서 반드시 재정의 요망
     * @param jc 설정을 적용할 컨포넌트
     */
    public abstract void setting(Component c);
    
    /**
     * 붙여넣을 컨포넌트를 만들고 붙이는 역할을 하는 메서드, 추상메드 이므로 이 클래스를 상속한 메서드에서 반드시 재정의 요망
     * @param jc 붙여넣을 컨포넌트
     */
    public abstract void make(Component c);    
}
