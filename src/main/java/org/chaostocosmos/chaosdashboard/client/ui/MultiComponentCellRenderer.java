/*
 * Filename : MultiComponentCellRenderer.java
 * Class : MultiComponentCellRenderer
 */
package org.chaostocosmos.chaosdashboard.client.ui;

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
 * ���� ������Ʈ �� ������
 * @author Kooin Shin 
 * @since 2003. 8. 23.
 */
public class MultiComponentCellRenderer extends DefaultTableCellRenderer
{
    /** ����������Ʈ �г� */
    JPanel panel;
    
    /** ��ġ Ÿ�� */
    int orientation;
    
    /** �� ũ�� ���� */
    boolean fitToCell;
    
    /** ������Ʈ �ܰ��� */
    Border border;
    
    /** ����������Ʈ ��� */
    MultiComponentElement[] elements;
    
    /**
     * ������
     * @param compNum ������Ʈ ����
     * @param orientation ���Ĺ��
     * @param fitToCell �� ũ�⿡ ���㿩��
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
     * ������ ������Ʈ�� ��´�.
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
