/*
 * Filename : ComboCellRenderer.java
 * Class : ComboCellRenderer
 */
package org.chaos.mgmt.client.ui;

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
 * 콤보박스 셀렌더러 
 * 
 * @version 1.5.0
 * @author Kooin Shin
 */
public class ComboCellEditor extends AbstractCellEditor implements TableCellEditor
{   
    /** 콤보 박스 */ 
    JComboBox comboBox;

    /** 콤보 박스 렌더러 외곽선 */
    protected Border border;
    
    /**
     * 아이템 목록을 인자로 갖는 생성자
     * @param items 아이템 목록
     */
    public ComboCellEditor(Object[] items)
    {
        this(new JComboBox(items));
    }
    
    /**
     * 콤보 박스를 인자로 갖는 생성자
     * @param comboBox 컴보박스
     */
    public ComboCellEditor(JComboBox comboBox)
    {
        this.comboBox = comboBox;
        this.border = new EmptyBorder(0, 0, 0, 0);
    }
    
    /**
     * 선택 인덱스를 얻는다.
     * @return int 선택 인덱스
     */
    public int getSelectedIndex()
    {
        return this.comboBox.getSelectedIndex();
    }
    
    /**
     * 선택 아이템을 얻는다.
     * @return Object 선택 아이템
     */
    public Object getSelectedItem()
    {
        return this.comboBox.getSelectedItem();        
    }
    
    /**
     * 셀 에디터를 얻는다.
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
     * 셀 에디터 값을 얻는다.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    {
        return this.comboBox.getSelectedItem();
    }
    
    /**
     * 에디팅 여부를 얻는다.
     * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
     */
    public boolean isCellEditable(EventObject eo)
    {
        return true;
    }
    
    /**
     * 선택 셀 여부를 얻는다.
     * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
     */
    public boolean shouldSelectCell(EventObject arg0)
    {
        return true;
    }
    
    /**
     * 실 에디팅 정지 여부를 얻는다.
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing()
    {
        super.fireEditingStopped();
        return true;
    }
    
    /**
     * 셀 에디팅 취소여부를 얻는다.
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
        super.fireEditingCanceled();
    }
    
    /**
     * 셀 에디터 리스너를 추가한다.
     * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void addCellEditorListener(CellEditorListener arg0)
    {
    }
    
    /**
     * 셀 에디터 리스너를 삭제한다.
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void removeCellEditorListener(CellEditorListener arg0)
    {
    }
}
