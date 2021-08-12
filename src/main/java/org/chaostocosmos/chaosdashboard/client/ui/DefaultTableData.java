/*
 * Filename : DefaultTableData.java
 * Class : DefaultTableData
 */
package org.chaostocosmos.chaosdashboard.client.ui;


/**
 * DefaultTable�� �� Ŭ����. 
 * DefaultTable�� �⺻���� ���°� �����Ǿ� �ִ�.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class DefaultTableData extends AbstractTableData
{
    /**
     * Column String ��ü �迭�� ���ڷ� ���� ������
     * @param columnName �ķ� String �迭
     */
    public DefaultTableData(String[] columnName)
    {
        this(makeDefaultColumnData(columnName));
    }
    
    /**
     * ColumnData��ü �迭�� ���ڷ� ���� ������
     * @param columnData �ķ� ��ü �迭
     */
    public DefaultTableData(ColumnData[] columnData)
    {
        super(columnData);
    }
    
    /**
     * �־��� �ε����� �ο츦 �����Ѵ�.
     * @param idx �ε���
     */
    public void removeRow(int idx)
    {
        tableData.remove(idx);
        fireTableDataChanged();
    }
    
    /**
     * row, col��ġ ���� ���� ���� ���θ� ����
     * @param row ��
     * @param col ��
     * @return ���� ����
     */
    public boolean isCellEditable(int row, int col)
    {
        if(super.columnData[col].isEditable())
            return true;
        else
            return false;
    }
}







