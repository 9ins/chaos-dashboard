/*
 * Filename : ColumnData.java
 * Class : ColumnData
 */
package org.chaos.mgmt.client.ui;

import java.awt.Color;

/**
 * DefaultTable의 컴럼정보를 표현하는 클래스
 * 
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class ColumnData implements ColumnConstants
{
    /** 열 이름 */
    Object columnName;
    
    /** 열 넓이 */
    int columnWidth;
    
    /** 열 정렬 */
    int columnAlign;
    
    /** 열을 편집할지 말지 */
    boolean isEditable;
    
    /** 열 색갈 */
    Color columnColor;
    
    /** 컬럼 타입 */
    int columnType;
    
    /** 컬럼 행수 */
    int columnRow;
    
    /** 컬럼 열수 */
    int columnCol;
    
    /** 컴보박스 아이템 목록 */
    Object[] comboItems;
    
    /** 스핀 필드 */
    SpinField spinField;
    
    /** 다중 컴포넌트 목록 */
    MultiComponentElement[] multiElements;
    
    /** 다중 컴포넌트 배치 타입 */
    int multiCompOrientation;
    
    /** 다중 컴포넌트시 컴포넌트를 셀 크기에 맞게 설정하는 플래그 */
    boolean fitToCell;
    
    /** 현재 디렉토리 */
    String currentPath;
    
    /** 다이얼로그 타이틀 */
    String title;
    
    /** 다이얼로그 타입 */
    int dlgType;
    
    /** 파일 선택 모드 */
    int selMode;
    
    /**
     * 열이름을 인자로 갖는 생성자
     * @param 열이름
     */
    public ColumnData(Object columnName)
    {
        this(CELL_TEXTFIELD, columnName, 100, CELL_ALIGH_CENTER, Color.white, false);
    }
    
    /**
     * 컬럼 타입, 컬럼 이름, 컬럼 넓이, 컬럼 정렬, 컬럼 색깔, 셀 편집여부를 인자로 갖는 생성자
     * @param columnType 컬럼 타입
     * @param columnName 컬럼 이름
     * @param columnWidth 컬럼 넓이
     * @param columnAlign 컬럼 정렬
     * @param columnColor 컬럼 색깔
     * @param isEditable 셀 편집여부
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable)
    {
        if(columnType == ColumnConstants.CELL_COMBOBOX || columnType == ColumnConstants.CELL_MULTI_COMPONENT)
            throw new Error("이 생성자로 생성할 수 없습니다.");
        this.columnType = columnType;
        this.columnName = columnName;
        this.columnWidth = columnWidth;
        this.columnAlign = columnAlign;
        this.columnColor = columnColor;
        this.isEditable = isEditable;
    }
    
    /**
     * 컬럼 타입, 컬럼 이름, 컬럼 넓이, 컬럼 정렬, 컬럼 색깔, 셀 편집여부, 스핀필드를 인자로 갖는 생성자
     * @param columnType 컬럼 타입
     * @param columnName 컬럼 이름
     * @param columnWidth 컬럼 넓이
     * @param columnAlign 컬럼 정렬
     * @param columnColor 컬럼 색깔
     * @param isEditable 셀 편집여부
     * @param spinField 스핀필드 객체
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable, SpinField spinField)
    {
        if(columnType != ColumnConstants.CELL_SPINFIELD)
            throw new Error("스핀필드 전용 생성자 입니다.");
        this.columnType = columnType;
        this.columnName = columnName;
        this.columnWidth = columnWidth;
        this.columnAlign = columnAlign;
        this.columnColor = columnColor;
        this.isEditable = isEditable;
        this.spinField = spinField;
    }        

    /**
     * 컬럼 타입, 컬럼 이름, 컬럼 넓이, 컬럼 정렬, 컬럼 색깔, 셀 편집여부, 콤보박스 아이템을 인자로 갖는 생성자
     * @param columnType 컬럼 타입
     * @param columnName 컬럼 이름
     * @param columnWidth 컬럼 넓이
     * @param columnAlign 컬럼 정렬
     * @param columnColor 컬럼 색깔
     * @param isEditable 셀 편집여부
     * @param comboItems 콤보 아이템 목록
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable, Object[] comboItems)
    {
        if(columnType != ColumnConstants.CELL_COMBOBOX)
            throw new Error("콤보박스 전용 생성자 입니다.");
        this.columnType = columnType;
        this.columnName = columnName;
        this.columnWidth = columnWidth;
        this.columnAlign = columnAlign;
        this.columnColor = columnColor;
        this.isEditable = isEditable;
        this.comboItems = comboItems;
    }        

    /**
     * 컬럼 타입, 컬럼 이름, 컬럼 넓이, 컬럼 정렬, 컬럼 색깔, 셀 편집여부, 다중 컴포넌트 목록을 인자로 갖는 생성자
     * @param columnType 컬럼 타입
     * @param columnName 컬럼 이름
     * @param columnWidth 컬럼 넓이
     * @param columnAlign 컬럼 정렬
     * @param columnColor 컬럼 색깔
     * @param isEditable 셀 편집여부
     * @param multiElements 다중 컴포넌트
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable, MultiComponentElement[] multiElements, int multiCompOrientation, boolean fitToCell)
    {
        if(columnType != ColumnConstants.CELL_MULTI_COMPONENT)
            throw new Error("다중 컴포넌트 전용 생성자 입니다.");
        this.columnType = columnType;
        this.columnName = columnName;
        this.columnWidth = columnWidth;
        this.columnAlign = columnAlign;
        this.columnColor = columnColor;
        this.isEditable = isEditable;
        this.multiElements = multiElements;
        this.multiCompOrientation = multiCompOrientation;
        this.fitToCell = fitToCell;
    }

    /**
     * 컬럼 종류를 얻는다.
     * @return int 컬럼 종류
     */
    public int getColumnType()
    {
        return this.columnType;
    }
    
    /**
     * 컬럼 종류를 설정한다.
     * @param columnType 컬럼 타입
     */
    public void setColumnType(int columnType)
    {
        this.columnType = columnType;
    }
    
    /**
     * 열 이름을 얻는다.
     * @return String 열이름
     */
    public Object getName()
    {
        return this.columnName;
    }
    
    /**
     * 열 이름을 설정한다.
     * @param columnName 컬럼 이름
     */
    public void setName(Object columnName)
    {
        this.columnName = columnName;
    }
    
    /**
     * 열 넓이를 구한다.
     * @return int 열넓이
     */
    public int getWidth()
    {
        return this.columnWidth;
    }
    
    /**
     * 열 넓이를 설정한다.
     * @param width 열넓이
     */
    public void setWidth(int columnWidth)
    {
        this.columnWidth = columnWidth;
    }
    
    /**
     * 열 정렬을 구한다.
     * @return int 열정렬방식
     */
    public int getAlign()
    {
        return this.columnAlign;
    }
    
    /**
     * 컬럼 정렬을 설정한다.
     * @param columnAlign 컬럼 정렬
     */
    public void setAlign(int columnAlign)
    {
        this.columnAlign = columnAlign;
    }
    
    /**
     * 열 색깔을 구한다.
     * @return Color 열색깔
     */
    public Color getColor()
    {
        return this.columnColor;
    }
    
    /**
     * 열 색깔을 설정한다.
     * @param columnColor 컬럼 색깔
     */
    public void setColor(Color columnColor)
    {
        this.columnColor = columnColor;
    }
    
    /**
     * 열을 보일지 말지를 얻어온다.
     * @return boolean 열을 보일지 말지
     */
    public boolean isEditable()
    {
        return this.isEditable;
    }
    
    /**
     * 열 편집 가능여부를 얻는다.
     * @param editable 편집 가능여부
     */
    public void setEditable(boolean editable)
    {
        this.isEditable = editable;
    }
    
    /**
     * 콤보 아이템을 얻는다.
     * @return Object[] 컴보 아이템
     */
    public Object[] getComboItems()
    {
        return this.comboItems;
    }
    
    /**
     * 콤보 아이템을 설정한다.
     * @param comboItems 컴보 아이템 
     */
    public void setComboItems(Object[] comboItems)
    {
        this.comboItems = comboItems;
    }
    
    /**
     * 스핀 필드를 얻는다.
     * @return SpinField 스핀 필드
     */
    public SpinField getSpinField()
    {
        return this.spinField;
    }
    
    /**
     * 스핀 필드를 설정한다.
     * @param spinField 스핀 필드
     */
    public void setSpinField(SpinField spinField)
    {
        this.spinField = spinField;
    }
    
    /**
     * 다중 컴포넌트 목록을 얻는다.
     * @return MultiComponentElement[] 다중컴포넌트 목록
     */
    public MultiComponentElement[] getMultiComponentElement()
    {
        return this.multiElements;
    }
    
    /**
     * 다중 컴포넌트 목록을 설정한다.
     * @param multiElements 다중 컴포넌트 목록
     */
    public void setMultiComponentElement(MultiComponentElement[] multiElements)
    {
        this.multiElements = multiElements;
    }
    
    /**
     * 다중 컴포넌트 배치 방식을 얻는다.
     * @return int 정렬방식을 얻는다.
     */
    public int getMultiComponentOrientation()
    {
        return this.multiCompOrientation;
    }
    
    /**
     * 다중 컴포넌트 배치 방식을 설정한다.
     * @param orientation 배치 방식
     */
    public void setMultiComponentOrientation(int orientation)
    {
        this.multiCompOrientation = orientation;
    }
    
    /**
     * 컴포넌트 사이즈 플래그를 얻는다.
     * @return boolean 컴포넌트 사이즈 플래그
     */
    public boolean getFitToCell()
    {
        return this.fitToCell;
    }
    
    /**
     * 컴포넌트 사이즈 플래그를 설정한다.
     * @param fitToCell 컴포넌트 사이즈 플래그
     */
    public void setFitToCell(boolean fitToCell)
    {
        this.fitToCell = fitToCell;
    }
}
