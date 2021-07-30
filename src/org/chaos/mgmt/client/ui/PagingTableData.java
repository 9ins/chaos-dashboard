/*
 * Filename : PagingTableData.java
 * Class : PagingTableData
 */
package org.chaos.mgmt.client.ui;

import java.util.Vector;


/**
 * PagingModel 클래스
 * 페이징 기능을 사용하는 테이블의 테이블 모델 클래스
 * 
 * Copyright: (주)쌍용정보통신
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class PagingTableData extends DefaultTableData
{
    /** 현재 페이지 */
    protected int currentPage = 1;
    
    /** 페이지당 출력될 행의 수 */
    protected int perPage = 1000;
    
    /** 페이지당 출력될 행이 저장될 Vector */
    protected Vector pageData;
    
    /**
     * 컬럼이름 배열을 인자로 갖는 생성자
     * @param columnNames 컬럼이름 배열
     */
    public PagingTableData(String[] columnNames)
    {
        this(makeDefaultColumnData(columnNames));
    }
    
    /**
     * 컬럼객체 배열을 인자로 갖는 생성자
     * @param columnData 컬럼객체 배열
     */
    public PagingTableData(ColumnData[] columnData)
    {
        super(columnData);
        pageData = new Vector();
        setPage(1);
    }
    
    /**
     * 테이블 행의 수를 얻는다.
     * @return 행의수
     */
    public int getRowCount()
    {
        return pageData == null ? 0 : pageData.size();
    }
    
    /**
     * 지정한 열과 행의 위치에 있는 객체를 얻는다.
     * @param row 행
     * @param column 열
     * @return Object 지정한 위치의 객체
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
     * 지정한 열과 행 위치의 값을 설정한다.
     * @see javax.swing.table.TableModel#setValueAt(Object, int, int)
     */
    public void setValueAt(Object value, int row, int col)
    {
        int type = columnData[col].getColumnType();
        Object[] o = null;
        switch(type)
        {
            case 1: //콤보박스
                o = (Object[])pageData.elementAt(row);
                o[col] = (String)value;
                break;
            case 2: //체크박스
                o = (Object[])pageData.elementAt(row);
                o[col] = (Boolean)value;
                break;
            case 3: //스크롤바
                o = (Object[])pageData.elementAt(row);
                o[col] = (Integer)value;
                break;
            case 4: //스핀
                o = (Object[])pageData.elementAt(row);
                o[col] = (Integer)value;
                break;
            case 5: //버튼
            case 0: //일반
            default ://디폴트
                o = (Object[])pageData.elementAt(row);
                o[col] = (String)value;
                break;
        }
        pageData.setElementAt(o, row);
        super.setValueAt(value, (currentPage-1)*perPage+row, col);       
    }
    
    /**
     * 열 객체를 추가 한다.
     * @param data 열 객체
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
     * 열 객체를 추가 한다.
     * @param row 열 객체
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
     * 현재 페이지 Vector를 얻는다.
     * @return 페이지 Vector
     */
    public Vector getPageData()
    {
        return pageData;
    }
    
    /**
     * 현재 페이지를 얻는다.
     * @return 현재페이지
     */
    public int getCurrentPage()
    {
        return this.currentPage;
    }
    
    /**
     * 모든 페이지의 갯수를 얻는다.
     * @return 모든 페이지수
     */
    public int getAllPageCount()
    {
        int rest = super.tableData.size()%perPage;
        int count = (rest == 0)?(int)(super.tableData.size()/perPage):(int)(super.tableData.size()/perPage+1);
        return count;
    }
    
    /**
     * 한페이지 아래로
     */
    public void pageDown()
    {
        if(currentPage < getAllPageCount())
        {
            setPage(++currentPage);
        }
    }
    
    /**
     * 한페이지 위로
     */
    public void pageUp()
    {
        if(currentPage > 1)
        {
            setPage(--currentPage);
        }
    }
    
    /**
     * 페이지를 주어진 인덱스 페이지로 셋팅
     * @param page 셋팅 페이지
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
     * 페이지당 보여줄 행의 수를 셋팅한다.
     * @param amount 행의 수량
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
     * 페이지 데이터를 설정한다.
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
