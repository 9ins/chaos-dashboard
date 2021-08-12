/*
 * Filename : WaitingDialog.java
 * Class : WaitingDialog
 */
package org.chaostocosmos.chaosdashboard.client.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.Frame;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.chaostocosmos.chaosdashboard.common.UtilBox;


/**
 * ��� ���̾�α�
 * 
 * @author Kooin Shin
 * @version 1.0.0
 */
public class WaitingDialog extends JDialog implements Runnable, ComponentListener
{
    /** �Ϸ� ���� */
    boolean isDone;
    
    /** ���� */
    WaitingBar waitingBar;
    
    /** ��� ������ */
    Icon icon;
    
    /** ���� ���� */
    Rectangle2D.Double waitingBarRect;
    
    /** Ŀ�� ����Ʈ */
    int point;
    
    /** ��� �ð� */
    long waitingMillis = 100;

    /** Ŀ�� ����ġ */
    int amount = 20;
    
    /** �ִ�ġ */
    int max;
    
    /** �ּ�ġ */
    int min;
    
    /** ������ ���� */
    boolean reverse;
    
    /** �޽��� �� */
    JLabel msgLabel;
    
    /** ������ �� */
    JLabel iconLabel;
    
    /** ��� ��ư */
    JButton cancelBtn;
    
    /**
     * ����Ʈ ������
     * @see java.lang.Object#Object()
     */
    public WaitingDialog()
    {
        this(null);
    }
    
    /**
     * ���� �������� ���ڷ� ���� ������
     * @see java.awt.Frame#Frame(Frame)
     */    
    public WaitingDialog(Frame frm)
    {
        this(frm, "�����");
    }
    
    /**
     * ���� ������, Ÿ��Ʋ�� ���ڷ� ���� ������
     * @see java.awt.Dialog#Dialog(Frame, String)
     */    
    public WaitingDialog(Frame frm, String title)
    {
        this(frm, title, "����� �Դϴ�.");
    }
    
    /**
     * ���� ������, Ÿ��Ʋ, �޽����� ���ڷ� ���� ������
     * @param frm ���� ������
     * @param title Ÿ��Ʋ
     * @param msg �޽���
     */    
    public WaitingDialog(Frame frm, String title, String msg)
    {
        this(frm, title, msg, new Dimension(300, 80));
    }
    
    /**
     * ���� ������, Ÿ��Ʋ, �޽���, ���̾�α� ����� ���ڷ� ���� ������
     * @param frm ���� ������
     * @param title Ÿ��Ʋ
     * @param msg �޽���
     * @param size ���̾�α� ������
     */
    public WaitingDialog(Frame frm, String title, String msg, Dimension size)
    {
        this(frm, title, msg, size, 0);
    }
    
    /**
     * ���� ������, Ÿ��Ʋ, �޽���, �� ������ ���ڷ� ���� ������
     * @param frm ���� ������
     * @param title Ÿ��Ʋ
     * @param msg �޽���
     * @param size ���̾�α� ������
     * @param barType ���̾�α� �� Ÿ��
     */
    public WaitingDialog(Frame frm, String title, String msg, Dimension size, int barType)
    {
        this(frm, title, msg, size, barType, null);        
    }
    
    /**
     * ���� ������, Ÿ��Ʋ, �޽���, ����ġ, ��� �ð�, �� ����, â ������ �������θ� ���ڷ� ���� ������
     * @param frm ���� ������
     * @param title Ÿ��Ʋ
     * @param msg �޽���
     * @param size ���̾�α� ������
     * @param barType ���̾�α� �� Ÿ��
     * @param resizable ũ�� �������� ����
     */
    public WaitingDialog(Frame frm, String title, String msg, Dimension size, int barType, Icon icon)
    {
        super(frm, title);
        JPanel pane = (JPanel)getContentPane();
        pane.setPreferredSize(size);
        pane.setLayout(new BorderLayout());
        this.max = size.width;
        this.min = 0;
        this.point = 0;
        this.icon = icon;
        this.waitingBarRect = new Rectangle2D.Double(0, 0, 0, 0);
        this.waitingBar = new WaitingBar(this.waitingBarRect, barType);
        this.waitingBar.setBarColor(new Color(100, 100, 200));        
        JPanel jp = new JPanel(new FlowLayout());        
        if(!msg.equals(""))
        {            
            msgLabel = new JLabel(msg);
            msgLabel.setHorizontalAlignment(JLabel.CENTER);
            jp.add(msgLabel);
        }
        if(icon != null)
        {        
            iconLabel = new JLabel(icon);
            iconLabel.setHorizontalAlignment(JLabel.CENTER);
            jp.add(iconLabel);
        }        
        pane.add(jp, "North");
        pane.add(waitingBar, "Center");
        
        cancelBtn = new JButton(" ��� ");
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(cancelBtn);
        pane.add(btnPanel, "South");
                     
        setContentPane(pane);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addComponentListener(this);
        pack();
    }
    
