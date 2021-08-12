/*
 * Filename : MultiComponentCellEditor.java
 * Class : MultiComponentCellEditor
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 * ���� ������Ʈ �� ������
 * @author Kooin Shin
 * @since 2003. 8. 23.
 */
public class MultiComponentCellEditor extends AbstractCellEditor implements TableCellEditor
{
    /** ���� ������Ʈ �г� */
    JPanel panel;
    
    /** �� ũ�� ���� */
    boolean fitToCell;
    
    /** ������Ʈ ��ġ */
    int orientation;

    /** ������Ʈ �ܰ��� */
    Border border;
    
    /** ���� ������Ʈ ��� */
    MultiComponentElement[] elements;
    
    /**
     * ������
     * @param compNum ������Ʈ ����
     * @param orientation ���� ���
     * @param fitToCell �� ũ�⿡ ���㿩��
     */
    public MultiComponentCellEditor(int compNum, int orientation, boolean fitToCell)
    {
        this.panel = new JPanel();
        this.border = new EmptyBorder(0, 0, 0, 0);
        this.fitToCell = fitToCell;
        this.orientation = orientation;
        if(orientation == ColumnConstants.ORIENTATION_Y_AXIS)
        {
            this.panel.setLayout(new GridLayout(compNum, 1));
        }
        else
        {
            this.panel.setLayout(new GridLayout(1, compNum));
        }
    }
    
    /**
     * �� �����͸� ��´�.
     * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        if(value instanceof MultiComponentElement[])
        {
            if(this.elements == null)
            {
                this.elements = (MultiComponentElement[])value;
                for(int i=0; i<elements.length; i++)
                {
                    Component comp = elements[i].getMultiComponentElement();
                    if(this.fitToCell)
                    {
                        Container cont = (Container)comp;
                        cont.setLayout(new BoxLayout(cont, orientation));
                    }
                    this.panel.add(comp);
                }
            }
        }
        this.panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        this.panel.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        this.panel.setBorder(table.hasFocus() ? UIManager.getBorder("Table.focusCellHighlightBorder") : this.border);
        //return this.panel;
        return (Component) this.elements[0];
    }

    /**
     * �� ������ ���� ��´�.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    {
        return this.elements;
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
