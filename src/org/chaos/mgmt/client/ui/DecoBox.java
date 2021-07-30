/*
 * Filename : DecoBox.java
 * Class : DecoBox
 */
package org.chaos.mgmt.client.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

/**
 * 외곽선의 종류를 정의한 클래스
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class DecoBox
{
    /**
     * 빈 외곽선
     */
    public static final Border EMPTY = new EmptyBorder(5, 5, 5, 5);

    /**
     * Bevel 오름 외곽선
     */
    public static final Border BEVEL_RAISED = new BevelBorder(BevelBorder.RAISED);

    /** 
     * Bevel 내림 외곽선
     */
    public static final Border BEVEL_LOWERED = new BevelBorder(BevelBorder.LOWERED);

    /**
     * Etched 오름 외곽선
     */
    public static final Border ETCHED_RAISED = new EtchedBorder(EtchedBorder.RAISED);

    /**
     * Etched 내림 외곽선
     */
    public static final Border ETCHED_LOWERED = new EtchedBorder(EtchedBorder.LOWERED);

    /**
     * Line 검정 외곽선
     */
    public static final Border LINE_BLACK = new LineBorder(Color.black);

    /**
     * Line 파랑 외곽선
     */
    public static final Border LINE_BLUE = new LineBorder(Color.blue);

    /**
     * Line 청록색 외곽선
     */
    public static final Border LINE_CYAN = new LineBorder(Color.cyan);

    /**
     * Line 회색 외곽선
     */
    public static final Border LINE_GRAY = new LineBorder(Color.gray, 5, true);

    /**
     * Line 어두운 회색 외곽선
     */
    public static final Border LINE_DARKGRAY = new LineBorder(Color.darkGray, 3, true);

    /**
     * Line 밝은 회색 외곽선
     */
    public static final Border LINE_LIGHTGRAY = new LineBorder(Color.lightGray);

    /**
     * Line 자홍색 외곽선
     */
    public static final Border LINE_MAGENTA = new LineBorder(Color.magenta);

    /**
     * Line 오렌지색 외곽선
     */
    public static final Border LINE_ORANGE = new LineBorder(Color.orange);

    /**
     * Line 핑크색 외곽선
     */
    public static final Border LINE_PINK = new LineBorder(Color.pink);

    /**
     * Line 빨강 외곽선
     */
    public static final Border LINE_RED = new LineBorder(Color.red);

    /**
     * Line 흰색 외곽선
     */
    public static final Border LINE_WHITE = new LineBorder(Color.white);

    /**
     * Line 노랑색 외곽선
     */
    public static final Border LINE_YELLOW = new LineBorder(Color.yellow);

    /**
     * 복합 Metal 외곽선
     */
    public static final Border COMPOUND_METAL = new CompoundBorder(new LineBorder(new Color(180, 180, 200), 1, true), new LineBorder(new Color(100, 100, 120), 1, true));

    /**
     * 패널 타이틀 폰트
     */
    public static final Font TITLE_FONT = new Font("굴림", Font.BOLD, 12);

    /**
     * 패널 타이틀 폰트색
     */
    public static final Color TITLE_COLOR = new Color(50, 50, 50);

    /**
     * 버튼 폰트
     */
    public static final Font BUTTON_FONT = new Font("굴림", Font.PLAIN, 12);

    /**
     * 버튼 폰트색
     */
    public static final Color BUTTON_COLOR = new Color(50, 50, 50);

    /**
     * 라벨 폰트
     */
    public static final Font LABEL_FONT = new Font("굴림", Font.PLAIN, 12);

    /**
     * 라벨 폰트색
     */
    public static final Color LABEL_COLOR = new Color(50, 50, 50);
}
