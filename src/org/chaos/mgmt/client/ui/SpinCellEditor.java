/*
 * Filename : SpinCellEditor.java
 * Class : SpinCellEditor
 */
package org.chaos.mgmt.client.ui;

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
 * SpinFieldCellEditor 클래스
 * JScrollBar를 상속하고 TableCellEditor가 구현되어 있다.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class SpinCellEditor extends AbstractCellEditor implements TableCellEditor
{
    /** 렌더러 외곽선 */
    protected Border border = new EmptyBorder(0, 0, 0, 0);
    
    /** 컬럼 정보 */
    ColumnData cd;

    /**
     * 컬럼 정보를 인자로 갖는 생성자
     * @param cd
     */
    public SpinCellEditor(ColumnData cd)
    {
        this.cd = cd;
    }
    
    /**
     * 테이블 셀의 Component를 얻는다.
     * @param table 테이블 객체
     * @param value 테이블 셀 값
     * @param isSelected 선택 여부
     * @param row 테이블 행
     * @param col 테이블 열
     * @return Component 컴포넌트
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
     * 셀 데디터 삭제시
     * @see javax.swing.CellEditor#removeCellEditorListener(CellEditorListener)
     */
    public void removeCellEditorListener(CellEditorListener cel)
    {
    }
    
    /**
     * 셀 에디터 리스너를 추가한다.
     * @see javax.swing.CellEditor#addCellEditorListener(CellEditorListener)
     */    
    public void addCellEditorListener(CellEditorListener cel)
    {
    }
    
    /**
     * 셀 에디팅을 취소한다.
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
        super.fireEditingCanceled();
    }
    
    /**
     * 셀 에디터 값을 얻는다.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    {
        return null; 
    }
    
    /**
     * 셀 에디팅 여부를 얻는다.
     * @see javax.swing.CellEditor#isCellEditable(EventObject)
     */
    public boolean isCellEditable(EventObject eo) 
    {
        return true;
    }
    
    /**
     * 셀 선택여부를 얻는다.
     * @see javax.swing.CellEditor#shouldSelectCell(EventObject)
     */
    public boolean shouldSelectCell(EventObject eo)
    {
        return true;
    }
    
    /**
     * 셀 에디팅 중지여부를 얻는다.
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing()
    {
        super.fireEditingStopped();
        return true;
    }
    
    /**
     * 에디팅 중지
     * @param ce
     */
    public void editingStopped(ChangeEvent ce)
    {
    }
    
    /**
     * 에디팅 취소
     * @param arg0
     */
    public void editingCanceled(ChangeEvent arg0)
    {
    }
}
