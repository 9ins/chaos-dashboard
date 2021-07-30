/*
 * Filename : LabelFileChooserField.java
 * Class : LabelFileChooserField
 */
package org.chaos.mgmt.client.ui;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import org.chaos.mgmt.common.UtilBox;


/**
 * 파일 선택 필드
 * @author Kooin
 */
public class LabelFileChooserField extends LabelTextField
{
    /** 파일 선택 다이얼로그 */
    JFileChooser fileChooser;    
    
    /** 파일 선택 버튼 */
    JButton fileBtn;
    
    /** 파일 선택 모드 */
    int selectionMode;
    
    /** 오너 창 */
    Window owner;
    
    /** 버튼 도시 여부 */
    boolean isSwBtn;
    
    /** 파일 경로 */
    String filePath;
    
    /**
     * 라벨 문자열을 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#PanelElement(String)
     */
    public LabelFileChooserField(String label)
    {
        this(label, System.getProperty("user.dir"));
    }
    
    /**
     * 라벨 문자열, 초기 경로를 인자로 갖는 생성자
     * @param label 라벨 이름
     * @param filePath 파일 경로
     */
    public LabelFileChooserField(String label, String filePath)
    {
        this(label, filePath, filePath.length());
    }
    
    /**
     * 라벨 문자열, 초기 경로, 컬럼 크기를 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.LabelTextField#LabelTextField(String, int)
     */
    public LabelFileChooserField(String label, String filePath, int column)
    {
        this(label, filePath, column, true);
    }
    
    /**
     * 라벨 문자열, 초기 경로, 컬럼 크기, 편집 가능여부를 인자로 갖는 생성자
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.LabelTextField#LabelTextField(String, int, boolean)
     */
    public LabelFileChooserField(String label, String filePath, int column, boolean editable)
    {
        this(label, filePath, column, editable, "...");
    }
    
    /**
     * 라벨 문자열, 초기 경로, 컬럼 크기, 편집 가능여부, 버튼 문자열을 인자로 갖는 생성자
     * @param label 라벨 이름
     * @param column 컬럼 크기
     * @param editable 편집여부
     * @param btnStr 버튼 문자열
     */
    public LabelFileChooserField(String label, String filePath, int column, boolean editable, String btnStr)
    {
        this(label, filePath, column, editable, btnStr, JFileChooser.FILES_AND_DIRECTORIES);
    }
    
    /**
     * 라벨 문자열, 초기 경로, 컬럼 크기, 편집 가능여부, 버튼 문자열, 파일선택모드를 인자로 갖는 생성자
     * @param label 라벨 이름
     * @param filePath 파일 경로
     * @param column 컬럼 크기
     * @param editable 편집여부
     * @param btnStr 버튼 문자열
     * @param selectionMode 선태 모드
     */
    public LabelFileChooserField(String label, String fPath, int column, boolean editable, String btnStr, final int selectionMode)
    {
        super(label, column, 255, editable, "");
        if(fPath == null || fPath.equals(""))
            fPath = System.getProperty("user.dir");
        this.filePath = fPath;
        super.setText(fPath);
        this.selectionMode = selectionMode;
        
        if(!btnStr.equals(""))
        {
            fileBtn = new JButton(btnStr);
            setting(fileBtn);
            fileBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    fileChooser = new JFileChooser(UtilBox.getAbsolutePath(filePath));
                    fileChooser.setFileSelectionMode(selectionMode);
                    fileChooser.showOpenDialog(owner);
                    File f = fileChooser.getSelectedFile();
                    if(f != null)
                        setText(f.getAbsolutePath());
                }
            });
        }
        else
        {
            super.getTextField().addFocusListener(new FocusAdapter()
            {
                public void focusGained(FocusEvent fe)
                {
                    fileChooser = new JFileChooser(UtilBox.getAbsolutePath(filePath));
                    fileChooser.setFileSelectionMode(selectionMode);                    
                    fileChooser.showOpenDialog(owner);
                    File f = fileChooser.getSelectedFile();
                    if(f != null)
                        setText(f.getAbsolutePath());
                }
                
                public void focusLost(FocusEvent fe)
                {
                    fileChooser.cancelSelection();
                }
            });
        }
    }    
    
    /**
     * 오너창 설정
     * @param owner 오너 윈도우
     */
    public void setOwner(Window owner)
    {
        this.owner = owner;        
    }
    
    /**
     * 파일경로를 설정한다.
     * @param path 경로
     */
    public void setText(String path)
    {
        super.setText(path);
        if(fileChooser != null)
            fileChooser.setCurrentDirectory(new File(path));
    }
    
    /**
     * 버튼 아이콘을 설정한다.
     * @param icon 버튼 아이콘
     */
    public void setButtonIcon(Icon icon)
    {
        this.fileBtn.setIcon(icon);
    }
    
    /**
     * 버튼 문자열을 설정한다.
     * @param btnText 버튼 문자열
     */
    public void setButtonText(String btnText)
    {
        this.fileBtn.setText(btnText);
    }
    
    ////////////////////////////// implements from PaneElement //////////////////////////////
    
	/**
     * 컴포넌트 설정
	 * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#setting(java.awt.Component)
	 */
	public void setting(Component c)
	{
        add(c);
	}
    
	/**
     * 컴포넌트 생성
	 * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#make(java.awt.Component)
	 */
	public void make(Component c)
	{
	}
}
