/*
 * Filename : AbstractTableData.java
 * Class : AbstractTableData
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
    
/**
 * <p>Title : TableData Ŭ���� 
 * <p>Discription : AbstractTableModel Ŭ������ ����Ͽ� ������ TableModel Ŭ����</p>
 * <p>History : 2003/02/10 �ű���, �����ۼ�</p>
 * @version 1.5.0
 * @author Kooin Shin 
 */
public abstract class AbstractTableData extends AbstractTableModel implements TableDataSortModel, ColumnConstants
{
    /** ColumnData �迭 */
    protected ColumnData[] columnData;

    /** �� �� ��ü�� �� ���� */
    protected Vector tableData;
    
    /**
     * �⺻ ������
     * @see java.lang.Object#Object()
     */
    protected AbstractTableData(ColumnData[] columnData)
    {
        super();
        this.columnData = columnData;
        this.tableData = new Vector();
    }

    /**
     * ������ ���� ����̸��� ��´�.
     * @param col ��
     * @return ����̸�
     */
    public String getColumnName(int col)
    {
        return columnData[col].getName()+"";
    }

    /**
     * ��� �÷� �̸������ ��´�.
     * @return String[] �÷��̸� ���
     */
    public String[] getColumnNames()
    {
        String[] names = new String[columnData.length];
        for(int i=0; i<columnData.length; i++)
        {
            names[i] = columnData[i].getName()+"";
        }
        return names;
    }

    /**
     * �÷��̸����� �÷��ε����� ��´�.
     * @param columnName �÷� �̸�
     * @return int �÷� �ε���
     */
    public int getColumnIndex(String columnName)
    {
        int idx = -1;
        for(int i=0; i<columnData.length; i++)
        {
            if(columnName.trim().equals((columnData[i].getName()+"").trim()))
            {
                idx = i;
                break;
            }
        }
        return idx;
    }

    /**
     * ���� ���� ��´�.
     * @return ���� ��
     */
    public int getColumnCount()
    {
        return columnData.length;
    }

    /**
     * ColumnData ��ü �迭�� ��´�.
     * @return �÷� ��ü �迭
     */
    public ColumnData[] getColumnData()
    {
        return this.columnData;
    }

    /**
     * ������ ��ġ�� ColumnData ��ü�� ��´�.
     * @param idx ��
     * @return ColumnData ��ü
     */
    public ColumnData getColumnDataAt(int idx)
    {
        if(idx < 0 || idx > columnData.length)
        {
            return null;
        }
        return columnData[idx];
    }

    /**
     * row, col��ġ�� ���� ��´�.
     * @param row ��
     * @param col ��
     * @return Object ���̺� ��
     */
    public Object getValueAt(int row, int col)
    {
        Object[] rowData = (Object[])tableData.elementAt(row);
        Object val = null;
        switch(columnData[col].getColumnType())
        {
            case CELL_COMBOBOX: //�޺��ڽ�
            val = (JComboBox)rowData[col];
            break;

            case CELL_CHECKBOX: //üũ�ڽ�
            val = (Boolean)rowData[col];
            break;

            case CELL_SCROLLBAR: //��ũ�ѹ�
            val = (Integer)rowData[col];
            break;

            case CELL_SPINFIELD: //�����ʵ�
            val = (SpinField)rowData[col];
            break;
            
            case CELL_DIGITFIELD: //�����ʵ�
            val = (Integer)rowData[col];
            break;
            
            case CELL_MULTI_COMPONENT: //����������Ʈ
            val = (MultiComponentElement[])rowData[col];
            break;
            
            case CELL_BUTTON: //��ư
            case CELL_DEFAULT: // �Ϲ� �ؽ�Ʈ
            default: //����Ʈ
            val = rowData[col];
            break;
        }
        return val;
    }
    
