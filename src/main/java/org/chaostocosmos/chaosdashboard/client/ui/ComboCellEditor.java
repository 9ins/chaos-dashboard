/*
 * Filename : ComboCellRenderer.java
 * Class : ComboCellRenderer
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 * �޺��ڽ� �������� 
 * 
 * @version 1.5.0
 * @author Kooin Shin
 */
public class ComboCellEditor extends AbstractCellEditor implements TableCellEditor
{   
    /** �޺� �ڽ� */ 
    JComboBox comboBox;

    /** �޺� �ڽ� ������ �ܰ��� */
    protected Border border;
    
    /**
     * ������ ����� ���ڷ� ���� ������
     * @param items ������ ���
     */
    public ComboCellEditor(Object[] items)
    {
        this(new JComboBox(items));
    }
    
    /**
     * �޺� �ڽ��� ���ڷ� ���� ������
     * @param comboBox �ĺ��ڽ�
     */
    public ComboCellEditor(JComboBox comboBox)
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
     * @return Object ���� ������
     */
    public Object getSelectedItem()
    {
        return this.comboBox.getSelectedItem();        
    }
    
    /**
     * �� �����͸� ��´�.
     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        if(value instanceof JComboBox)
        {
            this.comboBox = (JComboBox)value;
        }
        this.comboBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        this.comboBox.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        this.comboBox.setBorder(table.hasFocus() ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.comboBox;
    }
    
    /**
     * �� ������ ���� ��´�.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    {
        return this.comboBox.getSelectedItem();
    }
    
    /**
     * ������ ���θ� ��´�.
     * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
     */
    public boolean isCellEditable(EventObject eo)
    {
        return true;
    }
    
    /**
     * ���� �� ���θ� ��´�.
     * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
     */
    public boolean shouldSelectCell(EventObject arg0)
    {
        return true;
    }
    
    /**
     * �� ������ ���� ���θ� ��´�.
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing()
    {
        super.fireEditingStopped();
        return true;
    }
    
    /**
     * �� ������ ��ҿ��θ� ��´�.
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
        super.fireEditingCanceled();
    }
    
    /**
     * �� ������ �����ʸ� �߰��Ѵ�.
     * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void addCellEditorListener(CellEditorListener arg0)
    {
    }
    
    /**
     * �� ������ �����ʸ� �����Ѵ�.
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void removeCellEditorListener(CellEditorListener arg0)
    {
    }
}
