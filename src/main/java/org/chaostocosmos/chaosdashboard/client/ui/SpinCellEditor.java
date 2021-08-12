/*
 * Filename : SpinCellEditor.java
 * Class : SpinCellEditor
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;


/**
 * SpinFieldCellEditor Ŭ����
 * JScrollBar�� ����ϰ� TableCellEditor�� �����Ǿ� �ִ�.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class SpinCellEditor extends AbstractCellEditor implements TableCellEditor
{
    /** ������ �ܰ��� */
    protected Border border = new EmptyBorder(0, 0, 0, 0);
    
    /** �÷� ���� */
    ColumnData cd;

    /**
     * �÷� ������ ���ڷ� ���� ������
     * @param cd
     */
    public SpinCellEditor(ColumnData cd)
    {
        this.cd = cd;
    }
    
    /**
     * ���̺� ���� Component�� ��´�.
     * @param table ���̺� ��ü
     * @param value ���̺� �� ��
     * @param isSelected ���� ����
     * @param row ���̺� ��
     * @param col ���̺� ��
     * @return Component ������Ʈ
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        SpinField sf = null;
        if(value instanceof SpinField)
        {
            sf = (SpinField)value;
            sf.setBackground(isSelected ? Color.white : table.getBackground());
            sf.setForeground(isSelected ? Color.black : table.getForeground());
            sf.setBorder(table.hasFocus() ? UIManager.getBorder("Table.focusCellHighlightBorder"):border);
        }
        return sf;
    }
    
    /**
     * �� ������ ������
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
     * �� �������� ����Ѵ�.
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
        super.fireEditingCanceled();
    }
    
    /**
     * �� ������ ���� ��´�.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    {
        return null; 
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
     * �� ������ �������θ� ��´�.
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing()
    {
        super.fireEditingStopped();
        return true;
    }
    
    /**
     * ������ ����
     * @param ce
     */
    public void editingStopped(ChangeEvent ce)
    {
    }
    
    /**
     * ������ ���
     * @param arg0
     */
    public void editingCanceled(ChangeEvent arg0)
    {
    }
}
