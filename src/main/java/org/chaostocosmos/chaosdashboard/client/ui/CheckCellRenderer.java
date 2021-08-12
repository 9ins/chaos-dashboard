/*
 * Filename : CheckCellRenderer.java
 * Class : CheckCellRenderer
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * üũ �ڽ� ���̺� ��������
 * @copyright: sicc
 * @author Kooin
 * @version 1.0
 * @since JDK1.3.1
 */
class CheckCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
    /** üũ�ڽ� */
    JCheckBox checkBox;
        
    /** ��Ŀ���� �ƴҶ� �ܰ��� */
    protected Border border;
    
    /**
     * ����Ʈ ������
     * @see java.lang.Object#Object()
     */
    public CheckCellRenderer()
    {
        this("");
    }
    
    /**
     * ���Ĺ���� ���ڷ� ���� ������
     * @param align ���Ĺ��
     */
    public CheckCellRenderer(int align)
    {
        this("", align);
    }
    
    /**
     * üũ ��Ʈ���� ���ڷ� ���� ������
     * @param str üũ ��Ʈ��
     */
    public CheckCellRenderer(String str)
    {
        this(str, JCheckBox.CENTER);
    }
    
    /**
     * üũ ��Ʈ��, ���Ĺ���� ���ڷ� ���� ������
     * @param str üũ ��Ʈ��
     * @param align ���Ĺ��
     */    
    public CheckCellRenderer(String str, int align)
    {
        this.checkBox = new JCheckBox(str);
        this.checkBox.setHorizontalAlignment(align);
        border = new EmptyBorder(0, 0, 0, 0);
    }
    
    /**
     * ���̺� ���� ������Ʈ�� ��´�.
     * @param table ���̺�
     * @param value üũ�ڽ��� üũ����
     * @param isSelected üũ����
     * @param hasFocus ��Ŀ�� ����
     * @param row ��
     * @param col ��
     * @return ���̺� �� ������Ʈ
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,  int row, int col)
    {
        if(value instanceof Boolean)
        {
            this.checkBox.setSelected(((Boolean)value).booleanValue());
        }
        this.checkBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        this.checkBox.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());        
        this.checkBox.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.checkBox;
    }
}


