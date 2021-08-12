/*
 * Filename : SharedTableModel.java
 * Class : SharedTableModel
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.util.Vector;

/**
 * SharedTableListener �������̽�
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public interface SharedTableModel
{
    /**
     * ���̺� ���� �����Ѵ�.
     * @param v
     */
    public void setTableData(Vector v);
    
    /**
     * ȭ�� ����
     */
    public void updateSharedTableUI();
}
