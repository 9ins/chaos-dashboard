/*
 * Filename : ApprovalPanel.java
 * Class : ApprovalPanel
 */
package org.chaostocosmos.chaosdashboard.client.ui;

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
 * <p>Title : ����/����� �г�</p>
 * <p>Discription : ���� ����� �гη� ���� ����� ���θ� �����Ѵ�.</p>
 * <p>History </p>
 * @author Kooin-Shin
 * @version 1.0
 */
public class ApprovalPanel extends DefaultPanel
{
    /** ���� �̸� */
    String radioName[] = {"\uC2B9                          \uC778", "\uBE44          \uC2B9           \uC778"};

    /** ����� ���� */
    String nonApproval;
    
    /** ��ư �׷� */
    ButtonGroup bg;
    
    /** ���� ��ư ��� */
    JRadioButton jr[];
    
    /** ���õ� ���� �ε��� */
    int selectedIndex;
    
    /** �г� */
    JPanel jp;
    
    /** �г� */
    JPanel jp1;
    
    /** ����/����� �� */
    JLabel jl;
    
    /** ����/����� �ؽ�Ʈ ���� */    
    JTextArea jta;
    
    /** ��ũ�� �� */
    JScrollPane jsp;
    
    /**
     * ������
     * @param s Ÿ��Ʋ
     * @param i ���� �ε���
     */
    public ApprovalPanel(String s, int i)
    {
        super(s, BorderFactory.createEtchedBorder(), new BorderLayout());
        nonApproval = "\uBE44\uC2B9\uC778 \uB0B4\uC5ED";
        selectedIndex = i;
        initComp();
    }
    
    /**
     * ������Ʈ �ʱ�ȭ
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
     * �������� �����Ѵ�.
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
     * i��ġ�� ���� ��ư�� ��´�.
     * @param i ���� �ε���
     * @return JRadioButton ���� ��ư
     */
    public JRadioButton getRadioAt(int i)
    {
        return jr[i];
    }

    /**
     * ���õ� �ε����� ��´�.
     * @return ���� �ε��� ��ȣ
     */
    public int getSelectedIndex()
    {
        for(int i = 0; i < jr.length; i++)
            if(jr[i].isSelected())
                return i;
        return -1;
    }
}
