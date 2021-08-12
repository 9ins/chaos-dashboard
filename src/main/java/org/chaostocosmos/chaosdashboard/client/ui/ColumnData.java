/*
 * Filename : ColumnData.java
 * Class : ColumnData
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Color;

/**
 * DefaultTable�� �ķ������� ǥ���ϴ� Ŭ����
 * 
 * @author Kooin-Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class ColumnData implements ColumnConstants
{
    /** �� �̸� */
    Object columnName;
    
    /** �� ���� */
    int columnWidth;
    
    /** �� ���� */
    int columnAlign;
    
    /** ���� �������� ���� */
    boolean isEditable;
    
    /** �� ���� */
    Color columnColor;
    
    /** �÷� Ÿ�� */
    int columnType;
    
    /** �÷� ��� */
    int columnRow;
    
    /** �÷� ���� */
    int columnCol;
    
    /** �ĺ��ڽ� ������ ��� */
    Object[] comboItems;
    
    /** ���� �ʵ� */
    SpinField spinField;
    
    /** ���� ������Ʈ ��� */
    MultiComponentElement[] multiElements;
    
    /** ���� ������Ʈ ��ġ Ÿ�� */
    int multiCompOrientation;
    
    /** ���� ������Ʈ�� ������Ʈ�� �� ũ�⿡ �°� �����ϴ� �÷��� */
    boolean fitToCell;
    
    /** ���� ���丮 */
    String currentPath;
    
    /** ���̾�α� Ÿ��Ʋ */
    String title;
    
    /** ���̾�α� Ÿ�� */
    int dlgType;
    
    /** ���� ���� ��� */
    int selMode;
    
    /**
     * ���̸��� ���ڷ� ���� ������
     * @param ���̸�
     */
    public ColumnData(Object columnName)
    {
        this(CELL_TEXTFIELD, columnName, 100, CELL_ALIGH_CENTER, Color.white, false);
    }
    
    /**
     * �÷� Ÿ��, �÷� �̸�, �÷� ����, �÷� ����, �÷� ����, �� �������θ� ���ڷ� ���� ������
     * @param columnType �÷� Ÿ��
     * @param columnName �÷� �̸�
     * @param columnWidth �÷� ����
     * @param columnAlign �÷� ����
     * @param columnColor �÷� ����
     * @param isEditable �� ��������
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable)
    {
        if(columnType == ColumnConstants.CELL_COMBOBOX || columnType == ColumnConstants.CELL_MULTI_COMPONENT)
            throw new Error("�� �����ڷ� ������ �� �����ϴ�.");
        this.columnType = columnType;
        this.columnName = columnName;
        this.columnWidth = columnWidth;
        this.columnAlign = columnAlign;
        this.columnColor = columnColor;
        this.isEditable = isEditable;
    }
    
    /**
     * �÷� Ÿ��, �÷� �̸�, �÷� ����, �÷� ����, �÷� ����, �� ��������, �����ʵ带 ���ڷ� ���� ������
     * @param columnType �÷� Ÿ��
     * @param columnName �÷� �̸�
     * @param columnWidth �÷� ����
     * @param columnAlign �÷� ����
     * @param columnColor �÷� ����
     * @param isEditable �� ��������
     * @param spinField �����ʵ� ��ü
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable, SpinField spinField)
    {
        if(columnType != ColumnConstants.CELL_SPINFIELD)
            throw new Error("�����ʵ� ���� ������ �Դϴ�.");
        this.columnType = columnType;
        this.columnName = columnName;
        this.columnWidth = columnWidth;
        this.columnAlign = columnAlign;
        this.columnColor = columnColor;
        this.isEditable = isEditable;
        this.spinField = spinField;
    }        

    /**
     * �÷� Ÿ��, �÷� �̸�, �÷� ����, �÷� ����, �÷� ����, �� ��������, �޺��ڽ� �������� ���ڷ� ���� ������
     * @param columnType �÷� Ÿ��
     * @param columnName �÷� �̸�
     * @param columnWidth �÷� ����
     * @param columnAlign �÷� ����
     * @param columnColor �÷� ����
     * @param isEditable �� ��������
     * @param comboItems �޺� ������ ���
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable, Object[] comboItems)
    {
        if(columnType != ColumnConstants.CELL_COMBOBOX)
            throw new Error("�޺��ڽ� ���� ������ �Դϴ�.");
        this.columnType = columnType;
        this.columnName = columnName;
        this.columnWidth = columnWidth;
        this.columnAlign = columnAlign;
        this.columnColor = columnColor;
        this.isEditable = isEditable;
        this.comboItems = comboItems;
    }        

    /**
     * �÷� Ÿ��, �÷� �̸�, �÷� ����, �÷� ����, �÷� ����, �� ��������, ���� ������Ʈ ����� ���ڷ� ���� ������
     * @param columnType �÷� Ÿ��
     * @param columnName �÷� �̸�
     * @param columnWidth �÷� ����
     * @param columnAlign �÷� ����
     * @param columnColor �÷� ����
     * @param isEditable �� ��������
     * @param multiElements ���� ������Ʈ
     */
    public ColumnData(int columnType, Object columnName, int columnWidth, int columnAlign, Color columnColor, boolean isEditable, MultiComponentElement[] multiElements, int multiCompOrientation, boolean fitToCell)
    {
        if(columnType != ColumnConstants.CELL_MULTI_COMPONENT)
            throw new Error("���� ������Ʈ ���� ������ �Դϴ�.");
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
     * �÷� ������ ��´�.
     * @return int �÷� ����
     */
    public int getColumnType()
    {
        return this.columnType;
    }
    
    /**
     * �÷� ������ �����Ѵ�.
     * @param columnType �÷� Ÿ��
     */
    public void setColumnType(int columnType)
    {
        this.columnType = columnType;
    }
    
    /**
     * �� �̸��� ��´�.
     * @return String ���̸�
     */
    public Object getName()
    {
        return this.columnName;
    }
    
    /**
     * �� �̸��� �����Ѵ�.
     * @param columnName �÷� �̸�
     */
    public void setName(Object columnName)
    {
        this.columnName = columnName;
    }
    
    /**
     * �� ���̸� ���Ѵ�.
     * @return int ������
     */
    public int getWidth()
    {
        return this.columnWidth;
    }
    
    /**
     * �� ���̸� �����Ѵ�.
     * @param width ������
     */
    public void setWidth(int columnWidth)
    {
        this.columnWidth = columnWidth;
    }
    
    /**
     * �� ������ ���Ѵ�.
     * @return int �����Ĺ��
     */
    public int getAlign()
    {
        return this.columnAlign;
    }
    
    /**
     * �÷� ������ �����Ѵ�.
     * @param columnAlign �÷� ����
     */
    public void setAlign(int columnAlign)
    {
        this.columnAlign = columnAlign;
    }
    
    /**
     * �� ������ ���Ѵ�.
     * @return Color ������
     */
    public Color getColor()
    {
        return this.columnColor;
    }
    
    /**
     * �� ������ �����Ѵ�.
     * @param columnColor �÷� ����
     */
    public void setColor(Color columnColor)
    {
        this.columnColor = columnColor;
    }
    
    /**
     * ���� ������ ������ ���´�.
     * @return boolean ���� ������ ����
     */
    public boolean isEditable()
    {
        return this.isEditable;
    }
    
    /**
     * �� ���� ���ɿ��θ� ��´�.
     * @param editable ���� ���ɿ���
     */
    public void setEditable(boolean editable)
    {
        this.isEditable = editable;
    }
    
    /**
     * �޺� �������� ��´�.
     * @return Object[] �ĺ� ������
     */
    public Object[] getComboItems()
    {
        return this.comboItems;
    }
    
    /**
     * �޺� �������� �����Ѵ�.
     * @param comboItems �ĺ� ������ 
     */
    public void setComboItems(Object[] comboItems)
    {
        this.comboItems = comboItems;
    }
    
    /**
     * ���� �ʵ带 ��´�.
     * @return SpinField ���� �ʵ�
     */
    public SpinField getSpinField()
    {
        return this.spinField;
    }
    
    /**
     * ���� �ʵ带 �����Ѵ�.
     * @param spinField ���� �ʵ�
     */
    public void setSpinField(SpinField spinField)
    {
        this.spinField = spinField;
    }
    
    /**
     * ���� ������Ʈ ����� ��´�.
     * @return MultiComponentElement[] ����������Ʈ ���
     */
    public MultiComponentElement[] getMultiComponentElement()
    {
        return this.multiElements;
    }
    
    /**
     * ���� ������Ʈ ����� �����Ѵ�.
     * @param multiElements ���� ������Ʈ ���
     */
    public void setMultiComponentElement(MultiComponentElement[] multiElements)
    {
        this.multiElements = multiElements;
    }
    
    /**
     * ���� ������Ʈ ��ġ ����� ��´�.
     * @return int ���Ĺ���� ��´�.
     */
    public int getMultiComponentOrientation()
    {
        return this.multiCompOrientation;
    }
    
    /**
     * ���� ������Ʈ ��ġ ����� �����Ѵ�.
     * @param orientation ��ġ ���
     */
    public void setMultiComponentOrientation(int orientation)
    {
        this.multiCompOrientation = orientation;
    }
    
    /**
     * ������Ʈ ������ �÷��׸� ��´�.
     * @return boolean ������Ʈ ������ �÷���
     */
    public boolean getFitToCell()
    {
        return this.fitToCell;
    }
    
    /**
     * ������Ʈ ������ �÷��׸� �����Ѵ�.
     * @param fitToCell ������Ʈ ������ �÷���
     */
    public void setFitToCell(boolean fitToCell)
    {
        this.fitToCell = fitToCell;
    }
}
