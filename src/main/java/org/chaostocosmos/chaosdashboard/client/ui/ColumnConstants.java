/*
 * Filename : ColumnConstants.java
 * Class : ColumnConstants
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * ���̺� �÷� ���
 * 
 * @author Kooin Shin
 * @version
 * @since JKD1.3.1
 */
public interface ColumnConstants
{
    /** ���� Default */
    public static final int CELL_DEFAULT = 0;
    
    /** ���� TextField */
    public static final int CELL_TEXTFIELD = 1;

    /** ���� ComboBox */
    public static final int CELL_COMBOBOX = 2;

    /** ���� CheckBox */
    public static final int CELL_CHECKBOX = 3;

    /** ���� ScrollBar */
    public static final int CELL_SCROLLBAR = 4;

    /** ���� SpinTextField */
    public static final int CELL_SPINFIELD = 5;
    
    /** ���� Button */
    public static final int CELL_BUTTON = 6;
    
    /** ���� DigitField��� */
    public static final int CELL_DIGITFIELD = 7;
    
    /** ���� ��Ƽ ������Ʈ�� ��� */
    public static final int CELL_MULTI_COMPONENT = 8;
    
    /** �� �߾����� */
    public static final int CELL_ALIGH_CENTER = JLabel.CENTER;
    
    /** �� �������� */
    public static final int CELL_ALIGN_LEFT = JLabel.LEFT;
    
    /** �� ������ ���� */
    public static final int CELL_ALIGN_RIGHT = JLabel.RIGHT;
    
    /** ������Ʈ ��ġ���� X�� */
    public static final int ORIENTATION_X_AXIS = BoxLayout.X_AXIS;
    
    /** ������Ʈ ��ġ���� Y�� */
    public static final int ORIENTATION_Y_AXIS = BoxLayout.Y_AXIS;
}





