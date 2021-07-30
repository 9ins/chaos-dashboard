/*
 * Filename : SpinField.java
 * Class : SpinField
 */
package org.chaos.mgmt.client.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

/**
 * SpinField 클래스
 * 텍스트 필드 + 스크롤 바로 이루어진 컴포넌트
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class SpinField extends JPanel implements AdjustmentListener, ActionListener
{
    /** 유닛 정렬 왼쪽 */
    public static final int UNIT_ALIGN_LEFT = 0;

    /** 유닛 정렬 오른쪽 */
    public static final int UNIT_ALIGN_RIGHT = 1;

    /** 최소값 */
    int minimum;
    
    /** 최대값 */
    int maximum;
    
    /** 텍스트  필드 컬럼넓이 */
    int column;
    
    /** 값 */
    int value = 0;

    /** 증가치 */
    int amount = 1;
    
    /** 단위 스트링 */
    String unit = "";

    /** 단위 스트링 */
    String frontUnit = "";

    /** 유닛 정렬 방식 */
    int unitAlign = 1;
    
    /** 스크롤 바 */
    JScrollBar jsb;
    
    /** 텍스트 필드 */
    JTextField jtf;

    /** 텍스트 필드 글자섹 */
    Color foregroundColor = Color.black;

    /** 스핀 필드 리스너 목록 */
    SpinFieldListener[] listeners;
    
    /**
     * 디폴트 생성자
     * @see java.lang.Object#Object()
     */
    public SpinField()
    {
        this(0, Integer.MAX_VALUE);
    }

    /**
     * 최소값, 최대값을 인자로 가는 생성자
     * @param donw 하한값
     * @param maximum 상한값
     */
    public SpinField(int minimum, int maximum)
    {
        this(minimum, maximum, 1);
    }

    /**
     * 최소값, 최대값, 증가치, 넓이를 인자로 갖는 생성자
     * @param donw 하한값
     * @param maximum 상한값
     * @param amount 증가치
     * @param column 컬럼 수
     */
    public SpinField(int minimum, int maximum, int amount)
    {
        this(minimum, maximum, amount, "");
    }
    
    /**
     * 최소값, 최대값, 증가치, 단위 스트링을 인자로 갖는 생성자
     * @param minimum
     * @param maximum
     * @param amount
     * @param unit
     */    
    public SpinField(int minimum, int maximum, int amount, String unit)
    {
        this(minimum, maximum, amount, unit, (Math.max(maximum, minimum)+unit).length()+1);
    }

    /**
     * 최소값, 최대값, 증가치, 넓이를 인자로 갖는 생성자
     * @param donw 하한값
     * @param maximum 상한값
     * @param amount 증가치
     * @param unit 단위 스트링
     */
    public SpinField(int minimum, int maximum, int amount, String unit, int column)
    {
        this(minimum, maximum, amount, unit, column, SpinField.UNIT_ALIGN_RIGHT);
    }

    /**
     * 최소값, 최대값, 증가치, 넓이, 단위 스트링을 인자로 갖는 생성자
     * @param minimum 하한값
     * @param maximum 상한값
     * @param amount 증가치
     * @param unit 단위 스트링
     * @param column 컬럼 수
     * @param unitAlign 정렬
     */
    public SpinField(int minimum, int maximum, int amount, String unit, int column, int unitAlign)
    {
        this("", minimum, maximum, amount, unit, column, unitAlign);
    }

    /**
     * 최소값, 최대값, 증가치, 넓이, 단위 스트링을 인자로 갖는 생성자
     * @param minimum 하한값
     * @param maximum 상한값
     * @param amount 증가치
     * @param unit 단위 스트링
     * @param column 컬럼 수
     */
    public SpinField(String frontUnit, int minimum, int maximum, int amount, String unit, int column, int unitAlign)
    {
        super(new BorderLayout());
        this.maximum = Math.max(minimum, maximum);
        this.minimum = Math.min(minimum, maximum);
        this.amount = amount;
        this.unit = ""+unit;
        this.unitAlign = unitAlign;
        this.column = column;
        this.frontUnit = frontUnit;
        this.listeners = new SpinFieldListener[0];
        initComp();
    }
    
    /**
     * 컴포넌트 초기화
     */
    private void initComp()
    {
        jtf = new JTextField();
        jtf.setColumns(this.column);
        if (frontUnit != null)
            setText(frontUnit, minimum, unitAlign);
        else
            setText(minimum, unitAlign);
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        jtf.addActionListener(this);
        jtf.addKeyListener(new KeyAction());
        add(jtf, BorderLayout.CENTER);
        jsb = new JScrollBar(JScrollBar.VERTICAL);
        jsb.setMinimum(minimum-1);
        jsb.setMaximum(maximum+1);
        jsb.setVisibleAmount(0);
        jsb.setUnitIncrement(amount);
        jsb.addAdjustmentListener(this);
        jsb.setPreferredSize(new Dimension(jsb.getPreferredSize().width, jtf.getPreferredSize().height));
        add(jsb, BorderLayout.EAST);
        setSpinFieldValue(minimum, true, true);
    }

    /**
     * 텍스트 필드의 값을 셋팅한다.
     * @param newValue 값
     * @param isUpdateTextField 텍스트 필드의 값을 갱신할 것인지 여부
     * @param isUpdateScrollBar 스크롤 바의 값을 갱신할 것인지 여부
     */
    protected void setSpinFieldValue(int newValue, boolean isUpdateTextField, boolean isUpdateScrollBar)
    {
        int oldValue = value;
        if(newValue < minimum)
        {
            this.showWarningMessage("입력오류", "최소값 보다 작은값 입니다.");
            value = oldValue;
        }
        else if(newValue > maximum)
        {
            this.showWarningMessage("입력오류", "최대값 보다 큰값 입니다.");
            value = oldValue;
        }
        else
        {
            value = newValue;
        }
        if(isUpdateTextField)
        {
            setText(value, unitAlign);
            jtf.setForeground(this.foregroundColor);
        }
        if(isUpdateScrollBar)
        {
            jsb.setValue(minimum+maximum-value);
        }
        firePropertyChange("value", oldValue, value);
    }
    
    /**
     * 텍스트 필드의 값을 셋팅한다.
     * @param newValue 입력 값
     */
    public void setSpinFieldValue(int newValue)
    {
        setSpinFieldValue(newValue, true, true);
    }
    
    /**
     * 스핀 필드 값을 설정한다.
     * @param newVal 스핀필드 값
     */
    public void setSpinFieldValue(Object newVal)
    {
        setSpinFieldValue(Integer.parseInt(newVal+""));
    }

    /**
     * 텍스트 필드 값을 얻는다.
     * @return int 텍스트 필드 값
     */
    public int getSpinFieldValue()
    {
        String str = jtf.getText().trim();
        int n = str.indexOf(unit.trim());
        if(n > 0) str = str.substring(0, n).trim();
        int val = 0;
        try
        {
            val = Integer.parseInt(str);
        }
        catch(NumberFormatException e)
        {
            val = value;
        }
        return val;
    }    

    /**
     * 실제 값을 얻는다.
     * @return int 실제 값
     */
    public int getRealValue()
    {
        return this.value;
    }
    
    /**
     * 증가치를 구한다.
     * @return int 증가치
     */
    public int getAmount()
    {
        return this.amount;
    }
    
    /**
     * 단위 스트링을 구한다.
     * @return String 단위 스트링
     */
    public String getUnit()
    {
        return this.unit;
    }
    
    /**
     * 스핀필드 값을 얻는다.
     * @return String 스핀필드 값을 얻는다.
     */
    public String getText()
    {
        return jtf.getText().trim();
    }

    /**
     * 텍스트 필드값을 설정한다.
     * @param value 필드 값
     * @param unitAlign 정렬방식
     */
    public void setText(int value, int unitAlign)
    {
        if(unitAlign == 0)
            jtf.setText(unit+value);
        else
            jtf.setText(value+unit);
    }

    /**
     * 텍스트 필드값을 설정한다.
     * @param str 스트링 값
     * @param value 필드값
     * @param unitAlign 정렬방식
     */
    public void setText(String str, int value, int unitAlign)
    {
        if(unitAlign == 0)
            jtf.setText(str+unit+value);
        else
            jtf.setText(str+value+unit);
    }

    /**
     * 단위를 표시할 스트링을 셋팅한다.
     * @param unit 단위 스트링을 설정한다.
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    /**
     * 최소값을 얻는다.
     * @return int 최소값
     */
    public int getMinimum()
    {
        return this.minimum;
    }

    /**
     * 최소값을 셋팅
     * @param minimum 최소값
     */
    public void setMin(int minimum)
    {
        this.minimum = minimum;
        jsb.setMinimum(this.minimum);
    }

    /**
     * 최대값을 얻는다.
     * @return int 최대값
     */
    public int getMaximum()
    {
        return this.maximum;
    }

    /**
     * 최대값을 셋팅
     * @param maximum 최대값
     */
    public void setMax(int maximum)
    {
        this.maximum = maximum;
        jsb.setMaximum(this.maximum);
    }
    
    /**
     * 컬럼 사이즈를 설정한다.
     * @param size 컬럼 사이즈
     */
    public void setSize(Dimension size)
    {
        setPreferredSize(size);
    }
    
    /**
     * 컬럼 넓이를 얻는다.
     * @param int 컬럼 넓이
     */
    public int getColumnWidth()
    {
        return jtf.getColumns();
    }

    /**
     * 스핀필드 이벤트를 스핀필드 리스너로 전파한다.
     */
    public void sendEventToListeners()
    {
        if(listeners.length != 0)
            for(int i=0; i<listeners.length; i++)
                listeners[i].spinFieldEventReceived(new SpinFieldEvent(this, this.unit, getSpinFieldValue()));
    }

    /**
     * 액션 리스너를 붙인다
     * @param listener 리스너를 추가한다.
     */
    public void addActionListener(ActionListener listener)
    {
        jtf.addActionListener(listener);
    }

    /**
     * 스크롤바 리스너를 붙인다.
     * @param listener 리스너를 추가한다.
     */
    public void addAdjustmentListener(AdjustmentListener listener)
    {
        jsb.addAdjustmentListener(listener);
    }

    /**
     * 키 리스너를 붙인다.
     * @param listener 리스너를 추가한다.
     */
    public void addKeyListener(KeyListener listener)
    {
        jtf.addKeyListener(listener);
    }

    /**
     * 주의 메시지를 보여준다.
     * @param title 타이틀
     * @param msg 메시지내용
     */
    public void showWarningMessage(String title, String msg)
    {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * TextField객체를 얻는다.
     * @return JTextField 텍스트 필드를 얻는다.
     */
    public JTextField getTextField()
    {
        return this.jtf;
    }

    /**
     * ScrollBar객체를 얻는다.
     * @return JScrollBar 스크롤 바를 얻는다.
     */
    public JScrollBar getScrollBar()
    {
        return this.jsb;
    }
    
    /**
     * 배경색을 설정한다
     * @param c 배경색을 설정한다.
     */
    public void setBackground(Color c)
    {
        if(jtf != null)
            this.jtf.setBackground(c);
    }
    
    /**
     * 전경색을 설정한다
     * @param c 전경색을 설정한다.
     */
    public void setForeground(Color c)
    {
        if(jtf != null)
            this.jtf.setForeground(c);
    }

    /**
     * 텍스트 글자색을 얻는다.
     * @return Color 글자색을 설정한다. 
     */
    public Color getForegroundColor()
    {
        return this.foregroundColor;
    }

    /**
     * 텍스트 글자색을 설정한다. 
     * @param foregroundColor 글자색깔
     */
    public void setForegroundColor(Color foregroundColor)
    {
        this.foregroundColor = foregroundColor;
    }

    /**
     * 유닛 정렬 방식을 얻는다.
     * @return int 유닛 정렬 방식
     */
    public int getUnitAlign()
    {
        return this.unitAlign;
    }

    /**
     * 유닛 정렬 방식을 설정한다.
     * @param unitAlign 유닛 정렬 방식
     */
    public void setUnitAlign(int unitAlign)
    {
        this.unitAlign = unitAlign;
    }
    
    /**
     * 스핀필드 활성화 여부 설정
     * @param is 스핀필드 활성화 여부
     */    
    public void setEnabled(boolean is)
    {
        jsb.setEnabled(is);
        jtf.setEnabled(is);
    }    
    
    /**
     * 스핀필드 리스너를 추가한다.
     * @param listener 스핀필드 리스너를 추가한다.
     */
    public void addSpinFieldListener(SpinFieldListener listener)
    {
        SpinFieldListener[] ql = new SpinFieldListener[this.listeners.length+1];
        System.arraycopy(this.listeners, 0, ql, 0, this.listeners.length);
        ql[this.listeners.length] = listener;
        this.listeners = ql;
        ql = null;
    }
    
    ////////////////////////////// implements from ActionListener //////////////////////////////
    
    /**
     * 텍스트 필드 입력시
     * @param ae 액션 이벤트
     */
    public void actionPerformed(ActionEvent ae)
    {
        setSpinFieldValue(getSpinFieldValue());
        sendEventToListeners();
    }

    ////////////////////////////// implements from AdjustmentListener //////////////////////////////
    
    /**
     * 스크롤바의 값변화
     * @param ae 어드 저스트 이벤트
     */
    public void adjustmentValueChanged(AdjustmentEvent ae)
    {
        int val = maximum+minimum-ae.getValue();
        setSpinFieldValue(val, true, false);
        sendEventToListeners();
    }

    /**
     * 키 이벤트 핸들러
     */
    class KeyAction extends KeyAdapter
    {
        /**
         * 키가 눌렸을 시
         * @see java.awt.event.KeyListener#keyPressed(KeyEvent)
         */
        public void keyPressed(KeyEvent ke)
        {
            int keycode = ke.getKeyCode();
            if(keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_PAGE_UP)
            {
                int val = getSpinFieldValue()+amount;
                setSpinFieldValue(val, true, true);
            }
            else if(keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_PAGE_DOWN)
            {
                int val = getSpinFieldValue()-amount;
                setSpinFieldValue(val, true, true);
            }
        }
    }
}
