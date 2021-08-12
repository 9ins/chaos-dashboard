/*
 * Filename : SearchTable.java
 * Class : SearchTable
 */
package org.chaostocosmos.chaosdashboard.client.ui;

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

import org.chaostocosmos.chaosdashboard.common.UtilBox;


/**
 * �˻� ���̺� Ŭ����
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class SearchTable extends DefaultTable
{
    /** �˻��г� */
    JPanel searchPanel;
    
    /** �޺��ڽ� �� */    
    JLabel comboLabel;
    
    /** �˻� �޺��ڽ� */
    JComboBox searchCombo;
    
    /** �˻� �ʵ� �� */    
    JLabel fieldLabel;
    
    /** �˻� �ʵ� */
    JTextField searchField;
    
    /** �˻� ��ư */    
    JButton searchButton;
    
    /** ����� �˻� */
    JCheckBox searchCheck;
    
    /** ��� ��� ���̱� ��ư */
    JButton allButton;    
    
    /** ����� �˻����� */
    boolean isResultSearch;
    
    /** �˻� �� */    
    SearchTableData searchData;
    
    /**
     * �÷� �̸� ����� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[])
     */
    public SearchTable(String[] columnNames)
    {
        this(new SearchTableData(columnNames));
    }
    
    /**
     * �˻� ���� ���ڷ� ���� ������
     * @param searchData
     */    
    public SearchTable(SearchTableData searchData)
    {
        this(searchData, 800, 600);
    }   

    /**
     * �÷� �̸� ���, ���̺� ����, ���̺� ���̸� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[], int, int)
     */    
    public SearchTable(String[] columnNames, int tableWidth, int tableHeight)
    {
        this(new SearchTableData(columnNames), tableWidth, tableHeight);
    }
    
    /**
     * �˻� ��, ���̺� ����, ���̺� ���̸� ���ڷ� ���� ������
     * @param searchData �˻� ����Ÿ �� 
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     */    
    public SearchTable(SearchTableData searchData, int tableWidth, int tableHeight)
    {
        this(searchData, tableWidth, tableHeight, 20);
    }
    
    /**
     * �÷� �̸� ���, ���̺� ����, ���̺� ����, �� ���̸� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[], int, int, int)
     */    
    public SearchTable(String[] columnNames, int tableWidth, int tableHeight, int rowHeight)
    {
        this(new SearchTableData(columnNames), tableWidth, tableHeight, rowHeight);
    }
    
    /**
     * �˻� ��, ���̺� ����, ���̺� ����, �� ���̸� ���ڷ� ���� ������
     * @param searchData �˻� ����Ÿ ��
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     * @param rowHeight ���� ����
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
     * ������Ʈ �ʱ�ȭ
     */    
    private void initComp()
    {
        searchPanel = new JPanel(new FlowLayout());        
        JPanel jp = new JPanel();        
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
        comboLabel = new JLabel("�˻�����");
        String[] strs = searchData.getColumnNames();                
        searchCombo = new JComboBox(strs);
        searchCombo.addItem("��ü�˻�");
        searchCombo.setSelectedItem("��ü�˻�");
        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(searchField.getPreferredSize().width, 22));
        searchButton = new JButton("�˻�����");
        searchButton.setMargin(new Insets(2, 5, 2, 5));
        searchButton.setPreferredSize(new Dimension(70, 22));
        searchCheck = new JCheckBox("����� �˻�");
        allButton = new JButton("��ü����");
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
     * �ʱ�ȭ
     */    
    public void init()
    {
        searchCheck.setSelected(false);
        searchField.setText("");
    }
    
    /**
     * �˻��г��� �����.
     */    
    public void hidePanel()
    {
        searchPanel.setVisible(false);
    }
    
    /**
     * �˻��г��� ���δ�.
     */    
    public void showPanel()
    {
        searchPanel.setVisible(true);
    }
    
    /**
     * ���̺� �ʱ�ȭ
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#initTable()
     */
    private void initTable()
    {
    }
    
    /**
     * ���� �ο� ����� �����.
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
     * �˻� Ű ��Ʈ��, �˻� �ε������� �˻��Ѵ�.
     * @param searchKey �˻� Ű
     * @param idx �ε���
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
     * �̺�Ʈ ������ �߰�
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


