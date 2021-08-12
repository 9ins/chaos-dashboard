/*
 * Filename : SearchTableData.java
 * Class : SearchTableData
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.util.Vector;


/**
 * ���̺� �˻� ������ �� Ŭ����
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class SearchTableData extends DefaultTableData
{
    /** �˻� ������ ���� */
    Vector searchData;
    
    /**
     * �÷��̸� ����� ���ڷ� ���� ������
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableDataModel#TableDataModel(String[])
     */
    public SearchTableData(String[] columnNames)
    {
        this(makeDefaultColumnData(columnNames));
    }
    
    /**
     * �÷� �����͸� ���ڷ� ���� ������ 
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableDataModel#TableDataModel(ColumnData[])
     */
    public SearchTableData(ColumnData[] columnData)
    {
        super(columnData);
        this.searchData = new Vector();
    }

    /**
     * ���̺� ���� ���� ��´�.
     * @return ���Ǽ�
     */
    public int getRowCount()
    {
        return searchData == null ? 0 : searchData.size();
    }

    /**
     * ������ ���� ���� ��ġ�� �ִ� ��ü�� ��´�.
     * @param row ��
     * @param column ��
     * @return Object ������ ��ġ�� ��ü
     */
    public Object getValueAt(int row, int column)
    {
        if(row<0 || row>searchData.size())
            return null;
        Object[] objArr = (Object[])searchData.elementAt(row);
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
            case CELL_COMBOBOX: //�޺��ڽ�
                o = (Object[])searchData.elementAt(row);
                o[col] = (String)value;
                break;
            case CELL_CHECKBOX: //üũ�ڽ�
                o = (Object[])searchData.elementAt(row);
                o[col] = (Boolean)value;
                break;
            case CELL_SCROLLBAR: //��ũ�ѹ�
                o = (Object[])searchData.elementAt(row);
                o[col] = (Integer)value;
                break;
            case CELL_SPINFIELD: //����
                o = (Object[])searchData.elementAt(row);
                o[col] = (Integer)value;
                break;
            default ://����Ʈ
                o = (Object[])searchData.elementAt(row);
                o[col] = (String)value;
                break;
        }
        searchData.setElementAt(o, row);
    }

    /**
     * �� ��ü�� �߰� �Ѵ�.
     * @param data �� ��ü
     */
    public void addRow(Object data)
    {
        this.searchData.addElement(data);
        super.tableData.addElement(data);
        fireTableDataChanged();
    }    
    
    /**
     * ���̺� �����͸� �����Ѵ�.
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableData#setTableData(Vector)
     */
    public void setSearchTableData(Vector data)
    {
        this.searchData = data;
        this.fireTableDataChanged();
    }
    
    /**
     * ���̺� �����͸� �����Ѵ�.
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.AbstractTableData#setTableData(Vector)
     */
    public void setTableData(Vector data)
    {
        super.setTableData(data);
        setSearchTableData(data);
    }
    
    /**
     * ���̺� �����͸� ��´�.
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableData#getTableData()
     */
    public Vector getSearchTableData()
    {
        return this.searchData;
    }
    
    /**
     * �˻���� ��Ī �ο� �ε��� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return int[] �˻� �ε��� ���
     */ 
    public int[] getMatchRowIndexesFromResult(String searchString)
    {
        return getMatchRowIndexes(this.searchData, searchString);
    }
    
    /**
     * �˻���� ��Ī �ο� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return Vector �˻� ����
     */ 
    public Vector getMatchRowsFromResult(String searchString)
    {
        return getMatchRows(this.searchData, searchString);
    }
    
    /**
     * �˻���� ��Ī �ο� �ε��� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return int[] ��˻� �ε��� ���
     */
    public int[] getMatchRowIndexesFromResult(int column, String searchString)
    {
        return getMatchRowIndexes(this.searchData, column, searchString);
    }

    /**
     * �˻���� ��Ī �ο� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return Vector �˻� ����
     */
    public Vector getMatchRowsFromResult(int column, String searchString)
    {
        return getMatchRows(this.searchData, column, searchString);
    }

    /**
     * �˻���� ��Ī �ο� �ε��� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return int[]�˻� �ε��� ���
     */
    public int[] getMatchRowIndexes(String searchString)
    {
        return getMatchRowIndexes(super.tableData, searchString);
    }

    /**
     * �˻���� ��Ī �ο� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return Vector �˻� ����
     */
    public Vector getMatchRows(String searchString)
    {
        return getMatchRows(super.tableData, searchString);
    }

    /**
     * �˻���� ��Ī �ο� �ε��� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return int[] �˻� �ε���
     */
    public int[] getMatchRowIndexes(int column, String searchString)
    {
        return getMatchRowIndexes(super.tableData, column, searchString);
    }

    /**
     * �˻���� ��Ī �ο� ����� ��´�.
     * @param searchString �˻� ��Ʈ��
     * @return Vector �˻� ����
     */
    public Vector getMatchRows(int column, String searchString)
    {
        return getMatchRows(super.tableData, column, searchString);
    }

    /**
     * �˻�� �ִ� ��� �� �ε��� ����� ��´�.
     * @param data ���̺� ������
     * @param searchString �Ż� ��Ʈ��
     * @return int[] �˻� �ε��� ���
     */
    public int[] getMatchRowIndexes(Vector data, String searchString)
    {
        int[] indexes = new int[0];
        for(int i=0; i<data.size(); i++)
        {
            Object[] row = (Object[])data.elementAt(i);
            for(int j=0; j<row.length; j++)
            {
                String val = row[j]+"";
                int idx = val.indexOf(searchString);
                if(idx != -1)
                {
                    int[] indexes_ = new int[indexes.length+1];
                    System.arraycopy(indexes, 0, indexes_, 0, indexes.length);
                    indexes_[indexes.length] = i;
                    indexes = indexes_;
                    break;
                }
            }
        }
        return indexes;
    }
    
    /**
     * �˻�� �ִ� ��� �� ��� ���͸� ��´�.
     * @param data �˻� ������
     * @param searchString �˻� ��Ʈ��
     * @return Vector �˻� ����
     */    
    public Vector getMatchRows(Vector data, String searchString)
    {
        Vector v = new Vector();
        int[] indexes = getMatchRowIndexes(data, searchString);
        for(int i=0; i<indexes.length; i++)
        {
            v.add(data.elementAt(indexes[i]));
        }
        return v;
    }
    
    /**
     * ������ �÷� �� �˻�� �ִ� �� ��� �ε����� ��´�.
     * @param data �˻� ������
     * @param columnIndex �÷� �ε���
     * @param searchString �˻� ��Ʈ��
     * @return int[] �˻� �ε��� ���
     */ 
    public int[] getMatchRowIndexes(Vector data, int columnIndex, String searchString)
    {
        if(data == null)
            return null;
        int[] indexes = new int[0];
        if(columnIndex < super.getColumnCount())
        {
            for(int i=0; i<data.size(); i++)
            {
                Object[] row = (Object[])data.elementAt(i);
                String val = row[columnIndex]+"";
                int idx = val.indexOf(searchString);
                if(idx != -1)
                {
                    int[] indexes_ = new int[indexes.length+1];
                    System.arraycopy(indexes, 0, indexes_, 0, indexes.length);
                    indexes_[indexes.length] = i;
                    indexes = indexes_;
                }
            }
        }
        return indexes;
    }
    
    /**
     * ������ �÷� �� �˻�� �ִ� �� ��� ���͸� ��´�.
     * @param data �˻� ������
     * @param columnIndex �÷� �ε���
     * @param searchString �˻� ��Ʈ��
     * @return Vector �˻� ����
     */    
    public Vector getMatchRows(Vector data, int columnIndex, String searchString)
    {
        Vector v = new Vector();
        int[] indexes = getMatchRowIndexes(data, columnIndex, searchString);
        for(int i=0; i<indexes.length; i++)
        {
            v.add(data.elementAt(indexes[i]));                        
        }   
        return v;
    }    
}
