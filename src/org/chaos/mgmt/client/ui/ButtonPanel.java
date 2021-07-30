/*
 * Filename : ButtonPanel.java
 * Class : ButtonPanel
 */
package org.chaos.mgmt.client.ui;

import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * Panel에  버튼을 담고있는 객체 생성자에서 버튼이름을 String배열로 넘겨주면 
 * 배열크기만큼의 버튼이 패널에 배치된다.
 * 
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class ButtonPanel extends JPanel
{
    /** 왼쪽정렬 */
    public static final int LEFT = 0;

    /** 중앙정렬 */
    public static final int CENTER = 1;

    /** 오른쪽정렬 */
    public static final int RIGHT = 2;
    
    /** 버튼 정렬 방식 x축 */
    public static final int X_AXIS = 0;

    /** 버튼 정렬 방식 y축 */
    public static final int Y_AXIS = 1;
    
    /** 정렬방식 */
    protected int align = RIGHT;
    
    /** 버튼이름 배열 */
    protected String[] btnNames;
    
    /** 버튼객체 배열 */
    protected JButton[] btn;

    /** 패널 목록 */
    protected JPanel[] btnPanelList;
    
    /**
     * 버튼 이름 배열을 인자로 갖는 생성자
     * @param btnNames 버튼이름 배열
     */
    public ButtonPanel(String[] btnNames)
    {
        this(btnNames, ButtonPanel.RIGHT);
    }
    
    /**
     * 버튼이름 배열과 정렬방식을 인자로 갖는 생성자
     * @param btnNames 버튼이름 배열
     * @param align 정렬방식
     */
    public ButtonPanel(String[] btnNames, int align)
    {
        this(btnNames, align, ButtonPanel.X_AXIS);
    }

    /**
     * 버튼이름
     * @param btnNames
     * @param align
     * @param orientation
     */
    public ButtonPanel(String[] btnNames, int align, int orientation)
    {
        if(orientation == 0)
            super.setLayout(new FlowLayout());
        else
            super.setLayout(new GridLayout(btnNames.length, 1));
        this.btnNames = btnNames;
        initComponent(this.btnNames);
        align(align);
    }
    
    /**
     * 컴포넌트 초기화
     * @param names
     */
    private void initComponent(String[] names)
    {
        btn = new JButton[names.length];
        btnPanelList = new JPanel[names.length];        
        for(int i=0; i<btn.length; i++)
        {
            btn[i] = new JButton(names[i]);
            //btn[i].setFont(DecoBox.BUTTON_FONT);
            //btn[i].setForeground(Color.black);
            add(btn[i]);
        }
    }

    /**
     * 정렬방식으로 레이아웃을 설정한다.
     * @param align 정렬방식
     */
    private void align(int align)
    {
        this.align = align;
        LayoutManager lm = super.getLayout();
        int a = FlowLayout.CENTER;
        if(align == LEFT)
            a = FlowLayout.LEFT;
        else if(align == CENTER)
            a = FlowLayout.CENTER;
        else if(align == RIGHT)
            a = FlowLayout.RIGHT;
        if(lm instanceof FlowLayout)
        {
            FlowLayout fl = (FlowLayout)lm;
            fl.setAlignment(a);
        }
        else if(lm instanceof GridLayout)
        {
            for(int i=0; i<this.btnPanelList.length; i++)
            {
                FlowLayout fl = (FlowLayout)btnPanelList[i].getLayout();
                fl.setAlignment(a);
            }
        }
    }
    
    /**
     * 정렬방식을 바꾼다.
     * @param align 정렬방식
     */
    public void setAlign(int align)
    {
        align(align);
    }

    /**
     * 인덱스의 버튼객체를 리턴한다.
     * @param idx 버튼객체
     * @return Button 버튼 객체
     */
    public JButton getButton(int idx)
    {
        return btn[idx];
    }
    
    /**
     * 버튼객체 배열을 리턴한다.
     * @return JButton[] 버튼객체 배열
     */
    public JButton[] getButtonArray()
    {
        return btn;
    }

    /**
     * 버튼에 툴팁을 설정한다.
     * @param idx 버튼 인덱스
     * @param toolTipText 툴팁 텍스트
     */
    public void setToolTipAt(int idx, String toolTipText)
    {
        this.btn[idx].setToolTipText(toolTipText);
    }

    /**
     * btnNum위치의 버튼에 이벤트를 설정한다.
     * @param btnNum 버튼 인덱스
     * @param al 이벤트 리스너
     */
    public void setButtonAction(int btnNum, ActionListener al)
    {
        btn[btnNum].addActionListener(al);
    }
}

