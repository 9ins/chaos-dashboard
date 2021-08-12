/*
 * Filename : ScrollBarCellEditor.java
 * Class : ScrollBarCellEditor
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 * ScrollBarCellEditor Ŭ����
 * JScrollBar�� ����ϰ� TableCellEditor�� �����Ǿ� �ִ�.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
class ScrollBarCellEditor extends AbstractCellEditor implements TableCellEditor
{
    /** ��ũ�� �� */
    JScrollBar scrollBar;
    
    /** �� �ܰ��� */
    protected Border border;    
    
    /**
     * Default ������
     */
    public ScrollBarCellEditor()    
    {
        this(JScrollBar.HORIZONTAL);
    }
    
    /**
     * ��������� ���ڷ� ���� ������
     * @param orientation �������
     */
    public ScrollBarCellEditor(int orientation)
    {
        this.scrollBar = new JScrollBar(orientation);
        this.border = new EmptyBorder(0, 0, 0, 0);
    }
    
    /**
     * ���̺� ���� Component�� ��´�.
     * @param table ���̺� ��ü
     * @param value �� ��
     * @param isSelected ���ÿ���
     * @param row ���̺� ��
     * @param col ���̺� ��
     * @return �� ������Ʈ
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        if(value instanceof Integer)
        {
            Integer intValue = (Integer)value;
            this.scrollBar.setValue(intValue.intValue());
        }
        this.scrollBar.setBackground(isSelected && !table.hasFocus() ? table.getSelectionBackground() : table.getBackground());
        this.scrollBar.setForeground(isSelected && !table.hasFocus() ? Color.white : table.getForeground());
        this.scrollBar.setBorder(table.hasFocus() ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.scrollBar;
    }
    
    /**
     * �� ������ �����ʸ� �����Ѵ�.
     * @see javax.swing.CellEditor#removeCellEditorListener(CellEditorListener)
     */
    public void removeCellEditorListener(CellEditorListener cel)
    {
    }
    
    /**
     * �� ������ �����ʸ� �߰��Ѵ�.
     * @see javax.swing.CellEditor#addCellEditorListener(CellEditorListener)
     */    
    public void addCellEditorListener(CellEditorListener cel)
    {
    }
    
    /**
     * �� ����� ���
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
    }
    
    /**
     * �� ������ ���� ��´�.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    { 
        return new Integer(this.scrollBar.getValue()); 
    }
    
    /**
     * �� ������ ���θ� ��´�.
     * @see javax.swing.CellEditor#isCellEditable(EventObject)
     */
    public boolean isCellEditable(EventObject eo) 
    {
        return true;
    }
    
    /**
     * �� ���ÿ��θ� ��´�.
     * @see javax.swing.CellEditor#shouldSelectCell(EventObject)
     */
    public boolean shouldSelectCell(EventObject eo)
    {
        return true;
    }
    
    /**
     * �� ����� �������θ� ��´�.
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing()
    {
        return true;
    }
}
