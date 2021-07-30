/*
 * Filename : DefaultTableData.java
 * Class : DefaultTableData
 */
package org.chaos.mgmt.client.ui;


/**
 * DefaultTable의 모델 클래스. 
 * DefaultTable의 기본적인 형태가 구현되어 있다.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class DefaultTableData extends AbstractTableData
{
    /**
     * Column String 객체 배열을 인자로 갖는 생성자
     * @param columnName 컴럼 String 배열
     */
    public DefaultTableData(String[] columnName)
    {
        this(makeDefaultColumnData(columnName));
    }
    
    /**
     * ColumnData객체 배열을 인자로 갖는 생성자
     * @param columnData 컴럼 객체 배열
     */
    public DefaultTableData(ColumnData[] columnData)
    {
        super(columnData);
    }
    
    /**
     * 주어진 인덱스의 로우를 삭제한다.
     * @param idx 인덱스
     */
    public void removeRow(int idx)
    {
        tableData.remove(idx);
        fireTableDataChanged();
    }
    
    /**
     * row, col위치 셀의 편집 가능 여부를 결정
     * @param row 행
     * @param col 열
     * @return 가능 여부
     */
    public boolean isCellEditable(int row, int col)
    {
        if(super.columnData[col].isEditable())
            return true;
        else
            return false;
    }
}







