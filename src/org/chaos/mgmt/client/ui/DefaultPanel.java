/*
 * Filename : DefaultPanel.java
 * Class : DefaultPanel
 */
package org.chaos.mgmt.client.ui;

import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/**
 * JPanel을 상속하는 클래스로 KCTC 지역화기화면에 들어갈 공통 컨포넌트를 배치시킬 수 있는 패널 이다. 지역화기 화면 구성에서 패널은
 * 외곽선과 타이틀 이름을 갖는 특성을 지니고 있고 또한 패널에 들어가는 컨포넌트들 또한 <라벨>+<컴포넌트> 의 구조를 가지고 있다. 따라서
 * 외곽선과 그외 공통형태는 기본으로 제공하는 컨포넌트의 필요가 대두되었다. 또한 UI는 추후 업그래이드나 사용자 요청시 변경될 가능성을
 * 가지고 있기 때문에 공통 라이브러리를 사용하는 것이 추후 작업량을 줄일 수 있는 방법론이라 하겠다.
 * 
 * @author Kooin-Shin
 * @version 1.2
 */
public class DefaultPanel extends JPanel
{
    /** 패널의 타이틀 스트링 */
    String panelTitle;

    /** 외곽선을 보일지 말지 */
    boolean isBorderShow = true;

    /** DefaultPanel의 요소들이 들어갈 Vector */
    Vector elementV;

    /** DefaultPanel의 외곽선 객체 */
    Border border;
    
    /**
     * 디폴트 생성자
     * @see java.lang.Object#Object()
     */    
    public DefaultPanel()
    {
        this("");
    }
    
    /**
     * 패널 타이틀을 인자로 갖는 생성자
     * @param panelTitle 패널 타이틀
     */
    public DefaultPanel(String panelTitle)
    {
        this(panelTitle, BorderFactory.createEtchedBorder());
    }
    
    /**
     * 패널 타이틀, 보더를 인자로 갖는 생성자
     * @param panelTitle 패털 타이틀
     * @param border 외곽선
     */    
    public DefaultPanel(String panelTitle, Border border)
    {
        this(panelTitle, border, null);
    }
    
    /**
     * 타이틀 스트링, 외곽선 객체, 레이아웃 매니저를 인자로 가지는 생성자
     * @param panelTitle 타이틀 스트링
     * @param border 외곽선 객체
     * @param layout 레이아웃
     */
    public DefaultPanel(String panelTitle, Border border, LayoutManager layout)
    {
        super(layout);
        this.panelTitle = panelTitle;
        this.border = border;
        this.elementV = new Vector();
        setBorder();
        setMargin();
    }
    
    /**
     * 타이틀 스트링과 외곽선 객체, 열의 수를 인자로 가지는 생성자
     * @param panelTitle 타이틀 스트링
     * @param border 외곽선 객체
     * @param row 행의 수
     * @param column 열의 수
     */
    public DefaultPanel(String panelTitle, Border border, int row, int column)
    {
        this(panelTitle, border, new GridLayout(row, column));
    }
    
    /**
     * 입력받은 KElement 객체를 배치시킨다.
     */
    private void setElements()
    {
        int num = 0;
        int count = 0;
        int total = elementV.size();
        for(int i=0; i<total; i++)
        {
            Component c = (Component)elementV.elementAt(i);
            add(c);
        }
    }    
    
    /**
     * DefaultPanel의 외곽선을 설정하는 메서드
     */
    private void setBorder()
    {
        TitledBorder tb = new TitledBorder(border, panelTitle);
        //tb.setTitleFont(DecoBox.TITLE_FONT);
        //tb.setTitleFont(new Font("굴림", 12, Font.BOLD));
        //tb.setTitleColor(KDecoBox.TITLE_COLOR);
        setBorder(tb);
    }
    
    /**
     * 배열로 받은 인자값을 백터로 돌려주는 메서드
     * @param KElement[] DefaultPanel의 요소 배열
     * @return 요소 벡터
     */
    public static Vector getArrToVector(PanelElement[] arr)
    {
        Vector v = new Vector(arr.length);
        for( int i=0; i<arr.length;  i++)
            v.addElement(arr[i]);
        return v;
    }
    
    /**
     * DefaultPanel의 요소를 추가한다.
     * @param c 추가할 컨포넌트 객체
     * @param x x좌표
     * @param y y좌표
     */
    public void addElement(Component c, int x, int y)
    {
        if(!(getLayout() == null))
            return;
        add(c);
    }
    
    /**
     * DefaultPanel의 요소를 추가한다.
     * @param c 추가할 컨포넌트 객체
     * @param align 정렬방식
     */
    public void addElement(Component c, int align)
    {
        if(!(getLayout() instanceof FlowLayout))
            return;
        add(c, align);
    }
    
    /**
     * DefaultPanel의 요소를 추가한다.
     * @param c 추가할 컨포넌트 객체
     * @param align 정렬방식
     */
    public void addElement(Component c, String align)
    {
        if(!(getLayout() instanceof BorderLayout))
            return;
        add(c, align);
    }
    
    /**
     * DefaultPanel의 요소를 추가한다.
     * @param k KElement를 상속받아 구현한 객체
     */
    public void addElement(PanelElement k)
    {
        if(!(k instanceof PanelElement))
            return;
        elementV.addElement(k);
        add(k);
    }
    
    /**
     * DefaultPanel의 요소를 추가한다.
     * @param c 컨포넌트
     */
    public void addElement(Component c)
    {
        if(!(getLayout() instanceof GridLayout))
            return;
        elementV.addElement(c);
        add(c);
    }
    
    /**
     * DefaultPanel의 요소를 추가하는 메서드
     * @param title 타이틀
     * @param c Component 객체
     */
    public void addElement(String title, Component c)
    {
        if(c instanceof PanelElement)
            return;
        JPanel jp = new JPanel();
        jp.add(new JLabel(title));
        jp.add(c);
        elementV.addElement(c);
        add(jp);
    }
    
    /**
     * Index위치에 컴포넌트를 배치 한다.
     * @param index 컴포넌트 인덱스
     */
    public void addElementAt(Component c, int index)
    {
                
    }
    
    /**
     * DefaultPanel의 요소를 Vector로 얻는다.
     * @return 요소벡터
     */
    public Vector getElementV()
    {
        return this.elementV;
    }
    
    /**
     * BorderLayout일때 여백을 설정한다.
     */
    public void setMargin()
    {
        /*
        if(getLayout() instanceof BorderLayout)
        {
            add(Box.createVerticalStrut(10), "North");
            add(Box.createHorizontalStrut(10), "East");
            add(Box.createVerticalStrut(10), "South");
            add(Box.createHorizontalStrut(10), "West");
        }
        */
    }
    
    /**
     * 모든 컴포넌트 활성화를 설정한다.
     * @param be 활성화 여부
     */
    public void setEnabledAll(boolean be)
    {
        Component[] compList = getComponents();
        for(int i=0; i<compList.length; i++)
            compList[i].setEnabled(be);
    }
    
    /**
     * 타이틀 스트링을 설정한다.
     * @param title 타이틀 스트링
     */
    public void setTitle(String title)
    {
        this.panelTitle = title;
        setBorder();
    }
}
