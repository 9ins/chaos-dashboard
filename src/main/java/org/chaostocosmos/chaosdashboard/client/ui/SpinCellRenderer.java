/*
 * Filename : SpinCellRenderer.java
 * Class : SpinCellRenderer
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * ���� �� ������ Ŭ����
 *   
 * @author: Kooin Shin
 * @version: 1.0
 */
public class SpinCellRenderer extends DefaultTableCellRenderer
{
    /** ������ �ܰ��� */
    protected Border border = new EmptyBorder(0, 0, 0, 0);
    
    /** �÷� ���� */
    ColumnData cd;
    
    /**
     * �÷� ������ ���ڷ� ���� ������
     * @param cd
     */
    public SpinCellRenderer(ColumnData cd)
    {
        this.cd = cd;        
    }
    
    /**
     * �� ������Ʈ�� ��´�.
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        SpinField sf = null;
        if(value instanceof SpinField)
        {
            sf = (SpinField)value;            
            sf.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            sf.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());            
            sf.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);            
        }
        return sf;
    }   
}






