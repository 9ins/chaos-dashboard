/*
 * Filename : PanelEmpty.java
 * Class : PanelEmpty
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 * PanelEmpty 클래스
 * DefaultPanel에 넣을 수 있는 공백 컨포넌트 클래스
 * 
 * Copyright: (주)쌍용정보통신
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class PanelEmpty extends JPanel
{
    /** 수평 박스 */
    public static final int HORIZONTAL = 1;
    
    /** 수직 박스 */
    public static final int VERTICAL = 2;
    
    /** Box가 들어갈 Component객체 */
    Component c;
    
    /**
     * 디폴트 생성자
     * Default constructor
     * @see java.lang.Object#Object()
     */
    public PanelEmpty()
    {
        this(10);
    }
    
    /**
     * 넓이를 인자로 갖는 생성자
     * @param size 넓이
     */
    public PanelEmpty(int size)
    {
        this(size, 1);
    }
    
    /**
     * 라벨이름과 요소타입을 인자로 가지는 생성자
     * @param size 넓이
     * @param type 요소타입
     */
    public PanelEmpty(int size, int type)
    {
        if(type == HORIZONTAL)
        {
            this.c = Box.createHorizontalStrut(size);
        }
        else if(type == VERTICAL)
        {
            this.c = Box.createVerticalStrut(size);
        }
        setting(c);
        make(c);
    }
    
    /**
     * 오버라이딩된 메서드
     * @param c Component 객체
     */
    public void setting(Component c)
    {
    }
    
    /**
     * 오버라이딩된 메서드
     * @param c Component 객체
     */
    public void make(Component c)
    {
        add(c);
    }
    
    /**
     * 엘리먼트 활성화 여부
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */    
    public void doEnabled(boolean is)
    {
    }
}
