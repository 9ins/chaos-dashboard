/*
 * Filename : SharedTable.java
 * Class : SharedTable
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


/**
 * ���� ���̺� Ŭ���� 
 * @author Kooin
 * @version 1.0
 * @since JKD1.3.1
 */
public class SharedTable extends DefaultTable implements SharedTableModel, TableModelListener
{
    /** ���� ���̺� �Ŵ��� */
    private static SharedTableManager sharedTableManager;   
        
    /**
     * �÷� �̸� ����� ���ڷ� ���� ������
     * @param columnNames �÷� �̸����
     */
    public SharedTable(String[] columnNames)
    {
        this(new DefaultTableData(columnNames));
    }

    /**
     * ���̺� ���� ���ڷ� ���� ������
     * @param tdm ���̺� ��
     */
    public SharedTable(DefaultTableData atd)
    {
        this(atd, 800, 600, 20);
    }

    /**
     * ���̺� ��, ���̺� ����, ���̺� ����, ���̺� �ο� ���̸� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(TableDataModel, int, int, int)
     */
    public SharedTable(DefaultTableData tdm, int tableWidth, int tableHeight, int tableRowHeight)
    {
        super(tdm, tableWidth, tableHeight, tableRowHeight);
        tdm.addTableModelListener(this);
        if(sharedTableManager == null)
        {
            sharedTableManager = SharedTableManager.createSharedTableManager();
        }
        sharedTableManager.addSharedTableModel(this);
    }   
    
    ////////////////////////////// implementing from SharedTableModel //////////////////////////////
    
    /**
     * ���̺� �����͸� �����Ѵ�.
     * @param v ����
     */
    public void setTableData(Vector v)
    {
        DefaultTableData dtd = (DefaultTableData)getTableModel();
        dtd.replaceTableData(v);
    }
    
    /**
     * �������̺� ȭ�� ������Ʈ
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.SharedTableModel#updateSharedTableUI()
     */
    public void updateSharedTableUI()
    {
        JTable jt = getTable();
        jt.updateUI();
    }

    ////////////////////////////// implementing from TableModelListener //////////////////////////////

    /**
     * ���̺� ������ �����
     * @see javax.swing.event.TableModelListener#tableChanged(javax.swing.event.TableModelEvent)
     */
    public void tableChanged(TableModelEvent tme)
    {
        Object obj = tme.getSource();
        if(obj instanceof DefaultTableData)
        {
            DefaultTableData td = (DefaultTableData)obj;
            sharedTableManager.dispatchTableDataChanged(td.getTableData());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * ���� ���̺� �Ŵ��� 
     * @author Kooin Shin
     */
    private static class SharedTableManager
    {
        /**
         * ���� ���̺� �� 
         */
        SharedTableModel[] sharedModelList;
        
        /**
         * ���� ���̺� �޴��� ������
         * @param sharedModelList ���� ���̺� ��
         */     
        private SharedTableManager(SharedTableModel[] sharedModelList_)
        {
            sharedModelList = sharedModelList_;
        }
        
        /**
         * ���� ���̺� �޴����� ��´�.
         * @return SharedTableManager ���� ���̺� �Ŵ���
         */        
        public static SharedTableManager createSharedTableManager()
        {
            return new SharedTableManager(new SharedTableModel[0]);
        }
        
        /**
         * ���� ���̺� �޴����� ��´�.
         * @param sharedModelList ���� ���̺� ��
         * @return SharedTableManager ���� ���̺� �Ŵ���
         */
        public static SharedTableManager createSharedTableManager(SharedTableModel[] sharedModelList)
        {
            return new SharedTableManager(sharedModelList);
        }
        
        /**
         * ���� ���̺� ���� �߰��Ѵ�.
         * @param sharedModel ���� ���̺� ���� �߰��Ѵ�.
         */
        public void addSharedTableModel(SharedTableModel sharedModel)
        {
            synchronized(this.sharedModelList)
            {
                SharedTableModel[] tmp = new SharedTableModel[sharedModelList.length+1];
                System.arraycopy(sharedModelList, 0, tmp, 0, sharedModelList.length);
                tmp[sharedModelList.length] = sharedModel;
                sharedModelList = tmp;
                tmp = null;
            }
        }

        /**
         * ���ڷ� �־��� �̸��� �����ʸ� �����Ѵ�.
         * @param name ������ �̸�
         */
        public void removeSharedTableModel(String name)
        {
            synchronized(this.sharedModelList)
            {
                SharedTableModel[] tmp = null;
                for(int i=0; i<sharedModelList.length; i++)
                {
                    if(sharedModelList[i].getClass().getName().equals(name))
                    {
                        tmp = new SharedTableModel[sharedModelList.length-1];
                        System.arraycopy(sharedModelList, 0, tmp, 0, i-1);
                        System.arraycopy(sharedModelList, i+1, tmp, i, sharedModelList.length-i);
                    }
                }
                sharedModelList = tmp;
            }
        }

        /**
         * �����ʸ� �����Ѵ�.
         * @param sharedModel ���� ��
         */
        public void removeSharedTableModel(SharedTableModel sharedModel)
        {
            removeSharedTableModel(sharedModel.getClass().getName());
        }
        
        /**
         * ���̺� �� �̺�Ʈ�� �����Ѵ�.
         * @param v ���̺� ������
         */
        public synchronized void dispatchTableDataChanged(Vector v)
        {
            for(int i=0; i<sharedModelList.length; i++)
            {
                sharedModelList[i].setTableData(v);
                sharedModelList[i].updateSharedTableUI();
            }
        }
    }
}





