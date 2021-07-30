/*
 * Filename : SearchTableData.java
 * Class : SearchTableData
 */
package org.chaos.mgmt.client.ui;

import java.util.Vector;


/**
 * 테이블 검색 데이터 모델 클래스
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class SearchTableData extends DefaultTableData
{
    /** 검색 데이터 벡터 */
    Vector searchData;
    
    /**
     * 컬럼이름 목록을 인자로 갖는 생성자
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableDataModel#TableDataModel(String[])
     */
    public SearchTableData(String[] columnNames)
    {
        this(makeDefaultColumnData(columnNames));
    }
    
    /**
     * 컬럼 데이터를 인자로 갖는 생성자 
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableDataModel#TableDataModel(ColumnData[])
     */
    public SearchTableData(ColumnData[] columnData)
    {
        super(columnData);
        this.searchData = new Vector();
    }

    /**
     * 테이블 행의 수를 얻는다.
     * @return 행의수
     */
    public int getRowCount()
    {
        return searchData == null ? 0 : searchData.size();
    }

    /**
     * 지정한 열과 행의 위치에 있는 객체를 얻는다.
     * @param row 행
     * @param column 열
     * @return Object 지정한 위치의 객체
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
     * 지정한 열과 행 위치의 값을 설정한다.
     * @see javax.swing.table.TableModel#setValueAt(Object, int, int)
     */
    public void setValueAt(Object value, int row, int col)
    {
        int type = columnData[col].getColumnType();
        Object[] o = null;
        switch(type)
        {
            case CELL_COMBOBOX: //콤보박스
                o = (Object[])searchData.elementAt(row);
                o[col] = (String)value;
                break;
            case CELL_CHECKBOX: //체크박스
                o = (Object[])searchData.elementAt(row);
                o[col] = (Boolean)value;
                break;
            case CELL_SCROLLBAR: //스크롤바
                o = (Object[])searchData.elementAt(row);
                o[col] = (Integer)value;
                break;
            case CELL_SPINFIELD: //스핀
                o = (Object[])searchData.elementAt(row);
                o[col] = (Integer)value;
                break;
            default ://디폴트
                o = (Object[])searchData.elementAt(row);
                o[col] = (String)value;
                break;
        }
        searchData.setElementAt(o, row);
    }

    /**
     * 열 객체를 추가 한다.
     * @param data 열 객체
     */
    public void addRow(Object data)
    {
        this.searchData.addElement(data);
        super.tableData.addElement(data);
        fireTableDataChanged();
    }    
    
    /**
     * 테이블 데이터를 설정한다.
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableData#setTableData(Vector)
     */
    public void setSearchTableData(Vector data)
    {
        this.searchData = data;
        this.fireTableDataChanged();
    }
    
    /**
     * 데이블 데이터를 설정한다.
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.AbstractTableData#setTableData(Vector)
     */
    public void setTableData(Vector data)
    {
        super.setTableData(data);
        setSearchTableData(data);
    }
    
    /**
     * 테이블 데이터를 얻는다.
     * @see com.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.TableData#getTableData()
     */
    public Vector getSearchTableData()
    {
        return this.searchData;
    }
    
    /**
     * 검색어로 매칭 로우 인덱스 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return int[] 검색 인덱스 목록
     */ 
    public int[] getMatchRowIndexesFromResult(String searchString)
    {
        return getMatchRowIndexes(this.searchData, searchString);
    }
    
    /**
     * 검색어로 매칭 로우 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return Vector 검색 벡터
     */ 
    public Vector getMatchRowsFromResult(String searchString)
    {
        return getMatchRows(this.searchData, searchString);
    }
    
    /**
     * 검색어로 매칭 로우 인덱스 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return int[] 재검색 인덱스 목록
     */
    public int[] getMatchRowIndexesFromResult(int column, String searchString)
    {
        return getMatchRowIndexes(this.searchData, column, searchString);
    }

    /**
     * 검색어로 매칭 로우 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return Vector 검색 벡터
     */
    public Vector getMatchRowsFromResult(int column, String searchString)
    {
        return getMatchRows(this.searchData, column, searchString);
    }

    /**
     * 검색어로 매칭 로우 인덱스 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return int[]검색 인덱스 목록
     */
    public int[] getMatchRowIndexes(String searchString)
    {
        return getMatchRowIndexes(super.tableData, searchString);
    }

    /**
     * 검색어로 매칭 로우 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return Vector 검색 벡터
     */
    public Vector getMatchRows(String searchString)
    {
        return getMatchRows(super.tableData, searchString);
    }

    /**
     * 검색어로 매칭 로우 인덱스 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return int[] 검색 인덱스
     */
    public int[] getMatchRowIndexes(int column, String searchString)
    {
        return getMatchRowIndexes(super.tableData, column, searchString);
    }

    /**
     * 검색어로 매칭 로우 목록을 얻는다.
     * @param searchString 검색 스트링
     * @return Vector 검색 벡터
     */
    public Vector getMatchRows(int column, String searchString)
    {
        return getMatchRows(super.tableData, column, searchString);
    }

    /**
     * 검색어가 있는 모든 행 인덱스 목록을 얻는다.
     * @param data 테이블 데이터
     * @param searchString 거색 스트링
     * @return int[] 검색 인덱스 목록
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
     * 검색어가 있는 모든 행 목록 벡터를 얻는다.
     * @param data 검색 데이터
     * @param searchString 검색 스트링
     * @return Vector 검색 벡터
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
     * 지정한 컬럼 중 검색어가 있는 행 목록 인덱스를 얻는다.
     * @param data 검색 데이터
     * @param columnIndex 컬럼 인덱스
     * @param searchString 검색 스트링
     * @return int[] 검색 인덱스 목록
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
     * 지정한 컬럼 중 검색어가 있는 행 목록 백터를 얻는다.
     * @param data 검색 데이터
     * @param columnIndex 컬럼 인덱스
     * @param searchString 검색 스트링
     * @return Vector 검색 벡터
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
