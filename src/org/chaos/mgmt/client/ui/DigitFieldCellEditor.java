/*
 * Filename : DigitFieldCellEditor.java
 * Class : DigitFieldCellEditor
 */
package org.chaos.mgmt.client.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 * 숫자 필드 셀 에디터
 * 
 * @version 1.0
 * @author Kooin Shin
 */
public class DigitFieldCellEditor implements TableCellEditor
{
    /** 숫자 필드 */
    DigitField df;

    /** 콤보 박스 렌더러 외곽선 */
    protected Border border;
    
    /**
     * 디폴트 생성자
     * @see java.lang.Object#Object()
     */
    public DigitFieldCellEditor()
    {
        this(4);
    }
    
    /**
     * 컬럼 사이즈를 인자로 갖는 생성자
     * @param column 컬럼 사이즈
     */
    public DigitFieldCellEditor(int column)
    {
        this(column, column);
    }
    
    /**
     * 컬럼 크기, 최대크기를 인자로 갖는 생성자
     * @param column 컬럼 사이즈
     * @param limit 한계 값
     */
    public DigitFieldCellEditor(int column, int limit)
    {
        this(column, limit, "");
    }
    
    /**
     * 컬럼 사이즈, 최대크기, 단위 스트링을 인자로 갖는 생성자
     * @param column 컬럼 사이즈
     * @param limit 한계 값
     * @param unit 단위 스트링
     */    
    public DigitFieldCellEditor(int column, int limit, String unit)
    {
        this.df = new DigitField(column, limit, unit);
    }
    
    /**
     * 테이블 셀 에디터 컴포넌트를 얻는다.
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
     * 셀 에디터 값을 얻는다.
     * @see javax.swing.CellEditor#getCellEditorValue()
     */
    public Object getCellEditorValue()
    {
        return this.df.getDoubleValue()+"";
    }
    
    /**
     * 셀 에디팅 여부를 얻는다.
     * @see javax.swing.CellEditor#isCellEditable(java.util.EventObject)
     */
    public boolean isCellEditable(EventObject arg0)
    {
        return true;
    }
    
    /**
     * 셀 선택 여부를 얻는다.
     * @see javax.swing.CellEditor#shouldSelectCell(java.util.EventObject)
     */
    public boolean shouldSelectCell(EventObject arg0)
    {
        return true;
    }
    
    /**
     * 셀 에디팅 중지 여부를 얻는다.
     * @see javax.swing.CellEditor#stopCellEditing()
     */
    public boolean stopCellEditing()
    {
        return true;
    }
    
    /**
     * 셀 에디팅 취소
     * @see javax.swing.CellEditor#cancelCellEditing()
     */
    public void cancelCellEditing()
    {
    }
    
    /**
     * 셀 에디터 리스너를 등록 한다.
     * @see javax.swing.CellEditor#addCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void addCellEditorListener(CellEditorListener arg0)
    {
    }
    
    /**
     * 셀 에디터 리스너를 제거 한다.
     * @see javax.swing.CellEditor#removeCellEditorListener(javax.swing.event.CellEditorListener)
     */
    public void removeCellEditorListener(CellEditorListener arg0)
    {
    }
}