    /**
     * ��ҹ�ư�� ��´�.
     * @return JButton ��ҹ�ư
     */
    public JButton getCancelButton()
    {
        return this.cancelBtn;
    }
    
    /**
     * ��ҹ�ư �׼� �����ʸ� �߰��Ѵ�.
     * @param listener �׼� ������
     */
    public void addButtonActionListener(ActionListener listener)
    {
        cancelBtn.addActionListener(listener);
    }
    
    /**
     * ������ �̺�Ʈ ����
     * @see java.awt.Window#processWindowEvent(WindowEvent)
     */
    public void processWindowEvent(WindowEvent we)
    {
    }
    
    /**
     * �޽����� �����Ѵ�.
     * @param msg �޽���
     */
    public void setMessage(String msg)
    {
        msgLabel.setText(msg);       
    }
    
    /**
     * �������� �����Ѵ�.
     * @param icon ������
     */
    public void setIcon(Icon icon)
    {
        iconLabel.setIcon(icon);
    }

    /**
     * ��� ���̾�α׸� ���δ�.
     * @see java.awt.Component#show()
     */
    public void begin()
    {
   		UtilBox.setCenterScreen(this);
        setVisible(true);
        this.isDone = false;
        Thread thr = new Thread(this);
        thr.start();
    }
    
    /**
     * ��� ���̾�α׸� ���� �Ѵ�.
     */
    public void stop()
    {
        this.isDone = true;
    }
    
    /**
     * ��� â�� �����Ѵ�.
     * @param waitingMillis ��� �ð�
     */
    public void stop(long waitingMillis)
    {
        this.waitingMillis = waitingMillis;
        this.isDone = true;
    }
    
    ////////////////////////////// implements from Runnable //////////////////////////////
    
