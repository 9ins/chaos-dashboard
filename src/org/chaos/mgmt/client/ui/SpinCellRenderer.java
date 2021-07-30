/*
 * Filename : SpinCellRenderer.java
 * Class : SpinCellRenderer
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;


/**
 * 스핀 셀 랜더러 클래스
 *   
 * @author: Kooin Shin
 * @version: 1.0
 */
public class SpinCellRenderer extends DefaultTableCellRenderer
{
    /** 렌더러 외곽선 */
    protected Border border = new EmptyBorder(0, 0, 0, 0);
    
    /** 컬럼 정보 */
    ColumnData cd;
    
    /**
     * 컬럼 정보를 인자로 갖는 생성자
     * @param cd
     */
    public SpinCellRenderer(ColumnData cd)
    {
        this.cd = cd;        
    }
    
    /**
     * 셀 컴포넌트를 얻는다.
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        SpinField sf = null;
        if(value instanceof SpinField)
        {
            sf = (SpinField)value;            
            sf.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            sf.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());            
            sf.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);            
        }
        return sf;
    }   
}






