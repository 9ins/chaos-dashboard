/*
 * Filename : DefaultPanel.java
 * Class : DefaultPanel
 */
package org.chaostocosmos.chaosdashboard.client.ui;

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
 * JPanel�� ����ϴ� Ŭ������ KCTC ����ȭ��ȭ�鿡 �� ���� ������Ʈ�� ��ġ��ų �� �ִ� �г� �̴�. ����ȭ�� ȭ�� �������� �г���
 * �ܰ����� Ÿ��Ʋ �̸��� ���� Ư���� ���ϰ� �ְ� ���� �гο� ���� ������Ʈ�� ���� <��>+<������Ʈ> �� ������ ������ �ִ�. ����
 * �ܰ����� �׿� �������´� �⺻���� �����ϴ� ������Ʈ�� �ʿ䰡 ��εǾ���. ���� UI�� ���� ���׷��̵峪 ����� ��û�� ����� ���ɼ���
 * ������ �ֱ� ������ ���� ���̺귯���� ����ϴ� ���� ���� �۾����� ���� �� �ִ� ������̶� �ϰڴ�.
 * 
 * @author Kooin-Shin
 * @version 1.2
 */
public class DefaultPanel extends JPanel
{
    /** �г��� Ÿ��Ʋ ��Ʈ�� */
    String panelTitle;

    /** �ܰ����� ������ ���� */
    boolean isBorderShow = true;

    /** DefaultPanel�� ��ҵ��� �� Vector */
    Vector elementV;

    /** DefaultPanel�� �ܰ��� ��ü */
    Border border;
    
    /**
     * ����Ʈ ������
     * @see java.lang.Object#Object()
     */    
    public DefaultPanel()
    {
        this("");
    }
    
    /**
     * �г� Ÿ��Ʋ�� ���ڷ� ���� ������
     * @param panelTitle �г� Ÿ��Ʋ
     */
    public DefaultPanel(String panelTitle)
    {
        this(panelTitle, BorderFactory.createEtchedBorder());
    }
    
    /**
     * �г� Ÿ��Ʋ, ������ ���ڷ� ���� ������
     * @param panelTitle ���� Ÿ��Ʋ
     * @param border �ܰ���
     */    
    public DefaultPanel(String panelTitle, Border border)
    {
        this(panelTitle, border, null);
    }
    
    /**
     * Ÿ��Ʋ ��Ʈ��, �ܰ��� ��ü, ���̾ƿ� �Ŵ����� ���ڷ� ������ ������
     * @param panelTitle Ÿ��Ʋ ��Ʈ��
     * @param border �ܰ��� ��ü
     * @param layout ���̾ƿ�
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
     * Ÿ��Ʋ ��Ʈ���� �ܰ��� ��ü, ���� ���� ���ڷ� ������ ������
     * @param panelTitle Ÿ��Ʋ ��Ʈ��
     * @param border �ܰ��� ��ü
     * @param row ���� ��
     * @param column ���� ��
     */
    public DefaultPanel(String panelTitle, Border border, int row, int column)
    {
        this(panelTitle, border, new GridLayout(row, column));
    }
    
    /**
     * �Է¹��� KElement ��ü�� ��ġ��Ų��.
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
     * DefaultPanel�� �ܰ����� �����ϴ� �޼���
     */
    private void setBorder()
    {
        TitledBorder tb = new TitledBorder(border, panelTitle);
        //tb.setTitleFont(DecoBox.TITLE_FONT);
        //tb.setTitleFont(new Font("����", 12, Font.BOLD));
        //tb.setTitleColor(KDecoBox.TITLE_COLOR);
        setBorder(tb);
    }
    
    /**
     * �迭�� ���� ���ڰ��� ���ͷ� �����ִ� �޼���
     * @param KElement[] DefaultPanel�� ��� �迭
     * @return ��� ����
     */
    public static Vector getArrToVector(PanelElement[] arr)
    {
        Vector v = new Vector(arr.length);
        for( int i=0; i<arr.length;  i++)
            v.addElement(arr[i]);
        return v;
    }
    
    /**
     * DefaultPanel�� ��Ҹ� �߰��Ѵ�.
     * @param c �߰��� ������Ʈ ��ü
     * @param x x��ǥ
     * @param y y��ǥ
     */
    public void addElement(Component c, int x, int y)
    {
        if(!(getLayout() == null))
            return;
        add(c);
    }
    
    /**
     * DefaultPanel�� ��Ҹ� �߰��Ѵ�.
     * @param c �߰��� ������Ʈ ��ü
     * @param align ���Ĺ��
     */
    public void addElement(Component c, int align)
    {
        if(!(getLayout() instanceof FlowLayout))
            return;
        add(c, align);
    }
    
    /**
     * DefaultPanel�� ��Ҹ� �߰��Ѵ�.
     * @param c �߰��� ������Ʈ ��ü
     * @param align ���Ĺ��
     */
    public void addElement(Component c, String align)
    {
        if(!(getLayout() instanceof BorderLayout))
            return;
        add(c, align);
    }
    
    /**
     * DefaultPanel�� ��Ҹ� �߰��Ѵ�.
     * @param k KElement�� ��ӹ޾� ������ ��ü
     */
    public void addElement(PanelElement k)
    {
        if(!(k instanceof PanelElement))
            return;
        elementV.addElement(k);
        add(k);
    }
    
    /**
     * DefaultPanel�� ��Ҹ� �߰��Ѵ�.
     * @param c ������Ʈ
     */
    public void addElement(Component c)
    {
        if(!(getLayout() instanceof GridLayout))
            return;
        elementV.addElement(c);
        add(c);
    }
    
    /**
     * DefaultPanel�� ��Ҹ� �߰��ϴ� �޼���
     * @param title Ÿ��Ʋ
     * @param c Component ��ü
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
     * Index��ġ�� ������Ʈ�� ��ġ �Ѵ�.
     * @param index ������Ʈ �ε���
     */
    public void addElementAt(Component c, int index)
    {
                
    }
    
    /**
     * DefaultPanel�� ��Ҹ� Vector�� ��´�.
     * @return ��Һ���
     */
    public Vector getElementV()
    {
        return this.elementV;
    }
    
    /**
     * BorderLayout�϶� ������ �����Ѵ�.
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
     * ��� ������Ʈ Ȱ��ȭ�� �����Ѵ�.
     * @param be Ȱ��ȭ ����
     */
    public void setEnabledAll(boolean be)
    {
        Component[] compList = getComponents();
        for(int i=0; i<compList.length; i++)
            compList[i].setEnabled(be);
    }
    
    /**
     * Ÿ��Ʋ ��Ʈ���� �����Ѵ�.
     * @param title Ÿ��Ʋ ��Ʈ��
     */
    public void setTitle(String title)
    {
        this.panelTitle = title;
        setBorder();
    }
}
