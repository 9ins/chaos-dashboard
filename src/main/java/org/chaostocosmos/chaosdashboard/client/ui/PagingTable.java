/*
 * Filename : PagingTable.java
 * Class : PagingTable
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;


/**
 * PagingTable Ŭ����
 * ����¡ ���̺��� �����ϴ� Ŭ����
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class PagingTable extends DefaultTable implements TableModelListener
{
    /** ���̺��� ���� �Ǵ� DefaultTableModel ��ü */
    PagingTableData pm;
    
    /** �� ������ ��ư */
    JButton upBtn;
    
    /** �Ʒ� ������ ��ư */
    JButton downBtn;

    /** ���� ��ư */
    JButton previousBtn;

    /** ���� ��ư */
    JButton forwordBtn;
    
    /** ������ �޽��� �� */
    JLabel msgLabel;
    
    /** �Ʒ� �г� */
    JPanel downPanel;
    
    /** ��ư �г� */
    JPanel buttonPanel;
    /**
     * �÷��̸��� ���ڷ� ���� ������
     * @param columnNames �÷��̸�
     */
    public PagingTable(String[] columnNames)
    {
        this(new PagingTableData(columnNames));
    }

    /**
     * DefaultTableModel ��ü�� ���ڷ� ���� ������
     * @param pm ���̺� �� ��ü
     */
    public PagingTable(PagingTableData pm)
    {
        this(pm, 800, 600);
    }
    
    /**
     * �÷��̸��� ���̺� ����, ���̸� ���ڷ� ���� ������
     * @param columnNames �÷��̸� �迭�� ���ڷ� ���� ������
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     */
    public PagingTable(String[] columnNames, int tableWidth, int tableHeight)
    {
        this(new PagingTableData(columnNames), tableWidth, tableHeight);
    }

    /**
     * PagingModel ��ü�� ���̺� ����, ���̸� ���ڷ� ���� ������
     * @param tpm TableModel ��ü
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     */
    public PagingTable(PagingTableData pm, int tableWidth, int tableHeight)
    {
        this(pm, tableWidth, tableHeight, 20);
    }
    
    /**
     * �÷� ���, ���̺� ����, ���̺� ����, �� ���̸� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[], int, int, int)
     */
    public PagingTable(String[] columnNames, int tableWidth, int tableHeight, int rowHeight)
    {
        this(new PagingTableData(columnNames), tableWidth, tableHeight, rowHeight);        
    }
    
    /**
     * ����¡ ��, ���̺� ����, ���̺� ����, �� ���̸� ���ڷ� ���� ������
     * @param pm ����¡ ��
     * @param tableWidth ���̺� ����
     * @param tableHeight ���̺� ����
     * @param rowHeight ���� ����
     */    
    public PagingTable(PagingTableData pm, int tableWidth, int tableHeight, int rowHeight)
    {
        super(pm, tableWidth, tableHeight, rowHeight);
        this.pm = pm;
        initComp();
        initTable();
        addListener();
    }
    
    /**
     * ������Ʈ �ʱ�ȭ �Ѵ�.
     */
    private void initComp()
    {
        downPanel = new JPanel(new GridLayout(0, 2));
        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
        String msg = "�� ������ �� : "+(pm.getAllPageCount()+1)+"  ���� ������ : "+pm.getCurrentPage();
        msgLabel = new JLabel(msg);
        downPanel.add(msgLabel);
        
        previousBtn = new JButton("����");
        forwordBtn = new JButton("����");
        previousBtn.setHorizontalAlignment(SwingConstants.RIGHT);
        forwordBtn.setHorizontalAlignment(SwingConstants.RIGHT);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(previousBtn);
        buttonPanel.add(forwordBtn);
        downPanel.add(buttonPanel);
        add(downPanel, BorderLayout.SOUTH);
        upBtn = new JButton("...");
        downBtn = new JButton("...");
        jsp.setCorner(ScrollPaneConstants.UPPER_RIGHT_CORNER, upBtn);
        jsp.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER, downBtn);
    }
    
    /**
     * ��ư�� �����.
     */
    public void hideButtons()
    {
        downPanel.setVisible(false);
    }
    
    /**
     * ��ư�� ���δ�.
     */    
    public void showButtons()
    {
        downPanel.setVisible(true);
    }
    
    /**
     * ���̺��� ���� ������ �Ѵ�.
     */
    public void initTable()
    {
        setHorizontalScroll(true);
        setVerticalScroll(true);
        setPageButton();
        setPageLabel();
    }
    
    /**
     * �̺�Ʈ ������ ���� �Ѵ�.
     */
    private void addListener()
    {
    	this.pm.addTableModelListener(this);
        previousBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pm.pageUp();
                setPageButton();
                setPageLabel();
            }
        });
        forwordBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pm.pageDown();
                setPageButton();
                setPageLabel();
            }
        });
        upBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pm.pageUp();
                setPageButton();
                setPageLabel();
            }
        });
        downBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                pm.pageDown();
                setPageButton();
                setPageLabel();
            }
        });
    }
    
    /**
     * ��ư�� ���¸� ����
     */
    public void setPageButton()
    {
        if(pm.getCurrentPage() > 1)
        {
            upBtn.setVisible(true);
            previousBtn.setEnabled(true);
        }
        else
        {
            upBtn.setVisible(false);
            previousBtn.setEnabled(false);
        }
        if(pm.getCurrentPage() < pm.getAllPageCount())
        {
            downBtn.setVisible(true);
            forwordBtn.setEnabled(true);
        }
        else
        {
            downBtn.setVisible(false);
            forwordBtn.setEnabled(false);
        }
    }
    
    /**
     * ����¡ ���̺� �����͸� ��´�.
     * @return ����¡ ���̺� ������
     */
    public PagingTableData getPagingTableData() {
    	return this.pm;
    }
    
    /**
     * ���̺� �󺧻��¸� ����
     */
    public void setPageLabel()
    {
        msgLabel.setText("�� ������ �� : "+pm.getAllPageCount()+"  ���� ������ : "+pm.getCurrentPage());
    }

    /**
     * ��ư�� �߰��Ѵ�.
     * @param jButton
     */
	public void addButton(JButton jButton) {
		this.buttonPanel.add(jButton);
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		setPageButton();
	}
}



