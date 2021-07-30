/*
 * Filename : ScrollBarCellEditor.java
 * Class : ScrollBarCellEditor
 */
package org.chaos.mgmt.client.ui;

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
 * ScrollBarCellEditor 클래스
 * JScrollBar를 상속하고 TableCellEditor가 구현되어 있다.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
class ScrollBarCellEditor extends AbstractCellEditor implements TableCellEditor
{
    /** 스크롤 바 */
    JScrollBar scrollBar;
    
    /** 셀 외곽선 */
    protected Border border;    
    
    /**
     * Default 생성자
     */
    public ScrollBarCellEditor()    
    {
        this(JScrollBar.HORIZONTAL);
    }
    
    /**
     * 지향방향을 인자로 갖는 생성자
     * @param orientation 지향방향
     */
    public ScrollBarCellEditor(int orientation)
    {
        this.scrollBar = new JScrollBar(orientation);
        this.border = new EmptyBorder(0, 0, 0, 0);
    }
    
    /**
     * 테이블 셀의 Component를 얻는다.
     * @param table 테이블 객체
     * @param value 셀 값
     * @param isSelected 선택여부
     * @param row 테이블 행
     * @param col 테이블 열
     * @return 셀 컴포넌트
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
     * 셀 에디터 리스너를 삭제한다.
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
     * 셀 에디딩 취소
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
    }
    
    /**
     * 셀 에디터 값을 얻는다.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    { 
        return new Integer(this.scrollBar.getValue()); 
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
     * 셀 에디딩 중지여부를 얻는다.
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing()
    {
        return true;
    }
}
