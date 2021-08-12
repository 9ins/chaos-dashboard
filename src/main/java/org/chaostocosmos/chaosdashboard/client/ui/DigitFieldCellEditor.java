/*
 * Filename : DigitFieldCellEditor.java
 * Class : DigitFieldCellEditor
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 * ���� �ʵ� �� ������
 * 
 * @version 1.0
 * @author Kooin Shin
 */
public class DigitFieldCellEditor implements TableCellEditor
{
    /** ���� �ʵ� */
    DigitField df;

    /** �޺� �ڽ� ������ �ܰ��� */
    protected Border border;
    
    /**
     * ����Ʈ ������
     * @see java.lang.Object#Object()
     */
    public DigitFieldCellEditor()
    {
        this(4);
    }
    
    /**
     * �÷� ����� ���ڷ� ���� ������
     * @param column �÷� ������
     */
    public DigitFieldCellEditor(int column)
    {
        this(column, column);
    }
    
    /**
     * �÷� ũ��, �ִ�ũ�⸦ ���ڷ� ���� ������
     * @param column �÷� ������
     * @param limit �Ѱ� ��
     */
    public DigitFieldCellEditor(int column, int limit)
    {
        this(column, limit, "");
    }
    
    /**
     * �÷� ������, �ִ�ũ��, ���� ��Ʈ���� ���ڷ� ���� ������
     * @param column �÷� ������
     * @param limit �Ѱ� ��
     * @param unit ���� ��Ʈ��
     */    
    public DigitFieldCellEditor(int column, int limit, String unit)
    {
        this.df = new DigitField(column, limit, unit);
    }
    
    /**
     * ���̺� �� ������ ������Ʈ�� ��´�.
     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(JTable, Object, boolean, int, int)
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
        if(value != null)
            this.df.setValue(Integer.parseInt(value+""));
        else
            this.df.setValue(0);
        this.df.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        this.df.setForeground(isSelected ? Color.white : table.getForeground());
        this.df.setBorder(table.hasFocus() ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.df;
    }

    /**
     * �� ������ ���� ��´�.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    {
        return this.df.getDoubleValue()+"";
    }
    
    /**
     * �� ������ ���θ� ��´�.
     * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
     */
    public boolean isCellEditable(EventObject arg0)
    {
        return true;
    }
    
    /**
     * �� ���� ���θ� ��´�.
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
        return true;
    }
    
    /**
     * �� ������ ���
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
    }
    
    /**
     * �� ������ �����ʸ� ��� �Ѵ�.
     * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void addCellEditorListener(CellEditorListener arg0)
    {
    }
    
    /**
     * �� ������ �����ʸ� ���� �Ѵ�.
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void removeCellEditorListener(CellEditorListener arg0)
    {
    }
}