    /**
     * ��� ������ ����
     * @see java.lang.Runnable#run()
     */
    public void run()
    {
        while(!isDone)
        {
            this.waitingBar.setPoint(this.point);
            try
            {
                Thread.sleep(this.waitingMillis);                                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }                    
            if(this.point > max)
            {
                this.reverse = true;
                this.point = max;
            }
            else if(this.point < min)
            {
                this.reverse = false;
                this.point = min;                                
            }
            if(!this.reverse)
            {
                this.point += amount;
            }
            else
            {
                this.point -= amount;
            }
            repaint();
        }
        try
        {
            Thread.sleep(this.waitingMillis);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        dispose();
    }

    /**
     * ������Ʈ �������� ��
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
     */
    public void componentResized(ComponentEvent arg0)
    {
        double width = getSize().width-getPreferredSize().width/10;
        double height = getSize().height/12;
        this.waitingBar.setBarRectangle(new Rectangle2D.Double(0, 0, width, height));
        this.max = getSize().width;
        repaint();
    }

    /**
     * ������Ʈ �̵���
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    public void componentMoved(ComponentEvent arg0)
    {
    }

    /**
     * ������Ʈ ������ ��
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void componentShown(ComponentEvent arg0)
    {
    }

    /**
     * ������Ʈ ��������
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    public void componentHidden(ComponentEvent arg0)
    {
    }

    /**
     * ���� 
     * @author Kooin
     * @version 1.0
     * @since JDK1.3.1
     */
    public class WaitingBar extends JPanel
    {
        /** �� ���� */
        int barType;
        
        /** �� ���� */
        Rectangle2D.Double rect;

        /** Ŀ�� ����Ʈ */
        float point;

        /** ���� ���� */
        Color bgColor;

        /** ���� �� */
        Color barColor;

        /** ��� ������ */
        float bgAlpha;

        /** �� ������ */
        float barAlpha;
        
        /**
         * �� x, �� y, �� ����, �� ���̸� ���ڷ� ���� ������
         * @param x ���� x
         * @param y ���� y
         * @param width ����
         * @param height ����
         */
        public WaitingBar(double x, double y, double width, double height, int barType)
        {
            this(new Rectangle2D.Double(x, y, width, height), barType);            
        }
        
        /**
         * �� ������ ���ڷ� ���� ������
         * @param rect �� ����
         * @param barType �� Ÿ��
         */
        public WaitingBar(Rectangle2D.Double rect, int barType)
        {
            this.barType = barType;
            this.point  = 0;
            this.bgColor = Color.white;
            this.bgAlpha = 0.5f;
            this.barColor = Color.gray;
            this.barAlpha = 1.0f;
            setBarRectangle(rect);
        }
        
        /**
         * ������ ���� ���̵�
         * @see java.awt.Component#paint(Graphics)
         */
        public void paint(Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;
            if(this.barType == 0)
            {
                drawBarType0(g2d);
            }
            else if(this.barType == 1)
            {
                drawBarType1(g2d);
            }
        }
        
        /**
         * �� ���� 0�� ���
         * @param g2d �׷��Ƚ�2D ��ü
         */
        public void drawBarType0(Graphics2D g2d)
        {
            BasicStroke s = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, bgAlpha));
            g2d.setStroke(s);
            g2d.draw(rect);
            
            GradientPaint gp;
            if(!reverse)
            {
                gp = new GradientPaint((float)rect.x, (float)rect.y, bgColor, (float)(point+rect.x), (float)rect.y, barColor, false);
            }
            else
            {
                gp = new GradientPaint((float)(point+rect.x), (float)rect.y, bgColor, (float)rect.x, (float)rect.y, barColor, false);
            }
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, barAlpha));
            g2d.setColor(barColor);
            g2d.setPaint(gp);
            g2d.fill(rect);
        }
        
        /**
         * �� ���� 1�ϰ��
         * @param g2d �׷��Ƚ�2D ��ü
         */
        public void drawBarType1(Graphics2D g2d)
        {
            BasicStroke s = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, barAlpha));
            g2d.setStroke(s);
            g2d.draw(rect);
            
            GradientPaint gp;
            if(!reverse)
            {
                gp = new GradientPaint((float)rect.x, (float)rect.y, bgColor, (float)(point+rect.x), (float)rect.y, barColor);
            }
            else
            {
                gp = new GradientPaint((float)rect.x, (float)rect.y, barColor, (float)(point+rect.x), (float)rect.y, bgColor);
            }
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, barAlpha));
            g2d.setColor(barColor);
            g2d.setPaint(gp);
            g2d.fill(rect);
            
            int num = 30;
            g2d.setStroke(s);
            for(int i=0; i<num; i++)
            {
                g2d.draw(new Rectangle2D.Double(rect.x, rect.y, rect.width/num, rect.height));
                rect.x += rect.width/num;
            }
        }
        
        /**
         * �� Ÿ���� �����Ѵ�.
         * @param barType
         */        
        public void setBarType(int barType)
        {
            this.barType = barType;
        }
        
        /**
         * ������ �����Ѵ�.
         * @param bgColor
         */
        public void setBgColor(Color bgColor)
        {
            this.bgColor = bgColor;
        }
        
        /**
         * �� ���� �����Ѵ�.
         * @param barColor
         */
        public void setBarColor(Color barColor)
        {
            this.barColor = barColor;
        }
        
        /**
         * ��� �������� �����Ѵ�.
         * @param bgAlpha
         */
        public void setBgAlpha(float bgAlpha)
        {
            this.bgAlpha = bgAlpha;
        }
        
        /**
         * �� �������� �����Ѵ�.
         * @param barAlpha
         */
        public void setBarAlpha(float barAlpha)
        {
            this.barAlpha = barAlpha;
        }

        /**
         * ���� Ŀ�� ����Ʈ�� �����Ѵ�.
         * @param point
         */
        public void setPoint(float point)
        {
            this.point = point;
        }

        /**
         * �� ������ �����Ѵ�.
         * @param rect
         */
        public void setBarRectangle(Rectangle2D.Double rect)
        {
            this.rect = rect;
            this.rect.x = getSize().width/2-rect.width/2;
            this.rect.y = rect.height;
            setPreferredSize(new Dimension((int)this.rect.width, (int)this.rect.height));
        }
    }
}
