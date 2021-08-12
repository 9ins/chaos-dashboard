/*
 * Filename : LabelFileChooserField.java
 * Class : LabelFileChooserField
 */
package org.chaostocosmos.chaosdashboard.client.ui;

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

import org.chaostocosmos.chaosdashboard.common.UtilBox;


/**
 * ���� ���� �ʵ�
 * @author Kooin
 */
public class LabelFileChooserField extends LabelTextField
{
    /** ���� ���� ���̾�α� */
    JFileChooser fileChooser;    
    
    /** ���� ���� ��ư */
    JButton fileBtn;
    
    /** ���� ���� ��� */
    int selectionMode;
    
    /** ���� â */
    Window owner;
    
    /** ��ư ���� ���� */
    boolean isSwBtn;
    
    /** ���� ��� */
    String filePath;
    
    /**
     * �� ���ڿ��� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#PanelElement(String)
     */
    public LabelFileChooserField(String label)
    {
        this(label, System.getProperty("user.dir"));
    }
    
    /**
     * �� ���ڿ�, �ʱ� ��θ� ���ڷ� ���� ������
     * @param label �� �̸�
     * @param filePath ���� ���
     */
    public LabelFileChooserField(String label, String filePath)
    {
        this(label, filePath, filePath.length());
    }
    
    /**
     * �� ���ڿ�, �ʱ� ���, �÷� ũ�⸦ ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.LabelTextField#LabelTextField(String, int)
     */
    public LabelFileChooserField(String label, String filePath, int column)
    {
        this(label, filePath, column, true);
    }
    
    /**
     * �� ���ڿ�, �ʱ� ���, �÷� ũ��, ���� ���ɿ��θ� ���ڷ� ���� ������
     * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.LabelTextField#LabelTextField(String, int, boolean)
     */
    public LabelFileChooserField(String label, String filePath, int column, boolean editable)
    {
        this(label, filePath, column, editable, "...");
    }
    
    /**
     * �� ���ڿ�, �ʱ� ���, �÷� ũ��, ���� ���ɿ���, ��ư ���ڿ��� ���ڷ� ���� ������
     * @param label �� �̸�
     * @param column �÷� ũ��
     * @param editable ��������
     * @param btnStr ��ư ���ڿ�
     */
    public LabelFileChooserField(String label, String filePath, int column, boolean editable, String btnStr)
    {
        this(label, filePath, column, editable, btnStr, JFileChooser.FILES_AND_DIRECTORIES);
    }
    
    /**
     * �� ���ڿ�, �ʱ� ���, �÷� ũ��, ���� ���ɿ���, ��ư ���ڿ�, ���ϼ��ø�带 ���ڷ� ���� ������
     * @param label �� �̸�
     * @param filePath ���� ���
     * @param column �÷� ũ��
     * @param editable ��������
     * @param btnStr ��ư ���ڿ�
     * @param selectionMode ���� ���
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
     * ����â ����
     * @param owner ���� ������
     */
    public void setOwner(Window owner)
    {
        this.owner = owner;        
    }
    
    /**
     * ���ϰ�θ� �����Ѵ�.
     * @param path ���
     */
    public void setText(String path)
    {
        super.setText(path);
        if(fileChooser != null)
            fileChooser.setCurrentDirectory(new File(path));
    }
    
    /**
     * ��ư �������� �����Ѵ�.
     * @param icon ��ư ������
     */
    public void setButtonIcon(Icon icon)
    {
        this.fileBtn.setIcon(icon);
    }
    
    /**
     * ��ư ���ڿ��� �����Ѵ�.
     * @param btnText ��ư ���ڿ�
     */
    public void setButtonText(String btnText)
    {
        this.fileBtn.setText(btnText);
    }
    
    ////////////////////////////// implements from PaneElement //////////////////////////////
    
	/**
     * ������Ʈ ����
	 * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#setting(java.awt.Component)
	 */
	public void setting(Component c)
	{
        add(c);
	}
    
	/**
     * ������Ʈ ����
	 * @see org.chaos.mgmt.client.ui.sicc.kctc.exerctrlsw.obsvctrl.notebook.nbcommon.PanelElement#make(java.awt.Component)
	 */
	public void make(Component c)
	{
	}
}
