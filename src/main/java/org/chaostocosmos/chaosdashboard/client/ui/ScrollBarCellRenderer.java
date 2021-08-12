/*
 * Filename : ScrollBarCellRenderer.java
 * Class : ScrollBarCellRenderer
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * ScrollBarCellRenderer Ŭ����
 * JPanel�� ��ӹ޾� ��ο� ���̺��� �����ϴ� Ŭ����
 * 
 * @author: Kooin-Shin
 * @version: 1.0
 */
class ScrollBarCellRenderer extends DefaultTableCellRenderer
{
    /** ��ũ�� �� */
    JScrollBar scrollBar;
    
    /** �� �ܰ��� */    
    protected Border border;
    
    /**
     * Default ������
     */
    public ScrollBarCellRenderer()
    {
        this(JScrollBar.HORIZONTAL);
    }
    
    /**
     * ��������� ���ڷ� ���� ������
     * @param orientation
     */    
    public ScrollBarCellRenderer(int orientation)
    {
        this.scrollBar = new JScrollBar(orientation);
        this.border = new EmptyBorder(0, 0, 0, 0);        
    }
    
    /**
     * ���̺� ���� ��ũ�ѹ� ������Ʈ�� ��´�.
     * @param table ���̺�
     * @param value ��ũ�ѽ� ��ũ�� ũ��
     * @param isSelected ���� ����
     * @param hasFocus ��Ŀ�� ����
     * @param row ��
     * @param col ��
     * @return ��ũ�ѹ� ������Ʈ
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        if(value instanceof Integer)
        {
            Integer intValue = (Integer)value;
            this.scrollBar.setValue(intValue.intValue());
        }
        else
        {
            this.scrollBar.setValue(0);
        }
        this.scrollBar.setBackground(isSelected && !hasFocus ? table.getSelectionBackground() : table.getBackground());
        this.scrollBar.setForeground(isSelected && !hasFocus ? Color.white : table.getForeground());
        this.scrollBar.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.scrollBar;
    }
}
