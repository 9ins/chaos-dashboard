/*
 * Filename : ComboCellRenderer.java
 * Class : ComboCellRenderer
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * �޺� �ڽ� ��������
 * 
 * @author Kooin Shin
 * @version 1.5.0
 */
public class ComboCellRenderer extends DefaultTableCellRenderer
{
    /** �ĺ� �ڽ� */
    JComboBox comboBox;
    
    /** �޺� �ڽ� ������ �ܰ��� */    
    protected Border border;
    
    /**
     * ������ ����� ���ڷ� ���� ������
     * @param items �޺� ������
     */
    public ComboCellRenderer(Object[] items)
    {
        this(new JComboBox(items));
    }
    
    /**
     * �޺��ڽ��� ���ڷ� ���� ������
     * @param comboBox �޺� �ڽ�
     */
    public ComboCellRenderer(JComboBox comboBox)
    {
        this.comboBox = comboBox;
        this.border = new EmptyBorder(0, 0, 0, 0);
    }
    
    /**
     * ���� �ε����� ��´�.
     * @return int ���� �ε���
     */
    public int getSelectedIndex()
    {
        return this.comboBox.getSelectedIndex();
    }
    
    /**
     * ���� �������� ��´�.
     * @return Object ���þ�����
     */
    public Object getSelectedItem()
    {
        return this.comboBox.getSelectedItem();
    }
    
    /**
     * �� �������� ��´�.
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        if(value instanceof JComboBox)
        {
            this.comboBox = (JComboBox)value;
        }
        this.comboBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        this.comboBox.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        this.comboBox.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.comboBox;
    }
}