    /**
     * �÷��� ���� ���� �����Ѵ�.
     * @param value �÷���
     * @param row ��
     * @param col ��
     */
    public void setValueAt(Object value, int row, int col)
    {
        Object[] rowData = (Object[])tableData.elementAt(row);
        switch(columnData[col].getColumnType())
        {
            case CELL_COMBOBOX: //�޺��ڽ�
            rowData[col] = (JComboBox)value;
            break;

            case CELL_CHECKBOX: //üũ�ڽ�
            rowData[col] = (Boolean)value;
            break;

            case CELL_SCROLLBAR: //��ũ�ѹ�
            rowData[col] = (Integer)value;
            break;

            case CELL_SPINFIELD: //�����ʵ�
            rowData[col] = (SpinField)value;
            break;

            case CELL_BUTTON: //��ư
            rowData[col] = value;
            break;

            case CELL_DIGITFIELD: //�����ʵ�
            rowData[col] = (Integer)value;
            break;
            
            case CELL_MULTI_COMPONENT: //����������Ʈ
            rowData[col] = (MultiComponentElement[])value;
            break;
            
            case CELL_DEFAULT: // �Ϲ��ؽ�Ʈ
            default: //����Ʈ
            rowData[col] = value;
            break;
        }
        tableData.setElementAt(rowData, row);
        fireTableDataChanged();
    }
    
    /**
     * �÷��� ����� TableCellRenderer�����´�.
     * @param table J���̺� ��ü
     * @param type �÷� Ÿ��
     * @param column �÷� �ε���
     * @return TableCellRenderer ���̺� �� ������
     */
    public TableCellRenderer getTableCellRenderer(JTable table, int type, int column)
    {
        TableCellRenderer renderer = null;
        switch(type)
        {
            case CELL_COMBOBOX: //�޺��ڽ�
            renderer = new ComboCellRenderer(columnData[column].getComboItems());
            break;

            case CELL_CHECKBOX: //üũ�ڽ�
            renderer = new CheckCellRenderer(columnData[column].getAlign());
            break;

            case CELL_SCROLLBAR: //��ũ�ѹ�
            renderer = new ScrollBarCellRenderer();
            break;

            case CELL_SPINFIELD: //�����ʵ�
            renderer = new SpinCellRenderer(columnData[column]);
            break;

            case CELL_BUTTON: //��ư
            renderer = new ButtonCellRenderer();
            break;
            
            case CELL_DIGITFIELD: //���� �ʵ�
            renderer = new DefaultTableCellRenderer();
            break;
            
            case CELL_MULTI_COMPONENT: //����������Ʈ
            renderer = new MultiComponentCellRenderer(columnData[column].getMultiComponentElement().length, columnData[column].getMultiComponentOrientation(), true);
            break;
            
            case CELL_DEFAULT: //�Ϲ� �ؽ�Ʈ
            default:            //����Ʈ
            renderer = new DefaultTableCellRenderer();
            break;
        }
        return renderer;
    }
    
    /**
     * ���̺��� �÷��� ���� ���̺� �� �����͸� �����Ѵ�.
     * @param type �÷� Ÿ��
     * @param column �÷� �ε���
     * @return TableCellEditor ��ü
     */
    public TableCellEditor getTableCellEditor(JTable table, int type, int column)
    {
        TableCellEditor editor = null;
        switch(type)
        {
            case CELL_COMBOBOX: // �޺��ڽ�
            editor = new ComboCellEditor(columnData[column].getComboItems());
            break;

            case CELL_CHECKBOX: // üũ�ڽ�
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(columnData[column].getAlign());
            editor = new DefaultCellEditor(checkBox);
            break;

            case CELL_SCROLLBAR: // ��ũ�ѹ�
            editor = new ScrollBarCellEditor();
            break;

            case CELL_SPINFIELD: // �����ʵ�
            editor = new SpinCellEditor(columnData[column]);
            break;
            
            case CELL_DIGITFIELD: //�����ʵ�
            editor = new DigitFieldCellEditor();
            break;
            
            case CELL_MULTI_COMPONENT: //���� ������Ʈ
            editor = new MultiComponentCellEditor(columnData[column].getMultiComponentElement().length, columnData[column].getMultiComponentOrientation(), true);
            break;
                        
            case CELL_DEFAULT: // �Ϲ��ؽ�Ʈ
            default:
            JTextField jtf = new JTextField();
            jtf.setHorizontalAlignment(JTextField.LEFT);
            editor = new DefaultCellEditor(jtf);
            break;
        }
        return editor;
    }
    
