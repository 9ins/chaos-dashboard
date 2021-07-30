/*
 * Filename : LabelComboBox.java
 * Class : LabelComboBox
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;


/**
 * LabelComboBox 클래스
 * 라벨과 콤보박스를 통합하는 UI 컨포넌트
 * 
 * @author Kooin Shin
 * @version 1.0
 */
public class LabelComboBox extends PanelElement implements ItemListener
{
    /** 콤보박스 객체 */
    JComboBox comboBox;

    /** 콤보 아이템 목록 */
    ComboItem[] comboItems;
    
    /** 선택 인덱스 */
    int selectedIndex;
    
    /** 콤보박스 편집 여부 */
    boolean isEditable;

    /**
     * 라벨이름, 콤보 아이템 배열을 인자로 갖는 생성자
     * @param labelName 라벨이름
     * @param items 아이템 목록
     */
    public LabelComboBox(String labelName, Object[] items)
    {
        this(labelName, items, 50);
    }

    /**
     * 라벨이름, 콤보 아이템, 콤보 사이즈를 인자로 갖는 생성자
     * @param labelName 라벨이름
     * @param items 아이템 목록
     * @param size 콤보 사이즈
     */
    public LabelComboBox(String labelName, Object[] items, int width)
    {
        this(labelName, items, width, 22);
    }

    /**
     * 라벨이름, 콤보 아이템, 콤보 사이즈를 인자로 갖는 생성자
     * @param labelName 라벨이름
     * @param items 아이템 목록
     * @param width 콤보 사이즈
     * @param height 콤보 높이
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
     * 라벨이름, 아이템목록, 코드 목록을 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param items 아이템 목록
     * @param codes 코드 목록
     */
    public LabelComboBox(String labelName, Object[] items, Object[] codes)
    {
        this(labelName, items, codes, 50);
    }
    
    /**
     * 라벨이름, 아이템 목록, 코드 목록, 넓이를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param items 아이템 목록
     * @param codes 코드 목록
     * @param width 넓이
     */
    public LabelComboBox(String labelName, Object[] items, Object[] codes, int width)
    {
        this(labelName, items, codes, width, 22);
    }
    
    /**
     * 라벨이름, 아이템 목록, 코드목록, 넓이, 높이를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param items 아이템 목록
     * @param codes 코드 목록
     * @param width 넓이
     * @param height 높이
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
     * 라벨이름, 콤보 아이템을 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param comboItems 콤보 아이템 목록
     */
    public LabelComboBox(String labelName, ComboItem[] comboItems)
    {
        this(labelName, comboItems, 50);
    }
    
    /**
     * 라벨이름, 콤보 아이템, 넓이를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param comboItems 콤보 아이템 목록
     * @param width 넓이
     */
    public LabelComboBox(String labelName, ComboItem[] comboItems, int width)
    {
        this(labelName, comboItems, width, 22);    
    }
    
    /**
     * 라벨이름, 콤보 아이템, 넓이, 높이를 인자로 갖는 생성자
     * @param labelName 라벨 이름
     * @param comboItems 콤보 아이템 목록
     * @param width 넓이
     * @param height 높이
     */
    public LabelComboBox(String labelName, ComboItem[] comboItems, int width, int height)
    {
        super(labelName);
        init(comboItems, width, height);
    }
    
    /**
     * 초기화
     * @param comboItems 콤보 아이템 목록
     * @param width 넓이
     * @param height 높이
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
     * 아이템 목록을 얻는다.
     * @return Object[] 아이템 목록
     */
    public Object[] getItems()
    {
        Object[] o = new Object[comboItems.length];
        for(int i=0; i<o.length; i++)
            o[i] = this.comboItems[i].getItem();
        return o;
    }
    
    /**
     * 코드 목록을 얻는다.
     * @return Object[] 콤드 목록
     */
    public Object[] getCodes()
    {
        Object[] o = new Object[comboItems.length];
        for(int i=0; i<o.length; i++)
            o[i] = this.comboItems[i].getCode();
        return o;
    }

    /**
     * 콤보박스에 ActionListener를 셋팅한다.
     * @param al 액션 리스너 객체
     */
    public void addActionListener(ActionListener al)
    {
        this.comboBox.addActionListener(al);
    }
    
    /**
     * 콤보박스에 ItemListener를 설정한다.
     * @param il 아이템 리스너
     */
    public void addItemListener(ItemListener il)
    {
        this.comboBox.addItemListener(il);
    }
    
    /**
     * 선택 아이템을 얻는다.
     * @return 선택객체
     */
    public Object getSelectedItem()
    {
        return this.comboItems[this.selectedIndex].getItem();
    }
    
    /**
     * 선택 코드를 얻는다.
     * @return Object 선택 코드
     */
    public Object getSelectedCode()
    {
        return this.comboItems[this.selectedIndex].getCode();
    }
    
    /**
     * 코드값을 설정한다.
     * @param code
     */
    public void setSelectedCode(Object code)
    {
        this.comboItems[this.selectedIndex].setCode(code);
        this.comboBox.setSelectedItem(code);
    }
    
