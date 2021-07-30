/*
 * Filename : AbstractTableData.java
 * Class : AbstractTableData
 */
package org.chaos.mgmt.client.ui;

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
 * <p>Title : TableData 클래스 
 * <p>Discription : AbstractTableModel 클래스를 상속하여 구성한 TableModel 클래스</p>
 * <p>History : 2003/02/10 신구인, 최초작성</p>
 * @version 1.5.0
 * @author Kooin Shin 
 */
public abstract class AbstractTableData extends AbstractTableModel implements TableDataSortModel, ColumnConstants
{
    /** ColumnData 배열 */
    protected ColumnData[] columnData;

    /** 각 행 객체가 들어갈 백터 */
    protected Vector tableData;
    
    /**
     * 기본 생성자
     * @see java.lang.Object#Object()
     */
    protected AbstractTableData(ColumnData[] columnData)
    {
        super();
        this.columnData = columnData;
        this.tableData = new Vector();
    }

    /**
     * 지정한 열의 헤더이름을 얻는다.
     * @param col 열
     * @return 헤더이름
     */
    public String getColumnName(int col)
    {
        return columnData[col].getName()+"";
    }

    /**
     * 모든 컬럼 이름목록을 얻는다.
     * @return String[] 컬럼이름 목록
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
     * 컬럼이름으로 컬럼인덱스를 얻는다.
     * @param columnName 컬럼 이름
     * @return int 컬럼 인덱스
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
     * 열의 수를 얻는다.
     * @return 열의 수
     */
    public int getColumnCount()
    {
        return columnData.length;
    }

    /**
     * ColumnData 객체 배열을 얻는다.
     * @return 컬럼 객체 배열
     */
    public ColumnData[] getColumnData()
    {
        return this.columnData;
    }

    /**
     * 지정한 위치의 ColumnData 객체를 얻는다.
     * @param idx 열
     * @return ColumnData 객체
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
     * row, col위치의 값을 얻는다.
     * @param row 행
     * @param col 열
     * @return Object 테이블 값
     */
    public Object getValueAt(int row, int col)
    {
        Object[] rowData = (Object[])tableData.elementAt(row);
        Object val = null;
        switch(columnData[col].getColumnType())
        {
            case CELL_COMBOBOX: //콤보박스
            val = (JComboBox)rowData[col];
            break;

            case CELL_CHECKBOX: //체크박스
            val = (Boolean)rowData[col];
            break;

            case CELL_SCROLLBAR: //스크롤바
            val = (Integer)rowData[col];
            break;

            case CELL_SPINFIELD: //스핀필드
            val = (SpinField)rowData[col];
            break;
            
            case CELL_DIGITFIELD: //숫자필드
            val = (Integer)rowData[col];
            break;
            
            case CELL_MULTI_COMPONENT: //다중컴포넌트
            val = (MultiComponentElement[])rowData[col];
            break;
            
            case CELL_BUTTON: //버튼
            case CELL_DEFAULT: // 일반 텍스트
            default: //디폴트
            val = rowData[col];
            break;
        }
        return val;
    }
    
    /**
     * 컬럼에 따른 값을 셋팅한다.
     * @param value 컬럼값
     * @param row 행
     * @param col 열
     */
    public void setValueAt(Object value, int row, int col)
    {
        Object[] rowData = (Object[])tableData.elementAt(row);
        switch(columnData[col].getColumnType())
        {
            case CELL_COMBOBOX: //콤보박스
            rowData[col] = (JComboBox)value;
            break;

            case CELL_CHECKBOX: //체크박스
            rowData[col] = (Boolean)value;
            break;

            case CELL_SCROLLBAR: //스크롤바
            rowData[col] = (Integer)value;
            break;

            case CELL_SPINFIELD: //스핀필드
            rowData[col] = (SpinField)value;
            break;

            case CELL_BUTTON: //버튼
            rowData[col] = value;
            break;

            case CELL_DIGITFIELD: //숫자필드
            rowData[col] = (Integer)value;
            break;
            
            case CELL_MULTI_COMPONENT: //다중컴포넌트
            rowData[col] = (MultiComponentElement[])value;
            break;
            
            case CELL_DEFAULT: // 일반텍스트
            default: //디폴트
            rowData[col] = value;
            break;
        }
        tableData.setElementAt(rowData, row);
        fireTableDataChanged();
    }
    