    /**
     * ���ڷ� �־��� �÷� �� ����� ��´�.
     * @param col �÷� �ε���
     * @return Object[] �÷� �� ���
     */
    public Object[] getColumnValues(int col)
    {
        int rownum = tableData.size();
        Object[] objs = new Object[rownum];
        for(int i=0; i<rownum; i++)
            objs[i] = ((Object[])tableData.elementAt(i))[col];
        return objs;
    }
    
    /**
     * ���ڷ� �־��� �÷� �ڵ� ����� ��´�.
     * @param col �÷� �ε���
     * @return Object[] �÷� �� ���
     */
    public Object[] getColumnCodes(int col)
    {
        int rownum = tableData.size();
        Object[] objs = new Object[rownum];
        for(int i=0; i<rownum; i++)
            objs[i] = ((Object[])tableData.elementAt(i))[col];
        return objs;
    }
    
    /**
     * �� ��ü�� �߰� �Ѵ�.
     * @param data �� ��ü
     */
    public void addRow(Object row)
    {
        addRow((Object[])row);
    }
    
    /**
     * �� �߰� �Ѵ�.
     * @param row ���̺� ���� �߰��Ѵ�.
     */
    public void addRow(Object[] row)
    {
        tableData.add(row);
        fireTableDataChanged();
    }
    
    /**
     * ������ �ε��� ��� �÷��� �����͸� ���ͷ� ��´�.
     * @param columns �÷� �ε��� ���
     * @return Vector ���̺� ������ Vector�� ��´�.
     */
    public Vector getTableData(int[] columns)
    {
        return getTableData(this.tableData, columns);
    }
    
    /**
     * ������ �÷� �̸� ��� �����͸� ���ͷ� ��´�.
     * @param columnNames �÷� �̸� ����� ��´�.
     * @return Vector ���̺� ������ Vector�� ��´�.
     */
    public Vector getTableData(String[] columnNames)
    {
        return getTableData(this.tableData, columnNames);
    }

    /**
     * ������ �÷��� ���̺� �����͸� ���Ѵ�.
     * @param source ���̺� ������ ����
     * @param columns �÷� �ε��� ���
     * @return Vector ���̺� ������ ���͸� ��´�.
     */
    public Vector getTableData(Vector source, int[] columns)
    {
        Vector data = new Vector();
        int rowNum = source.size();
        int cnt = 0;
        for(int i=rowNum; --i>=0; cnt++)
        {
            Object[] rows = (Object[])(source.elementAt(cnt));
            Object[] rows_ = new Object[columns.length];
            for(int k=rows_.length; --k>=0;)
                rows_[k] = rows[columns[k]];
            data.add(rows_);
        }
        return data;
    }

    /**
     * ������ �÷��� ���̺� �����͸� ���Ѵ�.
     * @param source ���̺� ����
     * @param columnNames �÷� �̸� ���
     * @return Vector ������ ������
     */
    public Vector getTableData(Vector source, String[] columnNames)
    {
        int[] columns = new int[columnNames.length];
        for(int i=columns.length; --i>=0;)
            columns[i] = getColumnIndex(columnNames[i]);
        return getTableData(source, columns);
    }

    /**
     * �⺻ �÷� ������ �÷� ��ü �迭�� ��´�.
     * @param headers �÷� ����̸�
     * @return ColumnData[] �÷� ������ ���
     */
    public static ColumnData[] makeDefaultColumnData(String[] headers)
    {
        ColumnData[] cd = new ColumnData[headers.length];
        for( int i=0; i<headers.length; i++ )
            cd[i] = new ColumnData(headers[i]);
        return cd;
    }

    /**
     * ���̺� ���� ���� ��´�.
     * @return ���Ǽ�
     */
    public int getRowCount()
    {
        return tableData == null ? 0 : tableData.size();
    }
    
    /**
     * �־��� ��ġ�� �� ��ü�� �����Ѵ�.
     * @param idx �� �ε���
     */
    public void removeElementAt(int idx)
    {
        this.tableData.removeElementAt(idx);
        super.fireTableDataChanged();
    }
    
    /**
     * ���ڷ� �־��� �ε��� ��ġ�� ���� �����Ѵ�.
     * @param idx �� �ε���
     */
    public void removeRow(int idx)
    {
        this.tableData.removeElementAt(idx);
        super.fireTableDataChanged();
    }
    