    /**
     * 아이템 값을 설정한다.
     * @param item
     */
    public void setSelectedItem(Object item)
    {
        this.comboItems[this.selectedIndex].setItem(item);
        this.comboBox.setSelectedItem(item);
    }

    /**
     * 콤보박스에서 선택된 인덱스를 리턴한다.
     * @return 선택된 인덱스
     */
    public int getSelectedIndex()
    {
        return this.selectedIndex;
    }

    /**
     * 콤보 박스 아이템 배열을 구한다.
     * @return Object[] 콤보박스 아이템 목록
     */
    public ComboItem[] getComboItems()
    {
        return comboItems;
    }

    /**
     * 콤보 박스를 얻는다.
     * @return JComboBox 콤보박스
     */
    public JComboBox getComboBox()
    {
        return comboBox;
    }
    
    /**
     * 인자로 주어진 아이템 목록으로 콤보박스 아이템을 설정한다.
     * @param items 콤보 아이템 목록
     */
    public void setItems(Object[] items)
    {
        this.comboItems = new ComboItem[items.length];
        for(int i=0; i<items.length; i++)
            this.comboItems[i] = new ComboItem(items[i], items[i]);
        setComboItems(this.comboItems);
    }
    
    /**
     * 인자로 주어진 아이템 모록 코드 목록으로 콤보박스 아이템을 설정한다.
     * @param items 콤보 아이템 목록
     * @param codes 콤보 코드 목록
     */
    public void setItemsNCodes(Object[] items, Object[] codes)
    {
        this.comboItems = new ComboItem[items.length];
        for(int i=0; i<items.length; i++)
            this.comboItems[i] = new ComboItem(items[i], codes[i]);
        setComboItems(this.comboItems);
    }
    
    /**
     * 인자로 주어진 아이템으로 콤보박스 모델을 교체한다.
     * @param item 콤보 아이템
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
     * 콤보박스 편집 가능 여부
     * @param editable 편집 가능여부
     */
    public void setEditable(boolean editable)
    {
        this.comboBox.setEditable(editable);
    }
    
    /**
     * 콤보 아이템 사이즈
     * @see java.awt.Component#size()
     * @deprecated 
     */
    public int length()
    {
        return comboItems.length;
    }

    ////////////////////////////// implementing from MultiComponentElement //////////////////////////////

    /**
     * 값을 설정한다.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#setValue(java.lang.Object)
     */
    public void setValue(Object value)
    {
        this.comboBox.setSelectedItem(value);
    }

    /**
     * 값을 얻는다.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getValue()
     */
    public Object getValue()
    {
        return this.comboBox.getSelectedItem();
    }

    /**
     * 멀티 컴포넌트를 얻는다.
     * @see com.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.MultiComponentElement#getMultiComponentElement()
     */
    public Component getMultiComponentElement()
    {
        return this;
    }

    ////////////////////////////// implementing from ItemListener //////////////////////////////

    /**
     * 아이템 이벤트 수신
     * @param ie 아이템 이벤트
     */
    public void itemStateChanged(ItemEvent ie)
    {
        this.selectedIndex = comboBox.getSelectedIndex();
    }
    
    ////////////////////////////// implementing from PanelElement //////////////////////////////

    /**
     * KElement 클래스로 부터 오버라이딩 된 메서드로서 콤보박스에 대한 설정을 정의
     * @param jc 설정을 정의할 컨포넌트
     */
    public void setting(Component jc)
    {
    }

    /**
     * KElement 클래스로 부터 오버라이딩 된 메서드로서 컨포넌트를 만들고 붙이는 역할
     * @param jc 만들고 붙일 컨포넌트
     */
    public void make(Component jc)
    {
        add(jc);
    }

    /**
     * 컴포넌트 활성화 여부
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.exersimulationctrl.awes.common.PanelElement#doEnabled(boolean)
     */
    public void doEnabled(boolean is)
    {
        comboBox.setEnabled(is);
        label.setEnabled(is);
    }
    
    /**
     * 콤보 아이템 클래스
     */    
    public class ComboItem
    {
        /** 콤보 아이템 */
        Object item;
        
        /** 콤보 코드 */        
        Object code;
        
        /**
         * 콤보 아이템, 코드를 인자로 갖는 생성자
         * @param item
         * @param code
         */
        public ComboItem(Object item, Object code)
        {
            this.item = item;
            this.code = code;
        }
        
        /**
         * 콤보 아이템을 얻는다.
         * @return Object 콤보 아이템
         */        
        public Object getItem()
        {
            return this.item;
        }
        
        /**
         * 콤보 코드를 얻는다.
         * @return Object 콤보 코드
         */
        public Object getCode()
        {
            return this.code;
        }       
        
        /**
         * 아이템을 설정한다.
         * @param o
         */
        public void setItem(Object o)
        {
            this.item = o;
        }
        
        /**
         * 코드를 설정한다.
         * @param o
         */
        public void setCode(Object o)
        {
            this.code = o;
        }
    }
}
