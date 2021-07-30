/*
 * Filename : CheckCellRenderer.java
 * Class : CheckCellRenderer
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * 체크 박스 테이블 셀렌더러
 * @copyright: sicc
 * @author Kooin
 * @version 1.0
 * @since JDK1.3.1
 */
class CheckCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer
{
    /** 체크박스 */
    JCheckBox checkBox;
        
    /** 포커스가 아닐때 외곽선 */
    protected Border border;
    
    /**
     * 디폴트 생성자
     * @see java.lang.Object#Object()
     */
    public CheckCellRenderer()
    {
        this("");
    }
    
    /**
     * 정렬방식을 인자로 갖는 생성자
     * @param align 정렬방식
     */
    public CheckCellRenderer(int align)
    {
        this("", align);
    }
    
    /**
     * 체크 스트링을 인자로 갖는 생성자
     * @param str 체크 스트링
     */
    public CheckCellRenderer(String str)
    {
        this(str, JCheckBox.CENTER);
    }
    
    /**
     * 체크 스트링, 정렬방식을 인자로 갖는 생성자
     * @param str 체크 스트링
     * @param align 정렬방식
     */    
    public CheckCellRenderer(String str, int align)
    {
        this.checkBox = new JCheckBox(str);
        this.checkBox.setHorizontalAlignment(align);
        border = new EmptyBorder(0, 0, 0, 0);
    }
    
    /**
     * 테이블 셀의 컨포넌트를 얻는다.
     * @param table 테이블
     * @param value 체크박스의 체크상테
     * @param isSelected 체크여부
     * @param hasFocus 포커스 여부
     * @param row 행
     * @param col 열
     * @return 테이블 셀 컨포넌트
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,  int row, int col)
    {
        if(value instanceof Boolean)
        {
            this.checkBox.setSelected(((Boolean)value).booleanValue());
        }
        this.checkBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        this.checkBox.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());        
        this.checkBox.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.checkBox;
    }
}


