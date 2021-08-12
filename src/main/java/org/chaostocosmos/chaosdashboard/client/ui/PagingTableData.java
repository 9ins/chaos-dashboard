/*
 * Filename : PagingTableData.java
 * Class : PagingTableData
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.util.Vector;


/**
 * PagingModel Ŭ����
 * ����¡ ����� ����ϴ� ���̺��� ���̺� �� Ŭ����
 * 
 * Copyright: (��)�ֿ��������
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class PagingTableData extends DefaultTableData
{
    /** ���� ������ */
    protected int currentPage = 1;
    
    /** �������� ��µ� ���� �� */
    protected int perPage = 1000;
    
    /** �������� ��µ� ���� ����� Vector */
    protected Vector pageData;
    
    /**
     * �÷��̸� �迭�� ���ڷ� ���� ������
     * @param columnNames �÷��̸� �迭
     */
    public PagingTableData(String[] columnNames)
    {
        this(makeDefaultColumnData(columnNames));
    }
    
    /**
     * �÷���ü �迭�� ���ڷ� ���� ������
     * @param columnData �÷���ü �迭
     */
    public PagingTableData(ColumnData[] columnData)
    {
        super(columnData);
        pageData = new Vector();
        setPage(1);
    }
    
    /**
     * ���̺� ���� ���� ��´�.
     * @return ���Ǽ�
     */
    public int getRowCount()
    {
        return pageData == null ? 0 : pageData.size();
    }
    
    /**
     * ������ ���� ���� ��ġ�� �ִ� ��ü�� ��´�.
     * @param row ��
     * @param column ��
     * @return Object ������ ��ġ�� ��ü
     */
    public Object getValueAt(int row, int column)
    {
        if(row<0 || row>pageData.size()) 
            return null;
        Object[] objArr = (Object[])pageData.elementAt(row);
        Object obj = objArr[column];
        return obj;
    }
    
    /**
     * ������ ���� �� ��ġ�� ���� �����Ѵ�.
     * @see javax.swing.table.TableModel#setValueAt(Object, int, int)
     */
    public void setValueAt(Object value, int row, int col)
    {
        int type = columnData[col].getColumnType();
        Object[] o = null;
        switch(type)
        {
            case 1: //�޺��ڽ�
                o = (Object[])pageData.elementAt(row);
                o[col] = (String)value;
                break;
            case 2: //üũ�ڽ�
                o = (Object[])pageData.elementAt(row);
                o[col] = (Boolean)value;
                break;
            case 3: //��ũ�ѹ�
                o = (Object[])pageData.elementAt(row);
                o[col] = (Integer)value;
                break;
            case 4: //����
                o = (Object[])pageData.elementAt(row);
                o[col] = (Integer)value;
                break;
            case 5: //��ư
            case 0: //�Ϲ�
            default ://����Ʈ
                o = (Object[])pageData.elementAt(row);
                o[col] = (String)value;
                break;
        }
        pageData.setElementAt(o, row);
        super.setValueAt(value, (currentPage-1)*perPage+row, col);       
    }
    
    /**
     * �� ��ü�� �߰� �Ѵ�.
     * @param data �� ��ü
     */
    public void addRow(Object data)
    {
        if(pageData.size() < perPage)
        {
            pageData.addElement(data);
        }
        super.tableData.addElement(data);
        fireTableDataChanged();
    }
    
    /**
     * �� ��ü�� �߰� �Ѵ�.
     * @param row �� ��ü
     */
    public void addRow(Object[] row) {
        if(pageData.size() < perPage)
        {
            pageData.addElement(row);
        }
        super.tableData.addElement(row);
        fireTableDataChanged();
    }
    
    /**
     * ���� ������ Vector�� ��´�.
     * @return ������ Vector
     */
    public Vector getPageData()
    {
        return pageData;
    }
    
    /**
     * ���� �������� ��´�.
     * @return ����������
     */
    public int getCurrentPage()
    {
        return this.currentPage;
    }
    
    /**
     * ��� �������� ������ ��´�.
     * @return ��� ��������
     */
    public int getAllPageCount()
    {
        int rest = super.tableData.size()%perPage;
        int count = (rest == 0)?(int)(super.tableData.size()/perPage):(int)(super.tableData.size()/perPage+1);
        return count;
    }
    
    /**
     * �������� �Ʒ���
     */
    public void pageDown()
    {
        if(currentPage < getAllPageCount())
        {
            setPage(++currentPage);
        }
    }
    
    /**
     * �������� ����
     */
    public void pageUp()
    {
        if(currentPage > 1)
        {
            setPage(--currentPage);
        }
    }
    
    /**
     * �������� �־��� �ε��� �������� ����
     * @param page ���� ������
     */
    public void setPage(int page)
    {
        currentPage = page;
        pageData.removeAllElements();
        for(int i=0; i<perPage; i++)
        {
            int idx = (page-1)*perPage+i;
            if(idx >= super.tableData.size())
                break;
            pageData.add(super.tableData.elementAt(idx));
        }
        fireTableDataChanged();
    }
    
    /**
     * �������� ������ ���� ���� �����Ѵ�.
     * @param amount ���� ����
     */
    public void setPerPage(int amount)
    {
        if(amount < 1)
            return;
        this.perPage = amount;
        setPage(1);
        fireTableDataChanged();
    }
    
    /**
     * ������ �����͸� �����Ѵ�.
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableData#setTableData(Vector)
     */
    public void setPageData(Vector data)
    {
        int idx = (currentPage-1)*perPage;
        int count = this.pageData.size();
        for(int i=idx; i<idx+count; i++)
        {
            super.tableData.removeElementAt(i);
        }
        for(int i=idx; i<idx+data.size(); i++)
        {
            super.tableData.add(i, data.elementAt(i-idx));
        }
        this.pageData = data;
        fireTableDataChanged();
    }
}
