/*
 * Filename : ProgressDialog.java
 * Class : ProgressDialog
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.Image;
import java.awt.Frame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

/**
 * ProgressDialog Ŭ���� 
 * �ý��� ���۽� �ʱ�ȭ ����ٸ� ������ Ŭ���� ����Ʈ �ּҰ��� 0, �ִ밪�� 100, ���ణ���� 0.5�ʷ�
 * ����. ���� ��Ȳ�� ���� setProgress�޼��带 ȣ�������μ� ������׸� �����ؾ� �Ѵ�.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class ProgressDialog extends JDialog implements Runnable
{
    /** ����� â�� �θ� ������ */
    Frame parent;
    
    /** Ÿ��Ʋ �̹��� */
    Image img;
    
    /** â�� ������ �޽��� */    
    String msg;
    
    /** �޽��� �� */
    JLabel jl;
    
    /** ���¹� */
    JProgressBar jpb;
    
    /** ���¹� ���� ������ */
    Thread thr;
    
    /** �ּҰ� */
    int min = 0;
    
    /** �ִ밪 */
    int max = 100;
    
    /** ���Ű��� */
    int sleep = 500;
    
    /** ȭ�� ���� */
    int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    
    /** ȭ�� ���� */
    int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    /** ������� �� ���෮ */
    int total;
    
    /** �⺻ ���� �� */
    int amount;
    
    /** ���� ���� äũ */
    int count;
    
    /**
     * �޽���, ���ŷ��� ���ڷ� ���� ������
     * @param msg �޽���
     * @param amount ���ŷ�
     */
    public ProgressDialog(String msg, int amount)
    {
        super();
        this.msg = msg;
        this.amount = amount%100;
        initComp();
    }

    /**
     * �θ�������, �޽���, ���ŷ��� ���ڷ� ���� ������
     * @param parent �θ� ������
     * @param msg �޽���
     * @param amount ���ŷ�
     */
    public ProgressDialog(Frame parent, String msg, int amount)
    {
        super(parent, false);
        this.parent = parent;
        this.msg = msg;
        this.amount = amount%100;
        initComp();
    }

    /**
     * �θ�������, �޽���, ���ŷ�, �̹����� ���ڷ� ���� ������
     * @param parent �θ� ������
     * @param msg �޽���
     * @param amount ���ŷ�
     * @param img Ÿ��Ʋ �̹���
     */
    public ProgressDialog(Frame parent, String msg, int amount, Image img)
    {
        super(parent, false);
        this.parent = parent;
        this.msg = msg;
        this.amount = amount%100;
        this.img = img;
        initComp();
    }

    /**
     * ������Ʈ �ʱ�ȭ
     */
    private void initComp()
    {
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        if(img != null)
        {
            JPanel jp = new JPanel();
            JLabel jl = new JLabel(new ImageIcon(this.img), JLabel.CENTER);
            jp.add(jl);
            contentPane.add(jp);
        }

        jl = new JLabel(msg);
        jl.setHorizontalAlignment(JLabel.CENTER);
        JPanel jp1 = new JPanel(new FlowLayout());
        jp1.add(jl);
        contentPane.add(jp1);

        jpb = new JProgressBar(JProgressBar.HORIZONTAL, min, max);
        jpb.setStringPainted(true);
        jpb.setPreferredSize(new Dimension(250, 25));
        JPanel jp2 = new JPanel(new FlowLayout());
        jp2.add(jpb);
        contentPane.add(jp2);

        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        pack();
        int x = screenWidth/2-getWidth()/2;
        int y = screenHeight/2-getHeight()/2;
        setLocation(x, y);
    }
    
    /**
     * ���¹� ����
     */
    public void begin()
    {
        setVisible(true);
        thr = new Thread(this);
        thr.start();
    }
    
    /**
     * ���¹ٸ� ���� ��ų ������
     */
    public void run()
    {
        while(total<max)
        {
            try{ Thread.sleep(sleep); }
            catch(Exception e){}
            int val = total+count*amount;
            jpb.setValue(val);
            jpb.setString(total+"%");
            count++;
        }
        setWaiting(1000);
        dispose();
    }
    
    /**
     * �޽����� ���°��� �ٲ۴�.
     * @param msg �޽���
     * @param percent ���°�
     * @param waiting ��ٸ� �и� �� ��
     */
    public void setProgress(String msg, int percent, int waiting)
    {
        setValue(percent);
        setMessage(msg);
        setWaiting(waiting);
    }
    
    /**
     * �޽����� �ٲ۴�.
     * @param msg �޽���
     */
    public void setMessage(String msg)
    {
        jl.setText(msg);
    }
    
    /**
     * ���°��� �ٲ۴�.
     * @param percent ���°�
     */
    public void setValue(int percent)
    {
        if(percent<0 || percent>100)
            return;
        total = percent;
        jpb.setValue(percent);
    }
    
    /**
     * ������ �и��ʸ�ŭ ��ٸ���.
     * @param miliseconds �и���
     */
    public void setWaiting(int miliseconds)
    {
        try{ Thread.sleep(miliseconds); }
        catch(Exception e){}
    }
}    