    /**
     * ���̺��� ��� ��Ҹ� �����Ѵ�.
     */
    public void removeAllRows()
    {
        this.tableData.removeAllElements();
        super.fireTableDataChanged();
    }
    
    /**
     * ���� ��´�.
     * @param row �� �ε���
     */
    public Object getRowValue(int row)
    {
        if (row < 0 || row >= getRowCount())
            return null;
        else
            return tableData.elementAt(row);
    }
    
    /**
     * ���̺� Vector�� ���Ѵ�.
     * @return Vector ��ü
     */
    public Vector getTableData()
    {
        return this.tableData;
    }
    
    /**
     * �־��� Vector�� ���̺� ���� ����
     * @param tableData ���̺� Vector
     */
    public void setTableData(Vector td)
    {
        this.tableData = td;
        fireTableDataChanged();
    }
    
    /**
     * ���̺� �����͸� ��ü�Ѵ�.
     * @param tableData ���̺� ������ 
     */
    public void replaceTableData(Vector tableData)
    {
        this.tableData = tableData;
        fireTableDataChanged();
    }
    
    /**
     * ���� �ο� �ε��� ����� ��´�.
     * @return int[] ���� ���̺� �ο� �ε��� ���
     */
    public int[] getSelectedRowIndexes()
    {
        int column = -1;
        for(int i=columnData.length; --i>=0;)
            if(columnData[i].getColumnType() == CELL_CHECKBOX)
                column = i;
        if(column == -1)
            return null;
        return getSelectedRowIndexes(column);
    }
    
    /**
     * ���� �ο� �ε��� ����� ��´�.
     * @param column �÷� �ε���
     * @return int[] ���� �ο� �ε��� ���
     */
    public int[] getSelectedRowIndexes(int column)
    {
        return getSelectedRowIndexes(this.tableData, column);
    }
    
    /**
     * ���� �ο� �ε��� ����� ��´�.
     * @param data ���̺� ������ ����
     * @param column �÷� �ε��� 
     * @return int[] ���� �ο� �ε��� ����� ��´�.
     */
    public int[] getSelectedRowIndexes(Vector data, int column)
    {
        int type = columnData[column].getColumnType();
        if(type != CELL_CHECKBOX)
            return null;
        int[] indexes = new int[0];
        int cnt = 0;
        for(int i=data.size(); --i>=0; cnt++)
        {
            Object[] row = (Object[])data.elementAt(cnt);
            boolean is = ((Boolean)row[column]).booleanValue();
            if(is)
            {
                int[] tmp = new int[indexes.length+1];
                System.arraycopy(indexes, 0, tmp, 0, indexes.length);
                tmp[indexes.length] = cnt;
                indexes = tmp;
            }
        }
        return indexes;
    }
    
    /**
     * �־��� ���� ���Ĺ������ �����Ѵ�.
     * @param sortBy ���� ��
     * @param sortOrder ���Ĺ��(AbstractTableData.SORT_ASC, AbstractTableData.SORT_DESC)
     */
    public void sort(int sortBy, int sortOrder)
    {
        if( tableData.size() == 0 )
            return;
        if( sortOrder != SORT_ASC && sortOrder != SORT_DESC )
            return;
        Object prev = null;
        Object next = null;
        for( int i=0; i<tableData.size()-1; i++)
        {
            for( int j=1; j<tableData.size(); j++ )
            {
                prev = ((Object[])tableData.elementAt(j-1))[sortBy];
                next = ((Object[])tableData.elementAt(j))[sortBy];
                if( sortOrder == SORT_ASC & prev.hashCode() > next.hashCode() )
                {
                    Object tmp = tableData.elementAt(j-1);
                    tableData.setElementAt(tableData.elementAt(j), j-1);
                    tableData.setElementAt(tmp, j);
                }
                else if( sortOrder == SORT_DESC & prev.hashCode() <= next.hashCode() )
                {
                    Object tmp = tableData.elementAt(j-1);
                    tableData.setElementAt(tableData.elementAt(j), j-1);
                    tableData.setElementAt(tmp, j);
                }
            }
        }
        fireTableDataChanged();
    }
}
