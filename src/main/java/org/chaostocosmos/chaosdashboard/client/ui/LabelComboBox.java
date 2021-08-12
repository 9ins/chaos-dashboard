/*
 * Filename : LabelComboBox.java
 * Class : LabelComboBox
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;


/**
 * LabelComboBox Ŭ����
 * �󺧰� �޺��ڽ��� �����ϴ� UI ������Ʈ
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class LabelComboBox extends PanelElement implements ItemListener
{
    /** �޺��ڽ� ��ü */
    JComboBox comboBox;

    /** �޺� ������ ��� */
    ComboItem[] comboItems;
    
    /** ���� �ε��� */
    int selectedIndex;
    
    /** �޺��ڽ� ���� ���� */
    boolean isEditable;

    /**
     * ���̸�, �޺� ������ �迭�� ���ڷ� ���� ������
     * @param labelName ���̸�
     * @param items ������ ���
     */
    public LabelComboBox(String labelName, Object[] items)
    {
        this(labelName, items, 50);
    }

    /**
     * ���̸�, �޺� ������, �޺� ����� ���ڷ� ���� ������
     * @param labelName ���̸�
     * @param items ������ ���
     * @param size �޺� ������
     */
    public LabelComboBox(String labelName, Object[] items, int width)
    {
        this(labelName, items, width, 22);
    }

    /**
     * ���̸�, �޺� ������, �޺� ����� ���ڷ� ���� ������
     * @param labelName ���̸�
     * @param items ������ ���
     * @param width �޺� ������
     * @param height �޺� ����
     */
    public LabelComboBox(String labelName, Object[] items, int width, int height)
    {
        super(labelName);
        ComboItem[] comboItems = null;
        if(items != null)
        {
            comboItems = new ComboItem[items.length];
            for(int i=0; i<comboItems.length; i++)
                comboItems[i] = new ComboItem(items[i], items[i]);
        }
        init(comboItems, width, height);
    }
    
    /**
     * ���̸�, �����۸��, �ڵ� ����� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param items ������ ���
     * @param codes �ڵ� ���
     */
    public LabelComboBox(String labelName, Object[] items, Object[] codes)
    {
        this(labelName, items, codes, 50);
    }
    
    /**
     * ���̸�, ������ ���, �ڵ� ���, ���̸� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param items ������ ���
     * @param codes �ڵ� ���
     * @param width ����
     */
    public LabelComboBox(String labelName, Object[] items, Object[] codes, int width)
    {
        this(labelName, items, codes, width, 22);
    }
    
    /**
     * ���̸�, ������ ���, �ڵ���, ����, ���̸� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param items ������ ���
     * @param codes �ڵ� ���
     * @param width ����
     * @param height ����
     */
    public LabelComboBox(String labelName, Object[] items, Object[] codes, int width, int height)
    {
        super(labelName);
        ComboItem[] comboItems = null;
        if(items != null || codes != null)
        {
            comboItems = new ComboItem[items.length];
            for(int i=0; i<comboItems.length; i++)
                comboItems[i] = new ComboItem(items[i], codes[i]);
        }
        init(comboItems, width, height);
    }
    
    /**
     * ���̸�, �޺� �������� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param comboItems �޺� ������ ���
     */
    public LabelComboBox(String labelName, ComboItem[] comboItems)
    {
        this(labelName, comboItems, 50);
    }
    
    /**
     * ���̸�, �޺� ������, ���̸� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param comboItems �޺� ������ ���
     * @param width ����
     */
    public LabelComboBox(String labelName, ComboItem[] comboItems, int width)
    {
        this(labelName, comboItems, width, 22);    
    }
    
    /**
     * ���̸�, �޺� ������, ����, ���̸� ���ڷ� ���� ������
     * @param labelName �� �̸�
     * @param comboItems �޺� ������ ���
     * @param width ����
     * @param height ����
     */
    public LabelComboBox(String labelName, ComboItem[] comboItems, int width, int height)
    {
        super(labelName);
        init(comboItems, width, height);
    }
    
    /**
     * �ʱ�ȭ
     * @param comboItems �޺� ������ ���
     * @param width ����
     * @param height ����
     */
    protected void init(ComboItem[] comboItems, int width, int height)
    {
        if(comboItems == null || comboItems.length == 0)
            this.comboItems = new ComboItem[]{new ComboItem("", "")};
        else
            this.comboItems = comboItems;
        this.comboBox = new JComboBox(getItems());
        int w = (int)this.comboBox.getPreferredSize().getWidth();
        int h = (int)this.comboBox.getPreferredSize().getHeight();
        width = Math.max(width, w);
        height = Math.max(height, h);
        if(width != 0 && height != 0)
            this.comboBox.setPreferredSize(new Dimension(width, height));
        setting(this.comboBox);
        make(this.comboBox);
        addItemListener(this);
    }
    
    /**
     * ������ ����� ��´�.
     * @return Object[] ������ ���
     */
    public Object[] getItems()
    {
        Object[] o = new Object[comboItems.length];
        for(int i=0; i<o.length; i++)
            o[i] = this.comboItems[i].getItem();
        return o;
    }
    
    /**
     * �ڵ� ����� ��´�.
     * @return Object[] �޵� ���
     */
    public Object[] getCodes()
    {
        Object[] o = new Object[comboItems.length];
        for(int i=0; i<o.length; i++)
            o[i] = this.comboItems[i].getCode();
        return o;
    }

    /**
     * �޺��ڽ��� ActionListener�� �����Ѵ�.
     * @param al �׼� ������ ��ü
     */
    public void addActionListener(ActionListener al)
    {
        this.comboBox.addActionListener(al);
    }
    
    /**
     * �޺��ڽ��� ItemListener�� �����Ѵ�.
     * @param il ������ ������
     */
    public void addItemListener(ItemListener il)
    {
        this.comboBox.addItemListener(il);
    }
    
    /**
     * ���� �������� ��´�.
     * @return ���ð�ü
     */
    public Object getSelectedItem()
    {
        return this.comboItems[this.selectedIndex].getItem();
    }
    
    /**
     * ���� �ڵ带 ��´�.
     * @return Object ���� �ڵ�
     */
    public Object getSelectedCode()
    {
        return this.comboItems[this.selectedIndex].getCode();
    }
    
    /**
     * �ڵ尪�� �����Ѵ�.
     * @param code
     */
    public void setSelectedCode(Object code)
    {
        this.comboItems[this.selectedIndex].setCode(code);
        this.comboBox.setSelectedItem(code);
    }
    
    /**
     * ������ ���� �����Ѵ�.
     * @param item
     */
    public void setSelectedItem(Object item)
    {
        this.comboItems[this.selectedIndex].setItem(item);
        this.comboBox.setSelectedItem(item);
    }

    /**
     * �޺��ڽ����� ���õ� �ε����� �����Ѵ�.
     * @return ���õ� �ε���
     */
    public int getSelectedIndex()
    {
        return this.selectedIndex;
    }

    /**
     * �޺� �ڽ� ������ �迭�� ���Ѵ�.
     * @return Object[] �޺��ڽ� ������ ���
     */
    public ComboItem[] getComboItems()
    {
        return comboItems;
    }

    /**
     * �޺� �ڽ��� ��´�.
     * @return JComboBox �޺��ڽ�
     */
    public JComboBox getComboBox()
    {
        return comboBox;
    }
    
    /**
     * ���ڷ� �־��� ������ ������� �޺��ڽ� �������� �����Ѵ�.
     * @param items �޺� ������ ���
     */
    public void setItems(Object[] items)
    {
        this.comboItems = new ComboItem[items.length];
        for(int i=0; i<items.length; i++)
            this.comboItems[i] = new ComboItem(items[i], items[i]);
        setComboItems(this.comboItems);
    }
    
    /**
     * ���ڷ� �־��� ������ ��� �ڵ� ������� �޺��ڽ� �������� �����Ѵ�.
     * @param items �޺� ������ ���
     * @param codes �޺� �ڵ� ���
     */
    public void setItemsNCodes(Object[] items, Object[] codes)
    {
        this.comboItems = new ComboItem[items.length];
        for(int i=0; i<items.length; i++)
            this.comboItems[i] = new ComboItem(items[i], codes[i]);
        setComboItems(this.comboItems);
    }
    
    /**
     * ���ڷ� �־��� ���������� �޺��ڽ� ���� ��ü�Ѵ�.
     * @param item �޺� ������
     */
    public void setComboItems(ComboItem[] comboItems)
    {
        this.comboItems = comboItems;
        Object[] items = getItems();
        if(this.comboBox == null)
            this.comboBox = new JComboBox(items);
        else
        {
            this.comboBox.removeAllItems();
            for(int i=0;i<items.length;i++)
                this.comboBox.addItem(items[i]);
        }
    }
    
    /**
     * �޺��ڽ� ���� ���� ����
     * @param editable ���� ���ɿ���
     */
    public void setEditable(boolean editable)
    {
        this.comboBox.setEditable(editable);
    }
    
    /**
     * �޺� ������ ������
     * @see java.awt.Component#size()
     * @deprecated 
     */
    public int length()
    {
        return comboItems.length;
    }

    ////////////////////////////// implementing from MultiComponentElement //////////////////////////////

    /**
     * ���� �����Ѵ�.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
     */
    public void setValue(Object value)
    {
        this.comboBox.setSelectedItem(value);
    }

    /**
     * ���� ��´�.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
     */
    public Object getValue()
    {
        return this.comboBox.getSelectedItem();
    }

    /**
     * ��Ƽ ������Ʈ�� ��´�.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
     */
    public Component getMultiComponentElement()
    {
        return this;
    }

    ////////////////////////////// implementing from ItemListener //////////////////////////////

    /**
     * ������ �̺�Ʈ ����
     * @param ie ������ �̺�Ʈ
     */
    public void itemStateChanged(ItemEvent ie)
    {
        this.selectedIndex = comboBox.getSelectedIndex();
    }
    
    ////////////////////////////// implementing from PanelElement //////////////////////////////

    /**
     * KElement Ŭ������ ���� �������̵� �� �޼���μ� �޺��ڽ��� ���� ������ ����
     * @param jc ������ ������ ������Ʈ
     */
    public void setting(Component jc)
    {
    }

    /**
     * KElement Ŭ������ ���� �������̵� �� �޼���μ� ������Ʈ�� ����� ���̴� ����
     * @param jc ����� ���� ������Ʈ
     */
    public void make(Component jc)
    {
        add(jc);
    }

    /**
     * ������Ʈ Ȱ��ȭ ����
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */
    public void doEnabled(boolean is)
    {
        comboBox.setEnabled(is);
        label.setEnabled(is);
    }
    
    /**
     * �޺� ������ Ŭ����
     */    
    public class ComboItem
    {
        /** �޺� ������ */
        Object item;
        
        /** �޺� �ڵ� */        
        Object code;
        
        /**
         * �޺� ������, �ڵ带 ���ڷ� ���� ������
         * @param item
         * @param code
         */
        public ComboItem(Object item, Object code)
        {
            this.item = item;
            this.code = code;
        }
        
        /**
         * �޺� �������� ��´�.
         * @return Object �޺� ������
         */        
        public Object getItem()
        {
            return this.item;
        }
        
        /**
         * �޺� �ڵ带 ��´�.
         * @return Object �޺� �ڵ�
         */
        public Object getCode()
        {
            return this.code;
        }       
        
        /**
         * �������� �����Ѵ�.
         * @param o
         */
        public void setItem(Object o)
        {
            this.item = o;
        }
        
        /**
         * �ڵ带 �����Ѵ�.
         * @param o
         */
        public void setCode(Object o)
        {
            this.code = o;
        }
    }
}
