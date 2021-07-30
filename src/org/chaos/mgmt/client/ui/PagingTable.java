/*
 * Filename : PagingTable.java
 * Class : PagingTable
 */
package org.chaos.mgmt.client.ui;

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
 * PagingTable 클래스
 * 페이징 테이블을 구현하는 클래스
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class PagingTable extends DefaultTable implements TableModelListener
{
    /** 테이블의 모델이 되는 DefaultTableModel 객체 */
    PagingTableData pm;
    
    /** 위 페이지 버튼 */
    JButton upBtn;
    
    /** 아래 페이지 버튼 */
    JButton downBtn;

    /** 이전 버튼 */
    JButton previousBtn;

    /** 다음 버튼 */
    JButton forwordBtn;
    
    /** 페이지 메시지 라벨 */
    JLabel msgLabel;
    
    /** 아래 패널 */
    JPanel downPanel;
    
    /** 버튼 패널 */
    JPanel buttonPanel;
    /**
     * 컬럼이름을 인자로 갖는 생성자
     * @param columnNames 컬럼이름
     */
    public PagingTable(String[] columnNames)
    {
        this(new PagingTableData(columnNames));
    }

    /**
     * DefaultTableModel 객체를 인자로 갖는 생성자
     * @param pm 테이블 모델 객체
     */
    public PagingTable(PagingTableData pm)
    {
        this(pm, 800, 600);
    }
    
    /**
     * 컬럼이름과 테이블 넓이, 높이를 인자로 갖는 생성자
     * @param columnNames 컬럼이름 배열을 인자로 갖는 생성자
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     */
    public PagingTable(String[] columnNames, int tableWidth, int tableHeight)
    {
        this(new PagingTableData(columnNames), tableWidth, tableHeight);
    }

    /**
     * PagingModel 객체와 테이블 넓이, 높이를 인자로 갖는 생성자
     * @param tpm TableModel 객체
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     */
    public PagingTable(PagingTableData pm, int tableWidth, int tableHeight)
    {
        this(pm, tableWidth, tableHeight, 20);
    }
    
    /**
     * 컬럼 목록, 테이블 넓이, 테이블 높이, 행 높이를 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.DefaultTable#DefaultTable(String[], int, int, int)
     */
    public PagingTable(String[] columnNames, int tableWidth, int tableHeight, int rowHeight)
    {
        this(new PagingTableData(columnNames), tableWidth, tableHeight, rowHeight);        
    }
    
    /**
     * 페이징 모델, 테이블 넓이, 테이블 높이, 행 높이를 인자로 갖는 생성자
     * @param pm 페이징 모델
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     * @param rowHeight 행의 높이
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
     * 컨포넌트 초기화 한다.
     */
    private void initComp()
    {
        downPanel = new JPanel(new GridLayout(0, 2));
        downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.X_AXIS));
        String msg = "총 페이지 수 : "+(pm.getAllPageCount()+1)+"  현재 페이지 : "+pm.getCurrentPage();
        msgLabel = new JLabel(msg);
        downPanel.add(msgLabel);
        
        previousBtn = new JButton("이전");
        forwordBtn = new JButton("다음");
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
     * 버튼을 감춘다.
     */
    public void hideButtons()
    {
        downPanel.setVisible(false);
    }
    
    /**
     * 버튼을 보인다.
     */    
    public void showButtons()
    {
        downPanel.setVisible(true);
    }
    
    /**
     * 테이블에 대한 설정을 한다.
     */
    public void initTable()
    {
        setHorizontalScroll(true);
        setVerticalScroll(true);
        setPageButton();
        setPageLabel();
    }
    
    /**
     * 이벤트 정보를 설정 한다.
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
     * 버튼의 상태를 설정
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
     * 페이징 테이블 데이터를 얻는다.
     * @return 페이징 테이블 데이터
     */
    public PagingTableData getPagingTableData() {
    	return this.pm;
    }
    
    /**
     * 테이블 라벨상태를 셋팅
     */
    public void setPageLabel()
    {
        msgLabel.setText("총 페이지 수 : "+pm.getAllPageCount()+"  현재 페이지 : "+pm.getCurrentPage());
    }

    /**
     * 버튼을 추가한다.
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



