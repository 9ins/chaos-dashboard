/*
 * Filename : SharedTable.java
 * Class : SharedTable
 */
package org.chaos.mgmt.client.ui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


/**
 * 공유 테이블 클래스 
 * @author Kooin
 * @version 1.0
 * @since JKD1.3.1
 */
public class SharedTable extends DefaultTable implements SharedTableModel, TableModelListener
{
    /** 공유 테이블 매니저 */
    private static SharedTableManager sharedTableManager;   
        
    /**
     * 컬럼 이름 목록을 인자로 갖는 생성자
     * @param columnNames 컬럼 이름목록
     */
    public SharedTable(String[] columnNames)
    {
        this(new DefaultTableData(columnNames));
    }

    /**
     * 테이블 모델을 인자로 갖는 생성자
     * @param tdm 테이블 모델
     */
    public SharedTable(DefaultTableData atd)
    {
        this(atd, 800, 600, 20);
    }

    /**
     * 테이블 모델, 테이블 넓이, 테이블 높이, 테이블 로우 높이를 인자로 갖는 생성자
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
     * 데이블 데이터를 설정한다.
     * @param v 벡터
     */
    public void setTableData(Vector v)
    {
        DefaultTableData dtd = (DefaultTableData)getTableModel();
        dtd.replaceTableData(v);
    }
    
    /**
     * 공유테이블 화면 업데이트
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.SharedTableModel#updateSharedTableUI()
     */
    public void updateSharedTableUI()
    {
        JTable jt = getTable();
        jt.updateUI();
    }

    ////////////////////////////// implementing from TableModelListener //////////////////////////////

    /**
     * 테이블 데이터 변경시
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
     * 공유 테이블 매니저 
     * @author Kooin Shin
     */
    private static class SharedTableManager
    {
        /**
         * 공유 테이블 모델 
         */
        SharedTableModel[] sharedModelList;
        
        /**
         * 공유 테이블 메니저 생성자
         * @param sharedModelList 공유 테이블 모델
         */     
        private SharedTableManager(SharedTableModel[] sharedModelList_)
        {
            sharedModelList = sharedModelList_;
        }
        
        /**
         * 공유 테이블 메니저를 얻는다.
         * @return SharedTableManager 공유 테이블 매니저
         */        
        public static SharedTableManager createSharedTableManager()
        {
            return new SharedTableManager(new SharedTableModel[0]);
        }
        
        /**
         * 공유 테이블 메니저를 얻는다.
         * @param sharedModelList 공유 테이블 모델
         * @return SharedTableManager 공유 테이블 매니저
         */
        public static SharedTableManager createSharedTableManager(SharedTableModel[] sharedModelList)
        {
            return new SharedTableManager(sharedModelList);
        }
        
        /**
         * 공유 테이블 모델을 추가한다.
         * @param sharedModel 공유 테이블 모델을 추가한다.
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
         * 인자로 주어진 이름의 리스너를 삭제한다.
         * @param name 리스너 이름
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
         * 리스너를 삭제한다.
         * @param sharedModel 공유 모델
         */
        public void removeSharedTableModel(SharedTableModel sharedModel)
        {
            removeSharedTableModel(sharedModel.getClass().getName());
        }
        
        /**
         * 테이블 모델 이벤트를 전파한다.
         * @param v 테이블 데이터
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





