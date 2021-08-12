/*
 * Filename : SpinField.java
 * Class : SpinField
 */
package org.chaostocosmos.chaosdashboard.client.ui;

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
 * SpinField Ŭ����
 * �ؽ�Ʈ �ʵ� + ��ũ�� �ٷ� �̷���� ������Ʈ
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class SpinField extends JPanel implements AdjustmentListener, ActionListener
{
    /** ���� ���� ���� */
    public static final int UNIT_ALIGN_LEFT = 0;

    /** ���� ���� ������ */
    public static final int UNIT_ALIGN_RIGHT = 1;

    /** �ּҰ� */
    int minimum;
    
    /** �ִ밪 */
    int maximum;
    
    /** �ؽ�Ʈ  �ʵ� �÷����� */
    int column;
    
    /** �� */
    int value = 0;

    /** ����ġ */
    int amount = 1;
    
    /** ���� ��Ʈ�� */
    String unit = "";

    /** ���� ��Ʈ�� */
    String frontUnit = "";

    /** ���� ���� ��� */
    int unitAlign = 1;
    
    /** ��ũ�� �� */
    JScrollBar jsb;
    
    /** �ؽ�Ʈ �ʵ� */
    JTextField jtf;

    /** �ؽ�Ʈ �ʵ� ���ڼ� */
    Color foregroundColor = Color.black;

    /** ���� �ʵ� ������ ��� */
    SpinFieldListener[] listeners;
    
    /**
     * ����Ʈ ������
     * @see java.lang.Object#Object()
     */
    public SpinField()
    {
        this(0, Integer.MAX_VALUE);
    }

    /**
     * �ּҰ�, �ִ밪�� ���ڷ� ���� ������
     * @param donw ���Ѱ�
     * @param maximum ���Ѱ�
     */
    public SpinField(int minimum, int maximum)
    {
        this(minimum, maximum, 1);
    }

    /**
     * �ּҰ�, �ִ밪, ����ġ, ���̸� ���ڷ� ���� ������
     * @param donw ���Ѱ�
     * @param maximum ���Ѱ�
     * @param amount ����ġ
     * @param column �÷� ��
     */
    public SpinField(int minimum, int maximum, int amount)
    {
        this(minimum, maximum, amount, "");
    }
    
    /**
     * �ּҰ�, �ִ밪, ����ġ, ���� ��Ʈ���� ���ڷ� ���� ������
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
     * �ּҰ�, �ִ밪, ����ġ, ���̸� ���ڷ� ���� ������
     * @param donw ���Ѱ�
     * @param maximum ���Ѱ�
     * @param amount ����ġ
     * @param unit ���� ��Ʈ��
     */
    public SpinField(int minimum, int maximum, int amount, String unit, int column)
    {
        this(minimum, maximum, amount, unit, column, SpinField.UNIT_ALIGN_RIGHT);
    }

    /**
     * �ּҰ�, �ִ밪, ����ġ, ����, ���� ��Ʈ���� ���ڷ� ���� ������
     * @param minimum ���Ѱ�
     * @param maximum ���Ѱ�
     * @param amount ����ġ
     * @param unit ���� ��Ʈ��
     * @param column �÷� ��
     * @param unitAlign ����
     */
    public SpinField(int minimum, int maximum, int amount, String unit, int column, int unitAlign)
    {
        this("", minimum, maximum, amount, unit, column, unitAlign);
    }

    /**
     * �ּҰ�, �ִ밪, ����ġ, ����, ���� ��Ʈ���� ���ڷ� ���� ������
     * @param minimum ���Ѱ�
     * @param maximum ���Ѱ�
     * @param amount ����ġ
     * @param unit ���� ��Ʈ��
     * @param column �÷� ��
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
     * ������Ʈ �ʱ�ȭ
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
     * �ؽ�Ʈ �ʵ��� ���� �����Ѵ�.
     * @param newValue ��
     * @param isUpdateTextField �ؽ�Ʈ �ʵ��� ���� ������ ������ ����
     * @param isUpdateScrollBar ��ũ�� ���� ���� ������ ������ ����
     */
    protected void setSpinFieldValue(int newValue, boolean isUpdateTextField, boolean isUpdateScrollBar)
    {
        int oldValue = value;
        if(newValue < minimum)
        {
            this.showWarningMessage("�Է¿���", "�ּҰ� ���� ������ �Դϴ�.");
            value = oldValue;
        }
        else if(newValue > maximum)
        {
            this.showWarningMessage("�Է¿���", "�ִ밪 ���� ū�� �Դϴ�.");
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
     * �ؽ�Ʈ �ʵ��� ���� �����Ѵ�.
     * @param newValue �Է� ��
     */
    public void setSpinFieldValue(int newValue)
    {
        setSpinFieldValue(newValue, true, true);
    }
    
    /**
     * ���� �ʵ� ���� �����Ѵ�.
     * @param newVal �����ʵ� ��
     */
    public void setSpinFieldValue(Object newVal)
    {
        setSpinFieldValue(Integer.parseInt(newVal+""));
    }

    /**
     * �ؽ�Ʈ �ʵ� ���� ��´�.
     * @return int �ؽ�Ʈ �ʵ� ��
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
     * ���� ���� ��´�.
     * @return int ���� ��
     */
    public int getRealValue()
    {
        return this.value;
    }
    
    /**
     * ����ġ�� ���Ѵ�.
     * @return int ����ġ
     */
    public int getAmount()
    {
        return this.amount;
    }
    
    /**
     * ���� ��Ʈ���� ���Ѵ�.
     * @return String ���� ��Ʈ��
     */
    public String getUnit()
    {
        return this.unit;
    }
    
    /**
     * �����ʵ� ���� ��´�.
     * @return String �����ʵ� ���� ��´�.
     */
    public String getText()
    {
        return jtf.getText().trim();
    }

    /**
     * �ؽ�Ʈ �ʵ尪�� �����Ѵ�.
     * @param value �ʵ� ��
     * @param unitAlign ���Ĺ��
     */
    public void setText(int value, int unitAlign)
    {
        if(unitAlign == 0)
            jtf.setText(unit+value);
        else
            jtf.setText(value+unit);
    }

    /**
     * �ؽ�Ʈ �ʵ尪�� �����Ѵ�.
     * @param str ��Ʈ�� ��
     * @param value �ʵ尪
     * @param unitAlign ���Ĺ��
     */
    public void setText(String str, int value, int unitAlign)
    {
        if(unitAlign == 0)
            jtf.setText(str+unit+value);
        else
            jtf.setText(str+value+unit);
    }

    /**
     * ������ ǥ���� ��Ʈ���� �����Ѵ�.
     * @param unit ���� ��Ʈ���� �����Ѵ�.
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    /**
     * �ּҰ��� ��´�.
     * @return int �ּҰ�
     */
    public int getMinimum()
    {
        return this.minimum;
    }

    /**
     * �ּҰ��� ����
     * @param minimum �ּҰ�
     */
    public void setMin(int minimum)
    {
        this.minimum = minimum;
        jsb.setMinimum(this.minimum);
    }

    /**
     * �ִ밪�� ��´�.
     * @return int �ִ밪
     */
    public int getMaximum()
    {
        return this.maximum;
    }

    /**
     * �ִ밪�� ����
     * @param maximum �ִ밪
     */
    public void setMax(int maximum)
    {
        this.maximum = maximum;
        jsb.setMaximum(this.maximum);
    }
    
    /**
     * �÷� ����� �����Ѵ�.
     * @param size �÷� ������
     */
    public void setSize(Dimension size)
    {
        setPreferredSize(size);
    }
    
    /**
     * �÷� ���̸� ��´�.
     * @param int �÷� ����
     */
    public int getColumnWidth()
    {
        return jtf.getColumns();
    }

    /**
     * �����ʵ� �̺�Ʈ�� �����ʵ� �����ʷ� �����Ѵ�.
     */
    public void sendEventToListeners()
    {
        if(listeners.length != 0)
            for(int i=0; i<listeners.length; i++)
                listeners[i].spinFieldEventReceived(new SpinFieldEvent(this, this.unit, getSpinFieldValue()));
    }

    /**
     * �׼� �����ʸ� ���δ�
     * @param listener �����ʸ� �߰��Ѵ�.
     */
    public void addActionListener(ActionListener listener)
    {
        jtf.addActionListener(listener);
    }

    /**
     * ��ũ�ѹ� �����ʸ� ���δ�.
     * @param listener �����ʸ� �߰��Ѵ�.
     */
    public void addAdjustmentListener(AdjustmentListener listener)
    {
        jsb.addAdjustmentListener(listener);
    }

    /**
     * Ű �����ʸ� ���δ�.
     * @param listener �����ʸ� �߰��Ѵ�.
     */
    public void addKeyListener(KeyListener listener)
    {
        jtf.addKeyListener(listener);
    }

    /**
     * ���� �޽����� �����ش�.
     * @param title Ÿ��Ʋ
     * @param msg �޽�������
     */
    public void showWarningMessage(String title, String msg)
    {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * TextField��ü�� ��´�.
     * @return JTextField �ؽ�Ʈ �ʵ带 ��´�.
     */
    public JTextField getTextField()
    {
        return this.jtf;
    }

    /**
     * ScrollBar��ü�� ��´�.
     * @return JScrollBar ��ũ�� �ٸ� ��´�.
     */
    public JScrollBar getScrollBar()
    {
        return this.jsb;
    }
    
    /**
     * ������ �����Ѵ�
     * @param c ������ �����Ѵ�.
     */
    public void setBackground(Color c)
    {
        if(jtf != null)
            this.jtf.setBackground(c);
    }
    
    /**
     * ������� �����Ѵ�
     * @param c ������� �����Ѵ�.
     */
    public void setForeground(Color c)
    {
        if(jtf != null)
            this.jtf.setForeground(c);
    }

    /**
     * �ؽ�Ʈ ���ڻ��� ��´�.
     * @return Color ���ڻ��� �����Ѵ�. 
     */
    public Color getForegroundColor()
    {
        return this.foregroundColor;
    }

    /**
     * �ؽ�Ʈ ���ڻ��� �����Ѵ�. 
     * @param foregroundColor ���ڻ���
     */
    public void setForegroundColor(Color foregroundColor)
    {
        this.foregroundColor = foregroundColor;
    }

    /**
     * ���� ���� ����� ��´�.
     * @return int ���� ���� ���
     */
    public int getUnitAlign()
    {
        return this.unitAlign;
    }

    /**
     * ���� ���� ����� �����Ѵ�.
     * @param unitAlign ���� ���� ���
     */
    public void setUnitAlign(int unitAlign)
    {
        this.unitAlign = unitAlign;
    }
    
    /**
     * �����ʵ� Ȱ��ȭ ���� ����
     * @param is �����ʵ� Ȱ��ȭ ����
     */    
    public void setEnabled(boolean is)
    {
        jsb.setEnabled(is);
        jtf.setEnabled(is);
    }    
    
    /**
     * �����ʵ� �����ʸ� �߰��Ѵ�.
     * @param listener �����ʵ� �����ʸ� �߰��Ѵ�.
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
     * �ؽ�Ʈ �ʵ� �Է½�
     * @param ae �׼� �̺�Ʈ
     */
    public void actionPerformed(ActionEvent ae)
    {
        setSpinFieldValue(getSpinFieldValue());
        sendEventToListeners();
    }

    ////////////////////////////// implements from AdjustmentListener //////////////////////////////
    
    /**
     * ��ũ�ѹ��� ����ȭ
     * @param ae ��� ����Ʈ �̺�Ʈ
     */
    public void adjustmentValueChanged(AdjustmentEvent ae)
    {
        int val = maximum+minimum-ae.getValue();
        setSpinFieldValue(val, true, false);
        sendEventToListeners();
    }

    /**
     * Ű �̺�Ʈ �ڵ鷯
     */
    class KeyAction extends KeyAdapter
    {
        /**
         * Ű�� ������ ��
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
