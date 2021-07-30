/*
 * Filename :ButtonCellRenderer.java
 * Class : ButtonCellRenderer
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 테이블 버튼 셀렌더러 
 * @author: Kooin
 * @version: 1.0
 * @since: JDK1.3.1
 */
class ButtonCellRenderer extends DefaultTableCellRenderer
{
    /** 버튼 */
    JButton button;
    
    /** 콤보 박스 렌더러 외곽선 */
    protected Border border;

    /**
     * 생성자
     */
    public ButtonCellRenderer()
    {
        this("");
    }
    
    /**
     * 버튼을 인자로 갖는 생성자
     * @param btn
     */    
    public ButtonCellRenderer(String str)
    {
        this.button = new JButton(str);
        this.border = new EmptyBorder(0, 0, 0, 0);
    }

    /**
     * 버튼 컴포넌트를 얻는다.
     * @param table 테이블 객체
     * @param value 버튼
     * @param isSelected 선택여부
     * @param hasFocus 포커스 여부
     * @param row 테이블 행
     * @param col 테이블 열
     * @return Component 버튼 컨포넌트
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        if(value instanceof String)
        {
            this.button.setText(value+"");
        }
        this.button.setBackground(isSelected && !hasFocus ? table.getSelectionBackground() : table.getBackground());
        this.button.setForeground(isSelected && !hasFocus ? table.getSelectionForeground() : table.getForeground());
        this.button.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.button;
    }
}




