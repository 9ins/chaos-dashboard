/*
 * Filename : SharedTableModel.java
 * Class : SharedTableModel
 */
package org.chaos.mgmt.client.ui;

import java.util.Vector;

/**
 * SharedTableListener 인터페이스
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public interface SharedTableModel
{
    /**
     * 테이블 모델을 설정한다.
     * @param v
     */
    public void setTableData(Vector v);
    
    /**
     * 화면 갱신
     */
    public void updateSharedTableUI();
}
