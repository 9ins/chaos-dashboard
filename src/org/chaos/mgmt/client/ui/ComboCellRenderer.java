/*
 * Filename : ComboCellRenderer.java
 * Class : ComboCellRenderer
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 콤보 박스 셀렌더러
 * 
 * @author Kooin Shin
 * @version 1.5.0
 */
public class ComboCellRenderer extends DefaultTableCellRenderer
{
    /** 컴보 박스 */
    JComboBox comboBox;
    
    /** 콤보 박스 렌더러 외곽선 */    
    protected Border border;
    
    /**
     * 아이템 목록을 인자로 갖는 생성자
     * @param items 콤보 아이템
     */
    public ComboCellRenderer(Object[] items)
    {
        this(new JComboBox(items));
    }
    
    /**
     * 콤보박스를 인자로 갖는 생성자
     * @param comboBox 콤보 박스
     */
    public ComboCellRenderer(JComboBox comboBox)
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
     * @return Object 선택아이템
     */
    public Object getSelectedItem()
    {
        return this.comboBox.getSelectedItem();
    }
    
    /**
     * 셀 랜더러를 얻는다.
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col)
    {
        if(value instanceof JComboBox)
        {
            this.comboBox = (JComboBox)value;
        }
        this.comboBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        this.comboBox.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        this.comboBox.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        return this.comboBox;
    }
}




