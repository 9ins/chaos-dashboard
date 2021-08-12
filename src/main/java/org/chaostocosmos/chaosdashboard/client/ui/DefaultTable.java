/*
 * Filename : DefaultTable.java
 * Class : DefaultTable
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/**
 * DefaultTable Ŭ����
 * JPanel�� ��ӹ޾� ��ο� ���̺��� �����ϴ� Ŭ����
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class DefaultTable extends JPanel implements ColumnConstants
{
    /** ���̺� ��ü */
    JTable table;

    /** ���̺� ��� */
	JTableHeader gtHeader;
    
    /** ���̺��� ���� JScrollPane ��ü */
    JScrollPane jsp;
    
    /** ��ũ�ѹ� ��ü */
    JScrollBar jsb, jsb1;
    
    /** ���̺��� ���� �Ǵ� DefaultTableModel ��ü */
    DefaultTableData dtd;
    
    /** ���̺� �÷� �� */
    TableColumnModel cModel;
    
    /** ���̺� �ش��� ������ �ʺ����� */
    boolean isHeader = true;
    
    /** ���� ��ũ�� ���� */
    boolean isVerticalScroll;
    
    /** ���� ��ũ�� ���� */
    boolean isHorizontalScroll;
    
    /** üũ�ڽ� �÷� ��ü ���ÿ��� */
    boolean selected;
    
    /** ��� ���� */
    int headerHeight;
    
    /** ���̺� ���� */
    int tableWidth;
    
    /** ���̺� ���� */
    int tableHeight;

    /** ���̺� �ο� ���� */
    int tableRowHeight = 20;
    
    /** ���� ���� ��ũ�� ������ */
    int unitV = 15;
    
    /** ���� ���� ��ũ�� ������ */
    int unitH = 15;
    
    /** ���� ���� ��ũ�� ������ */
    int blockV = 500;
    
    /** ���� ���� ��ũ�� ������ */
    int blockH = 500;

    /**
     * �÷��̸��� ���ڷ� ���� ������
     * @param columnNames �÷��̸�
     */
    public DefaultTable(String[] columnNames)
    {
        this(new DefaultTableData(columnNames));
    }

    /**
     * DefaultTableModel ��ü�� ���ڷ� ���� ������
     * @param dtd ���̺� ��
     */
    public DefaultTable(DefaultTableData dtd)
    {
        this(dtd, 800, 600);
    }
    
    /**
     * �÷��̸��� ���̺� ����, ���̸� ���ڷ� ���� ������
     * @param columnNames �÷� �̸� ���
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     */
    public DefaultTable(String[] columnNames, int tableWidth, int tableHeight)
    {
        this(new DefaultTableData(columnNames), tableWidth, tableHeight);
    }

    /**
     * TableDataModel ��ü�� ���̺� ����, ���̸� ���ڷ� ���� ������
     * @param dtd ���̺� ������
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     */
    public DefaultTable(DefaultTableData dtd, int tableWidth, int tableHeight)
    {
        this(dtd, tableWidth, tableHeight, 20);
    }    
    
    /**
     * ���̺� ��, ���̺� �� ���̸� ���ڷ� ���� ������
     * @param dtd ���̺� ������
     * @param tableRowHeight ���̺� �� ����
     */
    public DefaultTable(DefaultTableData dtd, int tableRowHeight)
    {
        this(dtd, 0, 0, tableRowHeight);
    }

    /**
     * �÷��̸��� ���̺� ����, ���̸� ���ڷ� ���� ������
     * @param columnNames �÷� �̸�
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     * @param tableRowHeight ���̺� ���� ����
     */
    public DefaultTable(String[] columnNames, int tableWidth, int tableHeight, int tableRowHeight)
    {
        this(new DefaultTableData(columnNames), tableWidth, tableHeight, tableRowHeight);
    }
    
    /**
     * ���̺� ��, ���̺� ����, ���̺� ����, ���̺� �� ���̸� ���ڷ� ���� ������
     * @param dtd ���̺� ������
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     * @param tableRowHeight ���̺� ���� ����
     */    
    public DefaultTable(DefaultTableData dtd, int tableWidth, int tableHeight, int tableRowHeight)
    {
        super(new BorderLayout());
        this.dtd = dtd;
        this.tableWidth = tableWidth;
        this.tableHeight = tableHeight;
        this.tableRowHeight = tableRowHeight;
        
        initComp();
        initTable();
        addListener();
    }
    
    /**
     * ���̺� �����͸� �����Ѵ�.
     * @param dtd ���̺� ������
     */
    public void setTableData(DefaultTableData dtd)
    {
        removeAllColumns();
        this.dtd = dtd;
        table.setModel(this.dtd);        
        initTable();
        addListener();
    }
    
    /**
     * ������Ʈ �ʱ�ȭ �Ѵ�.
     */
    private void initComp()
    {
        table = new JTable(this.dtd);
        cModel = table.getColumnModel();
        gtHeader = new JTableHeader(cModel);
        table.setTableHeader(gtHeader);
        removeAllColumns();
        jsp = new JScrollPane(table);
        jsb = new JScrollBar(JScrollBar.VERTICAL);
        jsb1 = new JScrollBar(JScrollBar.HORIZONTAL);
        jsb.setUnitIncrement(unitV);
        jsb.setUnitIncrement(unitH);
        jsb.setBlockIncrement(blockV);
        jsb1.setBlockIncrement(blockH);
        jsp.setVerticalScrollBar(jsb);
        jsp.setHorizontalScrollBar(jsb1);
        if(isVerticalScroll) 
            setVerticalScroll(true);
        else 
            setVerticalScroll(false);
        if(isHorizontalScroll) 
            setHorizontalScroll(true);
        else 
            setHorizontalScroll(false);
        add(jsp);
    }
    
    /**
     * ���̺��� ���� ������ �Ѵ�.
     */
    private void initTable()
    {
        if(tableHeight != 0 && tableWidth != 0)
            jsp.setPreferredSize(new Dimension(tableWidth, tableHeight));
        if(tableRowHeight != 0)
            table.setRowHeight(tableRowHeight);
        table.setAutoCreateColumnsFromModel(false);
        addColumns(this.dtd);
        table.getTableHeader().setUpdateTableInRealTime(false);
        if(!isHeader)
            table.getTableHeader().setPreferredSize(new Dimension(0, 0));
    }
    
    /**
     * ���̺� �� ���̸� �����Ѵ�.
     * @param height �� ���̸� �����Ѵ�.
     */
    public void setTableRowHeight(int height)
    {
        this.tableRowHeight = height;
        this.table.setRowHeight(height);
    }
    
    /**
     * ���̺� ��� ���̸� �����Ѵ�.
     * @param height �� ����
     */
    public void setTableHeaderHeight(int height)
    {
        JTableHeader h = table.getTableHeader();
        h.setPreferredSize(new Dimension(h.getPreferredSize().width, height));
    }

    /**
     * ���̺� �÷��� ������Ʈ �Ѵ�.
     */
    public void updateColumns()
    {
        removeAllColumns();
        table.setTableHeader(this.gtHeader);
        addColumns(this.dtd);
    }
    
    /**
     * ���̺� �÷��� �߰��Ѵ�.
     * @param DefaultTableData ���̺� ������
     */
    private void addColumns(DefaultTableData data)
    {
        ColumnData[] kcd = data.getColumnData();
        for(int i=0; i<kcd.length; i++)
        {
            int type = kcd[i].getColumnType();
            TableCellRenderer renderer = data.getTableCellRenderer(table, type, i);
            TableCellEditor editor = data.getTableCellEditor(table, type, i);
            if(renderer instanceof DefaultTableCellRenderer)
            {
                DefaultTableCellRenderer renderer_ = (DefaultTableCellRenderer)renderer;
                renderer_.setHorizontalAlignment(kcd[i].getAlign());
                renderer_.setBackground(kcd[i].getColor());
                renderer_.setBorder(BorderFactory.createRaisedBevelBorder());
                TableColumn tc = new TableColumn(i, kcd[i].getWidth(), renderer_, editor);
                table.addColumn(tc);
            }
            else
            {
                TableColumn tc = new TableColumn(i, kcd[i].getWidth(), renderer, editor);
                table.addColumn(tc);
            }
        }
    }

    /**
     * ���̺� �÷��� ��´�.
     * @param idx �÷� �ε���
     * @return TableColumn ���̺� �÷�
     */
    public TableColumn getColumn(int idx)
    {
        return cModel.getColumn(idx);
    }
    
    /**
     * �÷� Ȱ��ȭ ���θ� �����Ѵ�.
     * @param col ��
     * @param is Ȱ��ȭ ����
     */
    public void setEnableColumn(int col, boolean is)
    {
        ColumnData[] cds = this.dtd.getColumnData();
        cds[col].setEditable(is);
    }
    
    /**
     * ��� ���� ���� �Ѵ�.
     */
    public void removeAllRows()
    {
        this.dtd.removeAllRows();
    }
    
    /**
     * �ε��� ��ġ�� ���� ���� �Ѵ�.
     * @param rowIndex ���̺� �� �ε���
     */    
    public void removeRow(int rowIndex)
    {
        this.dtd.removeRow(rowIndex);
    }

    /**
     * ���̺��� ��� �÷��� �����Ѵ�.
     */
    public void removeAllColumns()
    {
        ColumnData[] cd = this.dtd.getColumnData();
        for(int i=0; i<cd.length; i++)
        {
            try
            {
                removeColumnAt(cd[i].getName());
            }
            catch(Exception e)
            {
            }
        }
    }
    
    /**
     * �÷��� �����Ѵ�.
     * @param name �÷� �̸�
     */
    public void removeColumnAt(Object name)
    {
        TableColumn tc = table.getColumn(name);
        table.removeColumn(tc);
    }
    
    /**
     * �̺�Ʈ ������ ���� �Ѵ�.
     */
    private void addListener()
    {
        table.getTableHeader().addMouseListener(new MouseAdapter()
        {
            /**
             * ���콺 ��ư�� ����������
             * @see java.awt.event.MouseListener#mouseReleased(MouseEvent)
             */        
            public void mouseReleased(MouseEvent me)
            {
                int count = me.getClickCount();
                int col = getTable().getTableHeader().columnAtPoint(me.getPoint());
                if((count == 2) && col == 0)
                {
                    if(dtd.getColumnDataAt(0).getColumnType() == CELL_CHECKBOX)
                    {
                        int rowCnt = getTable().getRowCount();
                        //��ü ������ ������ ���
                        if (selected)
                        {
                            for(int i=0; i<rowCnt; i++)
                                getTable().setValueAt(new Boolean(false), i, 0);
                            selected = false;
                        }
                        //��ü ������ ���
                        else
                        {
                            for(int i=0; i<rowCnt ; i++)
                                getTable().setValueAt(new Boolean(true), i, 0);
                            selected = true;
                        }
                    }
                }
                else
                {
                    //dm.sortByColumn(col);
                    //dtd.sort(col, AbstractTableData.SORT_ASC);
                }
            }            
        });
    }
    
    /**
     * ���콺 �����ʸ� �߰��Ѵ�.
     * @see java.awt.Component#addMouseListener(MouseListener)
     */
    public void addMouseListener(MouseListener ml)
    {
        table.addMouseListener(ml);
    }
    
    /**
     * ���̺� �� ��ü�� ��´�
     * @return ���̺� �� ��ü
     */
    public DefaultTableData getTableModel()
    {
        return this.dtd;
    }
    
    /**
     * ���̺� ��ü�� ��´�
     * @return ���̺� ��ü
     */
    public JTable getTable()
    {
        return this.table;
    }
    
    /** 
     * ��ũ�� �� ��ü�� ��´�.
     * @return ScrollPane��ü
     */
    public JScrollPane getScrollPane()
    {
        return this.jsp;
    }
    
    /**
     * ���� ��ũ�ѹ� ����
     * @param isVerticalScroll ���� ��ũ�ѹ� ��������
     */
    public void setVerticalScroll(boolean is)
    {
        this.isVerticalScroll = is;
        if(isVerticalScroll) 
            jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        else 
            jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
    
    /**
     * ���� ��ũ�ѹ� ����
     * @param isHorizontalScroll ���� ��ũ�ѹ� ��������
     */
    public void setHorizontalScroll(boolean is)
    {
        this.isHorizontalScroll = is;
        if(isHorizontalScroll) 
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        else 
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    
    /**
     * ��� ����
     * @param isHeader ��� ����
     */
    public void setHeader(boolean isHeader)
    {
        this.isHeader = isHeader;
        if(!isHeader) 
            table.getTableHeader().setPreferredSize(new Dimension(0, 0));
    }
    
    /**
     * ���̺� �÷� ���� ��´�.
     * @return TableColumnModel ���̺� �÷� ��
     */
    public TableColumnModel getTableColumnModel()
    {
        return table.getColumnModel();
    }
    
    /**
     * ���� ��ũ���� ũ�⸦ ����
     * @param vSize ���� ���� ��ũ�� ũ��
     * @param hSize ���� ���� ��ũ�� ũ��
     */
    public void setBlockScrollSize(int vSize, int hSize)
    {
        blockV = vSize;
        blockH = hSize;
        jsb.setBlockIncrement(blockV);
        jsb1.setBlockIncrement(blockH);
    }
    
    /**
     * ���� ��ũ�� ũ�⸦ ����
     * @param vSize ���� ���� ��ũ�� ������
     * @param hSize ���� ���� ��ũ�� ������
     */
    public void setUnitScrollSize(int vSize, int hSize)
    {
        unitV = vSize;
        unitH = hSize;
        jsb.setUnitIncrement(unitV);
        jsb1.setBlockIncrement(unitH);
    }
    
    /**
     * ���������� ��ũ���� �ű��.
     * @param orientation
     */
    public void setToLast(int orientation) {
    	if(orientation == JScrollBar.VERTICAL)
    		jsb.setValue(jsb.getMaximum());
    	else if(orientation == JScrollBar.HORIZONTAL)
    		jsb1.setValue(jsb.getMaximum());
    	else
    		throw new IllegalArgumentException("Not valid argument : "+orientation);
    }
    
    /**
     * ���̺� �� ������
     * @param listener ������
     */
    public void addTableModelListener(TableModelListener listener) {
    	this.dtd.addTableModelListener(listener);
    }    
}
