/*
 * Filename : DefaultTable.java
 * Class : DefaultTable
 */
package org.chaos.mgmt.client.ui;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


/**
 * DefaultTable 클래스
 * JPanel을 상속받아 페널에 테이블을 구현하는 클래스
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class DefaultTable extends JPanel implements ColumnConstants
{
    /** 테이블 객체 */
    JTable table;

    /** 테이블 헤더 */
	JTableHeader gtHeader;
    
    /** 테이블이 붙을 JScrollPane 객체 */
    JScrollPane jsp;
    
    /** 스크롤바 객체 */
    JScrollBar jsb, jsb1;
    
    /** 테이블의 모델이 되는 DefaultTableModel 객체 */
    DefaultTableData dtd;
    
    /** 테이블 컬럼 모델 */
    TableColumnModel cModel;
    
    /** 테이블 해더를 보일지 않보일지 */
    boolean isHeader = true;
    
    /** 세로 스크롤 여부 */
    boolean isVerticalScroll;
    
    /** 가로 스크롤 여부 */
    boolean isHorizontalScroll;
    
    /** 체크박스 컬럼 전체 선택여부 */
    boolean selected;
    
    /** 헤더 높이 */
    int headerHeight;
    
    /** 테이블 넓이 */
    int tableWidth;
    
    /** 테이블 높이 */
    int tableHeight;

    /** 테이블 로우 높이 */
    int tableRowHeight = 20;
    
    /** 유닛 세로 스크롤 사이즈 */
    int unitV = 15;
    
    /** 유닛 가로 스크롤 사이즈 */
    int unitH = 15;
    
    /** 블록 세로 스크롤 사이즈 */
    int blockV = 500;
    
    /** 블록 가로 스크롤 사이즈 */
    int blockH = 500;

    /**
     * 컬럼이름을 인자로 갖는 생성자
     * @param columnNames 컬럼이름
     */
    public DefaultTable(String[] columnNames)
    {
        this(new DefaultTableData(columnNames));
    }

    /**
     * DefaultTableModel 객체를 인자로 갖는 생성자
     * @param dtd 테이블 모델
     */
    public DefaultTable(DefaultTableData dtd)
    {
        this(dtd, 800, 600);
    }
    
    /**
     * 컬럼이름과 테이블 넓이, 높이를 인자로 갖는 생성자
     * @param columnNames 컬럼 이름 목록
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     */
    public DefaultTable(String[] columnNames, int tableWidth, int tableHeight)
    {
        this(new DefaultTableData(columnNames), tableWidth, tableHeight);
    }

    /**
     * TableDataModel 객체와 테이블 넓이, 높이를 인자로 갖는 생성자
     * @param dtd 테이블 데이터
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     */
    public DefaultTable(DefaultTableData dtd, int tableWidth, int tableHeight)
    {
        this(dtd, tableWidth, tableHeight, 20);
    }    
    
    /**
     * 테이블 모델, 테이블 행 높이를 이자로 갖는 생성자
     * @param dtd 테이블 테이터
     * @param tableRowHeight 테이블 행 높이
     */
    public DefaultTable(DefaultTableData dtd, int tableRowHeight)
    {
        this(dtd, 0, 0, tableRowHeight);
    }

    /**
     * 컬럼이름과 테이블 넓이, 높이를 인자로 갖는 생성자
     * @param columnNames 컬럼 이름
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     * @param tableRowHeight 테이블 행의 높이
     */
    public DefaultTable(String[] columnNames, int tableWidth, int tableHeight, int tableRowHeight)
    {
        this(new DefaultTableData(columnNames), tableWidth, tableHeight, tableRowHeight);
    }
    
    /**
     * 테이블 모델, 테이블 넓이, 테이블 높이, 테이블 행 높이를 인자로 갖는 생성자
     * @param dtd 테이블 데이터
     * @param tableWidth 테이블 넓이
     * @param tableHeight 테이블 높이
     * @param tableRowHeight 테이블 행의 높이
     */    
    public DefaultTable(DefaultTableData dtd, int tableWidth, int tableHeight, int tableRowHeight)
    {
        super(new BorderLayout());
        this.dtd = dtd;
        this.tableWidth = tableWidth;
        this.tableHeight = tableHeight;
        this.tableRowHeight = tableRowHeight;
        
        initComp();
        initTable();
        addListener();
    }
    
    /**
     * 테이블 데이터를 설정한다.
     * @param dtd 테이블 테이터
     */
    public void setTableData(DefaultTableData dtd)
    {
        removeAllColumns();
        this.dtd = dtd;
        table.setModel(this.dtd);        
        initTable();
        addListener();
    }
    
    /**
     * 컨포넌트 초기화 한다.
     */
    private void initComp()
    {
        table = new JTable(this.dtd);
        cModel = table.getColumnModel();
        gtHeader = new JTableHeader(cModel);
        table.setTableHeader(gtHeader);
        removeAllColumns();
        jsp = new JScrollPane(table);
        jsb = new JScrollBar(JScrollBar.VERTICAL);
        jsb1 = new JScrollBar(JScrollBar.HORIZONTAL);
        jsb.setUnitIncrement(unitV);
        jsb.setUnitIncrement(unitH);
        jsb.setBlockIncrement(blockV);
        jsb1.setBlockIncrement(blockH);
        jsp.setVerticalScrollBar(jsb);
        jsp.setHorizontalScrollBar(jsb1);
        if(isVerticalScroll) 
            setVerticalScroll(true);
        else 
            setVerticalScroll(false);
        if(isHorizontalScroll) 
            setHorizontalScroll(true);
        else 
            setHorizontalScroll(false);
        add(jsp);
    }
    
    /**
     * 테이블에 대한 설정을 한다.
     */
    private void initTable()
    {
        if(tableHeight != 0 && tableWidth != 0)
            jsp.setPreferredSize(new Dimension(tableWidth, tableHeight));
        if(tableRowHeight != 0)
            table.setRowHeight(tableRowHeight);
        table.setAutoCreateColumnsFromModel(false);
        addColumns(this.dtd);
        table.getTableHeader().setUpdateTableInRealTime(false);
        if(!isHeader)
            table.getTableHeader().setPreferredSize(new Dimension(0, 0));
    }
    
    /**
     * 테이블 행 높이를 설정한다.
     * @param height 행 높이를 설정한다.
     */
    public void setTableRowHeight(int height)
    {
        this.tableRowHeight = height;
        this.table.setRowHeight(height);
    }
    
    /**
     * 테이블 헤더 높이를 설정한다.
     * @param height 행 높이
     */
    public void setTableHeaderHeight(int height)
    {
        JTableHeader h = table.getTableHeader();
        h.setPreferredSize(new Dimension(h.getPreferredSize().width, height));
    }

    /**
     * 테이블 컬럼을 업데이트 한다.
     */
    public void updateColumns()
    {
        removeAllColumns();
        table.setTableHeader(this.gtHeader);
        addColumns(this.dtd);
    }
    
    /**
     * 테이블 컬럼을 추가한다.
     * @param DefaultTableData 테이블 데이터
     */
    private void addColumns(DefaultTableData data)
    {
        ColumnData[] kcd = data.getColumnData();
        for(int i=0; i<kcd.length; i++)
        {
            int type = kcd[i].getColumnType();
            TableCellRenderer renderer = data.getTableCellRenderer(table, type, i);
            TableCellEditor editor = data.getTableCellEditor(table, type, i);
            if(renderer instanceof DefaultTableCellRenderer)
            {
                DefaultTableCellRenderer renderer_ = (DefaultTableCellRenderer)renderer;
                renderer_.setHorizontalAlignment(kcd[i].getAlign());
                renderer_.setBackground(kcd[i].getColor());
                renderer_.setBorder(BorderFactory.createRaisedBevelBorder());
                TableColumn tc = new TableColumn(i, kcd[i].getWidth(), renderer_, editor);
                table.addColumn(tc);
            }
            else
            {
                TableColumn tc = new TableColumn(i, kcd[i].getWidth(), renderer, editor);
                table.addColumn(tc);
            }
        }
    }

    /**
     * 테이블 컬럼을 얻는다.
     * @param idx 컬럼 인덱스
     * @return TableColumn 테이블 컬럼
     */
    public TableColumn getColumn(int idx)
    {
        return cModel.getColumn(idx);
    }
    
    /**
     * 컬럼 활성화 여부를 설정한다.
     * @param col 열
     * @param is 활성화 여부
     */
    public void setEnableColumn(int col, boolean is)
    {
        ColumnData[] cds = this.dtd.getColumnData();
        cds[col].setEditable(is);
    }
    
    /**
     * 모든 행을 삭제 한다.
     */
    public void removeAllRows()
    {
        this.dtd.removeAllRows();
    }
    
    /**
     * 인덱스 위치의 행을 삭제 한다.
     * @param rowIndex 테이블 행 인덱스
     */    
    public void removeRow(int rowIndex)
    {
        this.dtd.removeRow(rowIndex);
    }

    /**
     * 테이블의 모든 컬럼을 삭제한다.
     */
    public void removeAllColumns()
    {
        ColumnData[] cd = this.dtd.getColumnData();
        for(int i=0; i<cd.length; i++)
        {
            try
            {
                removeColumnAt(cd[i].getName());
            }
            catch(Exception e)
            {
            }
        }
    }
    
    /**
     * 컬럼을 삭제한다.
     * @param name 컬럼 이름
     */
    public void removeColumnAt(Object name)
    {
        TableColumn tc = table.getColumn(name);
        table.removeColumn(tc);
    }
    
    /**
     * 이벤트 정보를 설정 한다.
     */
    private void addListener()
    {
        table.getTableHeader().addMouseListener(new MouseAdapter()
        {
            /**
             * 마우스 버튼이 떼어졌을때
             * @see java.awt.event.MouseListener#mouseReleased(MouseEvent)
             */        
            public void mouseReleased(MouseEvent me)
            {
                int count = me.getClickCount();
                int col = getTable().getTableHeader().columnAtPoint(me.getPoint());
                if((count == 2) && col == 0)
                {
                    if(dtd.getColumnDataAt(0).getColumnType() == CELL_CHECKBOX)
                    {
                        int rowCnt = getTable().getRowCount();
                        //전체 선택을 해제할 경우
                        if (selected)
                        {
                            for(int i=0; i<rowCnt; i++)
                                getTable().setValueAt(new Boolean(false), i, 0);
                            selected = false;
                        }
                        //전체 선택할 경우
                        else
                        {
                            for(int i=0; i<rowCnt ; i++)
                                getTable().setValueAt(new Boolean(true), i, 0);
                            selected = true;
                        }
                    }
                }
                else
                {
                    //dm.sortByColumn(col);
                    //dtd.sort(col, AbstractTableData.SORT_ASC);
                }
            }            
        });
    }
    
    /**
     * 마우스 리스너를 추가한다.
     * @see java.awt.Component#addMouseListener(MouseListener)
     */
    public void addMouseListener(MouseListener ml)
    {
        table.addMouseListener(ml);
    }
    
    /**
     * 테이블 모델 객체를 얻는다
     * @return 테이블 모델 객체
     */
    public DefaultTableData getTableModel()
    {
        return this.dtd;
    }
    
    /**
     * 테이블 객체를 얻는다
     * @return 테이블 객체
     */
    public JTable getTable()
    {
        return this.table;
    }
    
    /** 
     * 스크롤 팬 객체를 얻는다.
     * @return ScrollPane객체
     */
    public JScrollPane getScrollPane()
    {
        return this.jsp;
    }
    
    /**
     * 세로 스크롤바 설정
     * @param isVerticalScroll 세로 스크롤바 설정여부
     */
    public void setVerticalScroll(boolean is)
    {
        this.isVerticalScroll = is;
        if(isVerticalScroll) 
            jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        else 
            jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }
    
    /**
     * 가로 스크롤바 설정
     * @param isHorizontalScroll 가로 스크롤바 설정여부
     */
    public void setHorizontalScroll(boolean is)
    {
        this.isHorizontalScroll = is;
        if(isHorizontalScroll) 
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        else 
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    
    /**
     * 헤더 설정
     * @param isHeader 헤더 여부
     */
    public void setHeader(boolean isHeader)
    {
        this.isHeader = isHeader;
        if(!isHeader) 
            table.getTableHeader().setPreferredSize(new Dimension(0, 0));
    }
    
    /**
     * 테이블 컬럼 모델을 얻는다.
     * @return TableColumnModel 테이블 컬럼 모델
     */
    public TableColumnModel getTableColumnModel()
    {
        return table.getColumnModel();
    }
    
    /**
     * 블럭 스크롤의 크기를 셋팅
     * @param vSize 블럭 세로 스크롤 크기
     * @param hSize 블럭 가로 스크롤 크기
     */
    public void setBlockScrollSize(int vSize, int hSize)
    {
        blockV = vSize;
        blockH = hSize;
        jsb.setBlockIncrement(blockV);
        jsb1.setBlockIncrement(blockH);
    }
    
    /**
     * 유닛 스크롤 크기를 셋팅
     * @param vSize 세로 유닛 스크롤 사이즈
     * @param hSize 가로 유닛 스크롤 사이즈
     */
    public void setUnitScrollSize(int vSize, int hSize)
    {
        unitV = vSize;
        unitH = hSize;
        jsb.setUnitIncrement(unitV);
        jsb1.setBlockIncrement(unitH);
    }
    
    /**
     * 마지막으로 스크롤을 옮긴다.
     * @param orientation
     */
    public void setToLast(int orientation) {
    	if(orientation == JScrollBar.VERTICAL)
    		jsb.setValue(jsb.getMaximum());
    	else if(orientation == JScrollBar.HORIZONTAL)
    		jsb1.setValue(jsb.getMaximum());
    	else
    		throw new IllegalArgumentException("Not valid argument : "+orientation);
    }
    
    /**
     * 테이블 모델 리스너
     * @param listener 리스너
     */
    public void addTableModelListener(TableModelListener listener) {
    	this.dtd.addTableModelListener(listener);
    }    
}
