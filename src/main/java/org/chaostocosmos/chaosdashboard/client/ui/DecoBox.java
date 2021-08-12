/*
 * Filename : DecoBox.java
 * Class : DecoBox
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

/**
 * �ܰ����� ������ ������ Ŭ����
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class DecoBox
{
    /**
     * �� �ܰ���
     */
    public static final Border EMPTY = new EmptyBorder(5, 5, 5, 5);

    /**
     * Bevel ���� �ܰ���
     */
    public static final Border BEVEL_RAISED = new BevelBorder(BevelBorder.RAISED);

    /** 
     * Bevel ���� �ܰ���
     */
    public static final Border BEVEL_LOWERED = new BevelBorder(BevelBorder.LOWERED);

    /**
     * Etched ���� �ܰ���
     */
    public static final Border ETCHED_RAISED = new EtchedBorder(EtchedBorder.RAISED);

    /**
     * Etched ���� �ܰ���
     */
    public static final Border ETCHED_LOWERED = new EtchedBorder(EtchedBorder.LOWERED);

    /**
     * Line ���� �ܰ���
     */
    public static final Border LINE_BLACK = new LineBorder(Color.black);

    /**
     * Line �Ķ� �ܰ���
     */
    public static final Border LINE_BLUE = new LineBorder(Color.blue);

    /**
     * Line û�ϻ� �ܰ���
     */
    public static final Border LINE_CYAN = new LineBorder(Color.cyan);

    /**
     * Line ȸ�� �ܰ���
     */
    public static final Border LINE_GRAY = new LineBorder(Color.gray, 5, true);

    /**
     * Line ��ο� ȸ�� �ܰ���
     */
    public static final Border LINE_DARKGRAY = new LineBorder(Color.darkGray, 3, true);

    /**
     * Line ���� ȸ�� �ܰ���
     */
    public static final Border LINE_LIGHTGRAY = new LineBorder(Color.lightGray);

    /**
     * Line ��ȫ�� �ܰ���
     */
    public static final Border LINE_MAGENTA = new LineBorder(Color.magenta);

    /**
     * Line �������� �ܰ���
     */
    public static final Border LINE_ORANGE = new LineBorder(Color.orange);

    /**
     * Line ��ũ�� �ܰ���
     */
    public static final Border LINE_PINK = new LineBorder(Color.pink);

    /**
     * Line ���� �ܰ���
     */
    public static final Border LINE_RED = new LineBorder(Color.red);

    /**
     * Line ��� �ܰ���
     */
    public static final Border LINE_WHITE = new LineBorder(Color.white);

    /**
     * Line ����� �ܰ���
     */
    public static final Border LINE_YELLOW = new LineBorder(Color.yellow);

    /**
     * ���� Metal �ܰ���
     */
    public static final Border COMPOUND_METAL = new CompoundBorder(new LineBorder(new Color(180, 180, 200), 1, true), new LineBorder(new Color(100, 100, 120), 1, true));

    /**
     * �г� Ÿ��Ʋ ��Ʈ
     */
    public static final Font TITLE_FONT = new Font("����", Font.BOLD, 12);

    /**
     * �г� Ÿ��Ʋ ��Ʈ��
     */
    public static final Color TITLE_COLOR = new Color(50, 50, 50);

    /**
     * ��ư ��Ʈ
     */
    public static final Font BUTTON_FONT = new Font("����", Font.PLAIN, 12);

    /**
     * ��ư ��Ʈ��
     */
    public static final Color BUTTON_COLOR = new Color(50, 50, 50);

    /**
     * �� ��Ʈ
     */
    public static final Font LABEL_FONT = new Font("����", Font.PLAIN, 12);

    /**
     * �� ��Ʈ��
     */
    public static final Color LABEL_COLOR = new Color(50, 50, 50);
}
