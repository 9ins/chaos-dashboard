/*
 * Filename : MultiComponentCellRenderer.java
 * Class : MultiComponentCellRenderer
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * 다중 컴포넌트 셀 랜더러
 * @author Kooin Shin 
 * @since 2003. 8. 23.
 */
public class MultiComponentCellRenderer extends DefaultTableCellRenderer
{
    /** 다중컴포넌트 패널 */
    JPanel panel;
    
    /** 배치 타입 */
    int orientation;
    
    /** 셀 크기 여부 */
    boolean fitToCell;
    
    /** 컴포넌트 외곽선 */
    Border border;
    
    /** 다중컴포넌트 목록 */
    MultiComponentElement[] elements;
    
    /**
     * 생성자
     * @param compNum 컴포넌트 갯수
     * @param orientation 정렬방식
     * @param fitToCell 셀 크기에 맞춤여부
     */
    public MultiComponentCellRenderer(int compNum, int orientation, boolean fitToCell)
    {
        this.border = new EmptyBorder(0, 0, 0, 0);
        this.fitToCell = fitToCell;
        this.orientation = orientation;
        this.panel = new JPanel();
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
     * 랜더러 컴포넌트를 얻는다.
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
        if(value instanceof MultiComponentElement[])
        {
            if(elements == null)
            {
                this.elements = (MultiComponentElement[])value;
                for(int i=0; i<this.elements.length; i++)
                {
                    Component comp = (Container)elements[i].getMultiComponentElement();
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
        this.panel.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : border);
        //return this.panel;
        return (Component) this.elements[0];
	}
}
