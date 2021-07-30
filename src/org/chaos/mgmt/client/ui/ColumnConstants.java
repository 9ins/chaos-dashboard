/*
 * Filename : ColumnConstants.java
 * Class : ColumnConstants
 */
package org.chaos.mgmt.client.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * 테이블 컬럼 상수
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public interface ColumnConstants
{
    /** 셀이 Default */
    public static final int CELL_DEFAULT = 0;
    
    /** 셀이 TextField */
    public static final int CELL_TEXTFIELD = 1;

    /** 셀이 ComboBox */
    public static final int CELL_COMBOBOX = 2;

    /** 셀이 CheckBox */
    public static final int CELL_CHECKBOX = 3;

    /** 셀이 ScrollBar */
    public static final int CELL_SCROLLBAR = 4;

    /** 셀이 SpinTextField */
    public static final int CELL_SPINFIELD = 5;
    
    /** 셀이 Button */
    public static final int CELL_BUTTON = 6;
    
    /** 셀이 DigitField경우 */
    public static final int CELL_DIGITFIELD = 7;
    
    /** 셀이 멀티 컴포넌트일 경우 */
    public static final int CELL_MULTI_COMPONENT = 8;
    
    /** 셀 중앙정렬 */
    public static final int CELL_ALIGH_CENTER = JLabel.CENTER;
    
    /** 셀 왼쪽정렬 */
    public static final int CELL_ALIGN_LEFT = JLabel.LEFT;
    
    /** 셀 오른쪽 정렬 */
    public static final int CELL_ALIGN_RIGHT = JLabel.RIGHT;
    
    /** 컴포넌트 배치형태 X축 */
    public static final int ORIENTATION_X_AXIS = BoxLayout.X_AXIS;
    
    /** 컴포넌트 배치형태 Y축 */
    public static final int ORIENTATION_Y_AXIS = BoxLayout.Y_AXIS;
}





