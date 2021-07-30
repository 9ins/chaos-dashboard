/*
 * Filename : SearchTable.java
 * Class : SearchTable
 */
package org.chaos.mgmt.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import org.chaos.mgmt.common.UtilBox;


/**
 * 검색 테이블 클래스
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class SearchTable extends DefaultTable
{
    /** 검색패널 */
    JPanel searchPanel;
    
    /** 콤보박스 라벨 */    
    JLabel comboLabel;
    
    /** 검색 콤보박스 */
    JComboBox searchCombo;
    
    /** 검색 필드 라벨 */    
    JLabel fieldLabel;
    
    /** 검색 필드 */
    JTextField searchField;
    
    /** 검색 버튼 */    
    JButton searchButton;
    
    /** 결과내 검색 */
    JCheckBox searchCheck;
    
    /** 모든 목록 보이기 버튼 */
    JButton allButton;    
    
    /** 결과내 검색여부 */
    boolean isResultSearch;
    
    /** 검색 모델 */    
    SearchTableData searchData;
    
    /**
     * 컬럼 이름 목록을 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[])
     */
    public SearchTable(String[] columnNames)
    {
        this(new SearchTableData(columnNames));
    }
    
    /**
     * 검색 모델을 인자로 갖는 생성자
     * @param searchData
     */    
    public SearchTable(SearchTableData searchData)
    {
        this(searchData, 800, 600);
    }   

    /**
     * 컬럼 이름 목록, 테이블 넓이, 테이블 높이를 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[], int, int)
     */    
    public SearchTable(String[] columnNames, int tableWidth, int tableHeight)
    {
        this(new SearchTableData(columnNames), tableWidth, tableHeight);
    }
    
    /**
     * 검색 모델, 테이블 넓이, 테이블 높이를 인자로 갖는 생성자
     * @param searchData 검색 데이타 모델 
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     */    
    public SearchTable(SearchTableData searchData, int tableWidth, int tableHeight)
    {
        this(searchData, tableWidth, tableHeight, 20);
    }
    
    /**
     * 컬럼 이름 목록, 테이블 넓이, 테이블 높이, 행 높이를 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[], int, int, int)
     */    
    public SearchTable(String[] columnNames, int tableWidth, int tableHeight, int rowHeight)
    {
        this(new SearchTableData(columnNames), tableWidth, tableHeight, rowHeight);
    }
    
    /**
     * 검색 모델, 테이블 넓이, 테이블 높이, 행 높이를 인자로 갖는 생성자
     * @param searchData 검색 데이타 모델
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     * @param rowHeight 행의 높이
     */
    public SearchTable(SearchTableData searchData, int tableWidth, int tableHeight, int rowHeight)
    {
        super(searchData, tableWidth, tableHeight, rowHeight);
        this.searchData = searchData;
        initComp();
        initTable();
        addListener();
    }

    /**
     * 컴포넌트 초기화
     */    
    private void initComp()
    {
        searchPanel = new JPanel(new FlowLayout());        
        JPanel jp = new JPanel();        
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
        comboLabel = new JLabel("검색조건");
        String[] strs = searchData.getColumnNames();                
        searchCombo = new JComboBox(strs);
        searchCombo.addItem("전체검색");
        searchCombo.setSelectedItem("전체검색");
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(searchField.getPreferredSize().width, 22));
        searchButton = new JButton("검색시작");
        searchButton.setMargin(new Insets(2, 5, 2, 5));
        searchButton.setPreferredSize(new Dimension(70, 22));
        searchCheck = new JCheckBox("결과내 검색");
        allButton = new JButton("전체보기");
        allButton.setMargin(new Insets(2, 5, 2, 5));
        allButton.setPreferredSize(new Dimension(70, 22));        
        jp.add(comboLabel);
        jp.add(Box.createHorizontalStrut(5));
        jp.add(searchCombo);
        jp.add(Box.createHorizontalStrut(5));
        jp.add(searchField);
        jp.add(Box.createHorizontalStrut(5));
        jp.add(searchButton);
        jp.add(Box.createHorizontalStrut(5));
        jp.add(searchCheck);
        jp.add(Box.createHorizontalStrut(5));
        jp.add(allButton);        
        searchPanel.add(jp, "Center");
        add(searchPanel, BorderLayout.NORTH);
        init();
    }
    
    /**
     * 초기화
     */    
    public void init()
    {
        searchCheck.setSelected(false);
        searchField.setText("");
    }
    
    /**
     * 검색패널을 감춘다.
     */    
    public void hidePanel()
    {
        searchPanel.setVisible(false);
    }
    
    /**
     * 검색패널을 보인다.
     */    
    public void showPanel()
    {
        searchPanel.setVisible(true);
    }
    
    /**
     * 테이블 초기화
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#initTable()
     */
    private void initTable()
    {
    }
    
    /**
     * 선택 로우 목록을 만든다.
     */
    public void sortSelectedRows()
    {
        Vector data = searchData.getTableData();
        Vector data_ = new Vector();
        for(int i=0; i<data.size(); i++)
        {
            Object[] o = (Object[])data.elementAt(i);
            if(((Boolean)o[0]).booleanValue())
            {
                data_.add(data.remove(i));
                i--;
            }
        }
        Vector v = UtilBox.addVectorToVector(data_, data);
        searchData.setTableData(v);
    }
    
    /**
     * 검색 키 스트링, 검색 인덱스를로 검색한다.
     * @param searchKey 검색 키
     * @param idx 인덱스
     */    
    private void search(String searchKey, int idx)
    {
        Vector data = null;
        if(idx != -1)
        {
            if(!isResultSearch)
            {
                data = searchData.getMatchRows(idx, searchKey);
            }
            else
            {
                data = searchData.getMatchRowsFromResult(idx, searchKey);
            }
        }
        else
        {
            if(!isResultSearch)
            {
                data = searchData.getMatchRows(searchKey);
            }
            else
            {
                data = searchData.getMatchRowsFromResult(searchKey);
            }
        }
        searchData.setSearchTableData(data);
    } 
        
    /**
     * 이벤트 리스너 추가
     */    
    private void addListener()
    {
        searchField.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String keyStr = searchField.getText();
                int column = searchData.getColumnIndex(searchCombo.getSelectedItem()+"");               
                search(keyStr, column);
            }
        });
        
        searchButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String keyStr = searchField.getText();
                int column = searchData.getColumnIndex(searchCombo.getSelectedItem()+"");               
                search(keyStr, column);
            }
        });
        
        searchCheck.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(isResultSearch)
                {
                    isResultSearch = false;
                }
                else
                {
                    isResultSearch = true;
                }
            }
        });
        
        allButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(searchData.getColumnData()[0].getColumnType() == ColumnData.CELL_CHECKBOX)
                {
                    sortSelectedRows();                                
                }
                searchData.setSearchTableData(searchData.getTableData());                
            }
        });
    }
}


