/*
 * Filename : TableDataSortModel.java
 * Class : TableDataSortModel
 */
package org.chaostocosmos.chaosdashboard.client.ui;

/**
 * ���̺� ������ ��Ʈ �� �������̽�
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public interface TableDataSortModel
{
    /**
     * ���� ���� ����
     */
    public static final int SORT_ASC = 1;

    /**
     * ���� ���� ����
     */
    public static final int SORT_DESC = -1;
    
    /**
     * ���ڷ� �־��� ���� ���� ������� �����Ѵ�.
     * @param sortBy
     * @param sortOrder
     */
    public void sort(int sortBy, int sortOrder);
}
