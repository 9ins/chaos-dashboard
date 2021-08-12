/*
 * Filename :ButtonCellRenderer.java
 * Class : ButtonCellRenderer
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * ���̺� ��ư �������� 
 * @author: Kooin
 * @version: 1.0
 * @since: JDK1.3.1
 */
class ButtonCellRenderer extends DefaultTableCellRenderer
{
    /** ��ư */
    JButton button;
    
    /** �޺� �ڽ� ������ �ܰ��� */
    protected Border border;

    /**
     * ������
     */
    public ButtonCellRenderer()
    {
        this("");
    }
    
    /**
     * ��ư�� ���ڷ� ���� ������
     * @param btn
     */    
    public ButtonCellRenderer(String str)
    {
        this.button = new JButton(str);
        this.border = new EmptyBorder(0, 0, 0, 0);
    }

    /**
     * ��ư ������Ʈ�� ��´�.
     * @param table ���̺� ��ü
     * @param value ��ư
     * @param isSelected ���ÿ���
     * @param hasFocus ��Ŀ�� ����
     * @param row ���̺� ��
     * @param col ���̺� ��
     * @return Component ��ư ������Ʈ
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        if(value instanceof String)
        {
            this.button.setText(value+"");
        }
        this.button.setBackground(isSelected && !hasFocus ? table.getSelectionBackground() : table.getBackground());
        this.button.setForeground(isSelected && !hasFocus ? table.getSelectionForeground() : table.getForeground());
        this.button.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.button;
    }
}




