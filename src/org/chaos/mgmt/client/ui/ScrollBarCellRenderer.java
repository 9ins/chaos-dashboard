/*
 * Filename : ScrollBarCellRenderer.java
 * Class : ScrollBarCellRenderer
 */
package org.chaos.mgmt.client.ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * ScrollBarCellRenderer 클래스
 * JPanel을 상속받아 페널에 테이블을 구현하는 클래스
 * 
 * @author: Kooin-Shin
 * @version: 1.0
 */
class ScrollBarCellRenderer extends DefaultTableCellRenderer
{
    /** 스크롤 바 */
    JScrollBar scrollBar;
    
    /** 셀 외곽선 */    
    protected Border border;
    
    /**
     * Default 생성자
     */
    public ScrollBarCellRenderer()
    {
        this(JScrollBar.HORIZONTAL);
    }
    
    /**
     * 지향방향을 인자로 갖는 생성자
     * @param orientation
     */    
    public ScrollBarCellRenderer(int orientation)
    {
        this.scrollBar = new JScrollBar(orientation);
        this.border = new EmptyBorder(0, 0, 0, 0);        
    }
    
    /**
     * 테이블 셀의 스크롤바 콤포넌트를 얻는다.
     * @param table 테이블
     * @param value 스크롤시 스크롤 크기
     * @param isSelected 선택 여부
     * @param hasFocus 포커스 여부
     * @param row 행
     * @param col 열
     * @return 스크롤바 콤포넌트
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        if(value instanceof Integer)
        {
            Integer intValue = (Integer)value;
            this.scrollBar.setValue(intValue.intValue());
        }
        else
        {
            this.scrollBar.setValue(0);
        }
        this.scrollBar.setBackground(isSelected && !hasFocus ? table.getSelectionBackground() : table.getBackground());
        this.scrollBar.setForeground(isSelected && !hasFocus ? Color.white : table.getForeground());
        this.scrollBar.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.scrollBar;
    }
}
