/*
 * Filename : ApprovalPanel.java
 * Class : ApprovalPanel
 */
package org.chaos.mgmt.client.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * <p>Title : 승인/비승인 패널</p>
 * <p>Discription : 승인 비승인 패널로 승인 비승인 여부를 설정한다.</p>
 * <p>History </p>
 * @author Kooin-Shin
 * @version 1.0
 */
public class ApprovalPanel extends DefaultPanel
{
    /** 라디오 이름 */
    String radioName[] = {"\uC2B9                          \uC778", "\uBE44          \uC2B9           \uC778"};

    /** 비승인 내역 */
    String nonApproval;
    
    /** 버튼 그룹 */
    ButtonGroup bg;
    
    /** 라디오 버튼 목록 */
    JRadioButton jr[];
    
    /** 선택된 라디오 인덱스 */
    int selectedIndex;
    
    /** 패널 */
    JPanel jp;
    
    /** 패널 */
    JPanel jp1;
    
    /** 승인/비승인 라벨 */
    JLabel jl;
    
    /** 승인/비승인 텍스트 영역 */    
    JTextArea jta;
    
    /** 스크롤 팬 */
    JScrollPane jsp;
    
    /**
     * 생성자
     * @param s 타이틀
     * @param i 선택 인덱스
     */
    public ApprovalPanel(String s, int i)
    {
        super(s, BorderFactory.createEtchedBorder(), new BorderLayout());
        nonApproval = "\uBE44\uC2B9\uC778 \uB0B4\uC5ED";
        selectedIndex = i;
        initComp();
    }
    
    /**
     * 컴포넌트 초기화
     */
    private void initComp()
    {
        jr = new JRadioButton[radioName.length];
        bg = new ButtonGroup();
        jp = new JPanel(new GridLayout(1, jr.length));
        jp.setLayout(new GridLayout(1, jr.length));
        for(int i = 0; i < jr.length; i++)
        {
            jr[i] = new JRadioButton(radioName[i]);
            if(i == selectedIndex)
                jr[i].setSelected(true);
            jr[i].setHorizontalAlignment(0);
            jr[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionevent)
                {
                    setApprovalEnabled();
                }
            });
            bg.add(jr[i]);
            jp.add(jr[i]);
        }
        add(jp, "North");
        jp1 = new JPanel(new BorderLayout());
        jl = new JLabel(nonApproval);
        jl.setEnabled(false);
        jl.setHorizontalAlignment(2);
        jp1.add(jl, "North");
        jta = new JTextArea();
        jta.setEnabled(false);
        jsp = new JScrollPane(jta, 20, 31);
        jsp.setVerticalScrollBar(new JScrollBar(1));
        jsp.getVerticalScrollBar().setBlockIncrement(100);
        jsp.getVerticalScrollBar().setUnitIncrement(10);
        jp1.add(jsp, "Center");
        add(jp1, "Center");
    }
    
    /**
     * 승인으로 설정한다.
     */
    public void setApprovalEnabled()
    {
        int i = getSelectedIndex();
        if(i == 0)
        {
            jl.setEnabled(false);
            jta.setEnabled(false);
        } else
        if(i == 1)
        {
            jl.setEnabled(true);
            jta.setEnabled(true);
            jta.requestFocus();
        }
    }
    
    /**
     * i위치의 라디오 버튼을 얻는다.
     * @param i 라디오 인덱스
     * @return JRadioButton 라디오 버튼
     */
    public JRadioButton getRadioAt(int i)
    {
        return jr[i];
    }

    /**
     * 선택된 인덱스를 얻는다.
     * @return 선택 인덱스 번호
     */
    public int getSelectedIndex()
    {
        for(int i = 0; i < jr.length; i++)
            if(jr[i].isSelected())
                return i;
        return -1;
    }
}