    /**
     * 컬럼에 사용할 TableCellRenderer가져온다.
     * @param table J테이블 객체
     * @param type 컬럼 타입
     * @param column 컬럼 인덱스
     * @return TableCellRenderer 테이블 셀 랜더러
     */
    public TableCellRenderer getTableCellRenderer(JTable table, int type, int column)
    {
        TableCellRenderer renderer = null;
        switch(type)
        {
            case CELL_COMBOBOX: //콤보박스
            renderer = new ComboCellRenderer(columnData[column].getComboItems());
            break;

            case CELL_CHECKBOX: //체크박스
            renderer = new CheckCellRenderer(columnData[column].getAlign());
            break;

            case CELL_SCROLLBAR: //스크롤바
            renderer = new ScrollBarCellRenderer();
            break;

            case CELL_SPINFIELD: //스핀필드
            renderer = new SpinCellRenderer(columnData[column]);
            break;

            case CELL_BUTTON: //버튼
            renderer = new ButtonCellRenderer();
            break;
            
            case CELL_DIGITFIELD: //숫자 필드
            renderer = new DefaultTableCellRenderer();
            break;
            
            case CELL_MULTI_COMPONENT: //다중컴포넌트
            renderer = new MultiComponentCellRenderer(columnData[column].getMultiComponentElement().length, columnData[column].getMultiComponentOrientation(), true);
            break;
            
            case CELL_DEFAULT: //일반 텍스트
            default:            //디폴트
            renderer = new DefaultTableCellRenderer();
            break;
        }
        return renderer;
    }
    
    /**
     * 테이블의 컬럼에 따른 테이블 셀 에디터를 리턴한다.
     * @param type 컬럼 타입
     * @param column 컬럼 인덱스
     * @return TableCellEditor 객체
     */
    public TableCellEditor getTableCellEditor(JTable table, int type, int column)
    {
        TableCellEditor editor = null;
        switch(type)
        {
            case CELL_COMBOBOX: // 콤보박스
            editor = new ComboCellEditor(columnData[column].getComboItems());
            break;

            case CELL_CHECKBOX: // 체크박스
            JCheckBox checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(columnData[column].getAlign());
            editor = new DefaultCellEditor(checkBox);
            break;

            case CELL_SCROLLBAR: // 스크롤바
            editor = new ScrollBarCellEditor();
            break;

            case CELL_SPINFIELD: // 스핀필드
            editor = new SpinCellEditor(columnData[column]);
            break;
            
            case CELL_DIGITFIELD: //숫자필드
            editor = new DigitFieldCellEditor();
            break;
            
            case CELL_MULTI_COMPONENT: //다중 컴포넌트
            editor = new MultiComponentCellEditor(columnData[column].getMultiComponentElement().length, columnData[column].getMultiComponentOrientation(), true);
            break;
                        
            case CELL_DEFAULT: // 일반텍스트
            default:
            JTextField jtf = new JTextField();
            jtf.setHorizontalAlignment(JTextField.LEFT);
            editor = new DefaultCellEditor(jtf);
            break;
        }
        return editor;
    }
    
    /**
     * 인자로 주어진 컬럼 값 목록을 얻는다.
     * @param col 컬럼 인덱스
     * @return Object[] 컬럼 값 목록
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
     * 인자로 주어진 컬럼 코드 목록을 얻는다.
     * @param col 컬럼 인덱스
     * @return Object[] 컬럼 값 목록
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
     * 열 객체를 추가 한다.
     * @param data 열 객체
     */
    public void addRow(Object row)
    {
        addRow((Object[])row);
    }
    
    /**
     * 열 추가 한다.
     * @param row 테이블 열을 추가한다.
     */
    public void addRow(Object[] row)
    {
        tableData.add(row);
        fireTableDataChanged();
    }
    
    /**
     * 지정한 인덱스 목록 컬럼의 데이터를 백터로 얻는다.
     * @param columns 컬럼 인덱스 목록
     * @return Vector 테이블 데이터 Vector를 얻는다.
     */
    public Vector getTableData(int[] columns)
    {
        return getTableData(this.tableData, columns);
    }
    
