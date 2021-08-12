/*
 * Filename : LabelTextField.java
 * Class : LabelTextField
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import java.awt.event.KeyListener;
import javax.swing.Box;

/**
 * LabelTextField Ŭ����
 * �󺧰� �ؽ�Ʈ �ʵ带 �����ϴ� UI ������Ʈ 
 * Copyright: (��)�ֿ��������
 * 
 * @author Kooin Shin
 * @version 1.0
 * @since JDK1.3.1
 */
public class LabelTextField extends PanelElement
{
    /** �ؽ�Ʈ �ʵ� ��ü */
    JTextField textField;
    
    /** ��ť��Ʈ */
    BoundDocument doc;
    
    /** �ؽ�Ʈ �ʵ� ���� */
    int column;
    
    /** �Է� ���� ���� */
    int limit;
    
    /** �������� ���� */
    boolean editable = true;
    
    /**
     * ���̸��� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#PanelElement(String)
     */    
    public LabelTextField(String labelName)
    {
        this(labelName, labelName.length());
    }
    
    /**
     * ���̸��� �÷� ���̸� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param column �÷� �ε���
     */
    public LabelTextField(String labelName, int column)
    {
        this(labelName, column, column);
    }
    
    /**
     * ���̸�, �÷�������, �������ɿ��θ� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param column �÷� �ε���
     * @param editable ���� ����
     */
    public LabelTextField(String labelName, int column, boolean editable)
    {
        this(labelName, column, column, editable, "");
    }
        
    /**
     * ���̸��� �÷� ����, �Է� ���� ���̸� ���ڷ� ���� ������
     * @param labelName ��Ҷ� �̸�
     * @param column �ؽ�Ʈ �ʵ� ����
     * @param limit �Ѱ谪
     */
    public LabelTextField(String labelName, int column, int limit)
    {
        this(labelName, column, limit, true, "");
    }    
    
    /**
     * ���̸�, �÷� ����, �Է����� ����, �������ɿ��θ� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param column �÷� �ε���
     * @param limit �Ѱ谪
     * @param editable ��������
     * @param validChars ��ȿ���ڿ�
     */    
    public LabelTextField(String labelName, int column, int limit, boolean editable, String validChars)
    {
        super(labelName);
        this.column = column;
        this.editable = editable;
        this.textField = new JTextField(column);
        this.doc = new BoundDocument(limit, this.textField, validChars);
        this.textField.setDocument(doc);
        this.textField.setEditable(editable);
        this.textField.setBackground(Color.white);
        setting(textField);
        make(textField);
        setPreferredSize(new Dimension(column, getPreferredSize().height));
    }
    
    /**
     * KElement Ŭ������ ���� �������̵� �� �޼��� �ؽ�Ʈ �ʵ忡 ���� ������ �����ϰ� �ȴ�.
     * @param jc ������ ������ ������Ʈ
     */
    public void setting(Component jc)
    {
    }
    
    /**
     * KElement Ŭ������ ���� �������̵� �� �޼��� ������Ʈ�� ����� ������ �Ѵ�.
     */
    public void make(Component jc)
    {
        add(Box.createHorizontalGlue());
        add(jc);
    }
    
    /**
     * �ؽ�Ʈ �ʵ��� ���ڿ��� ��´�.
     * @return ���ڿ�
     */
    public String getText()
    {
        return this.textField.getText();
    }
    
    /**
     * �ؽ�Ʈ �ʵ� ��ü�� �����´�.
     * @return �ؽ�Ʈ �ʵ� ��ü
     */
    public JTextField getTextField()
    {
        return this.textField;
    }
    
    /**
     * �ķ� ����� ��´�.
     * @return int
     */
    public int getColumn()
    {
        return this.column;
    }
    
    /**
     * �ִ� �Է� ����� ��´�.
     * @return int
     */
    public int getLimit()
    {
        return this.limit;
    }
    
    /**
     * �ؽ�Ʈ �ʵ忡 ���ڿ��� ����
     * @param text ���ڿ�
     */
    public void setText(String text)
    {
        this.textField.setText(text);
    }
    
    /**
     * Ű �����ʸ� �����Ѵ�.
     * @param kl Ű ������
     */
    public void addKeyListener(KeyListener kl)
    {
        this.textField.addKeyListener(kl);
    }
    
    /**
     * Ȱ��ȭ ���θ� �����Ѵ�.
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */    
    public void doEnabled(boolean is)
    {
        this.textField.setEnabled(is);
    }
    
    /**
     * �������ɿ��θ� �����Ѵ�.
     * @param is �������ɿ���
     */
    public void setEditable(boolean is)
    {
        this.textField.setEditable(is);
    }

	/**
     * ������Ʈ ���� ��´�. 
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
	 */
	public Object getValue()
	{
		return getText();
	}

	/**
     * ������Ʈ ���� �����Ѵ�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
	 */
	public void setValue(Object value)
	{
        setText(value+"");
	}

	/**
     * ���� ������Ʈ�� ��´�.
	 * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
	 */
	public Component getMultiComponentElement()
	{
		return this;
	}
}



