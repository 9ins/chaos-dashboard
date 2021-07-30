/*
 * Filename : TableDataSortModel.java
 * Class : TableDataSortModel
 */
package org.chaos.mgmt.client.ui;

/**
 * 데이블 데이터 소트 모델 인터페이스
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public interface TableDataSortModel
{
    /**
     * 오름 차순 정렬
     */
    public static final int SORT_ASC = 1;

    /**
     * 내림 차순 정렬
     */
    public static final int SORT_DESC = -1;
    
    /**
     * 인자로 주어진 열과 정렬 방법으로 소팅한다.
     * @param sortBy
     * @param sortOrder
     */
    public void sort(int sortBy, int sortOrder);
}