    /**
     * 지정한 컬럼 이름 목록 데이터를 벡터로 얻는다.
     * @param columnNames 컬럼 이름 목록을 얻는다.
     * @return Vector 테이블 데이터 Vector를 얻는다.
     */
    public Vector getTableData(String[] columnNames)
    {
        return getTableData(this.tableData, columnNames);
    }

    /**
     * 지정한 컬럼의 테이블 데이터를 구한다.
     * @param source 테이블 데이터 벡터
     * @param columns 컬럼 인덱스 목록
     * @return Vector 테이블 데이터 벡터를 얻는다.
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
     * 지정한 컬럼의 테이블 데이터를 구한다.
     * @param source 테이블 벡터
     * @param columnNames 컬럼 이름 목록
     * @return Vector 데이터 데이터
     */
    public Vector getTableData(Vector source, String[] columnNames)
    {
        int[] columns = new int[columnNames.length];
        for(int i=columns.length; --i>=0;)
            columns[i] = getColumnIndex(columnNames[i]);
        return getTableData(source, columns);
    }

    /**
     * 기본 컬럼 정보로 컬럼 객체 배열을 얻는다.
     * @param headers 컬럼 헤더이름
     * @return ColumnData[] 컬럼 데이터 목록
     */
    public static ColumnData[] makeDefaultColumnData(String[] headers)
    {
        ColumnData[] cd = new ColumnData[headers.length];
        for( int i=0; i<headers.length; i++ )
            cd[i] = new ColumnData(headers[i]);
        return cd;
    }

    /**
     * 테이블 행의 수를 얻는다.
     * @return 행의수
     */
    public int getRowCount()
    {
        return tableData == null ? 0 : tableData.size();
    }
    
    /**
     * 주어진 위치의 열 객체를 삭제한다.
     * @param idx 열 인덱스
     */
    public void removeElementAt(int idx)
    {
        this.tableData.removeElementAt(idx);
        super.fireTableDataChanged();
    }
    
    /**
     * 인자로 주어진 인덱스 위치의 행을 삭제한다.
     * @param idx 열 인덱스
     */
    public void removeRow(int idx)
    {
        this.tableData.removeElementAt(idx);
        super.fireTableDataChanged();
    }
    
    /**
     * 테이블의 모든 요소를 삭제한다.
     */
    public void removeAllRows()
    {
        this.tableData.removeAllElements();
        super.fireTableDataChanged();
    }
    
    /**
     * 행을 얻는다.
     * @param row 열 인덱스
     */
    public Object getRowValue(int row)
    {
        if (row < 0 || row >= getRowCount())
            return null;
        else
            return tableData.elementAt(row);
    }
    
    /**
     * 테이블 Vector를 구한다.
     * @return Vector 객체
     */
    public Vector getTableData()
    {
        return this.tableData;
    }
    
    /**
     * 주어진 Vector를 테이블 값에 셋팅
     * @param tableData 테이블 Vector
     */
    public void setTableData(Vector td)
    {
        this.tableData = td;
        fireTableDataChanged();
    }
    
    /**
     * 테이블 데이터를 대체한다.
     * @param tableData 테이블 데이터 
     */
    public void replaceTableData(Vector tableData)
    {
        this.tableData = tableData;
        fireTableDataChanged();
    }
    
    /**
     * 선택 로우 인덱스 목록을 얻는다.
     * @return int[] 선택 테이블 로우 인덱스 목록
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
     * 선택 로우 인덱스 목록을 얻는다.
     * @param column 컬럼 인덱스
     * @return int[] 선택 로우 인덱스 목록
     */
    public int[] getSelectedRowIndexes(int column)
    {
        return getSelectedRowIndexes(this.tableData, column);
    }
    
    /**
     * 선택 로우 인덱스 목록을 얻는다.
     * @param data 테이블 데이터 백터
     * @param column 컬럼 인덱스 
     * @return int[] 선택 로우 인덱스 목록을 얻는다.
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
     * 주어진 열과 정렬방식으로 정렬한다.
     * @param sortBy 선택 열
     * @param sortOrder 정렬방식(AbstractTableData.SORT_ASC, AbstractTableData.SORT_DESC)
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
