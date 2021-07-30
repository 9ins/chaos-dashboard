/*
 * Filename : WaitingDialog.java
 * Class : WaitingDialog
 */
package org.chaos.mgmt.client.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.chaos.mgmt.client.ChaosMgmtClient;
import org.chaos.mgmt.common.UtilBox;


/**
 * 대기 다이얼로그
 * 
 * @author Kooin Shin
 * @version 1.0.0
 */
public class WaitingDialog extends JDialog implements Runnable, ComponentListener
{
    /** 완료 여부 */
    boolean isDone;
    
    /** 대기바 */
    WaitingBar waitingBar;
    
    /** 대기 아이콘 */
    Icon icon;
    
    /** 대기바 영역 */
    Rectangle2D.Double waitingBarRect;
    
    /** 커서 포인트 */
    int point;
    
    /** 대기 시간 */
    long waitingMillis = 100;

    /** 커서 증가치 */
    int amount = 20;
    
    /** 최대치 */
    int max;
    
    /** 최소치 */
    int min;
    
    /** 리버스 여부 */
    boolean reverse;
    
    /** 메시지 라벨 */
    JLabel msgLabel;
    
    /** 아이콘 라벨 */
    JLabel iconLabel;
    
    /** 취소 버튼 */
    JButton cancelBtn;
    
    /**
     * 디폴트 생성자
     * @see java.lang.Object#Object()
     */
    public WaitingDialog()
    {
        this(null);
    }
    
    /**
     * 오너 프레임을 인자로 갖는 생성자
     * @see java.awt.Frame#Frame(Frame)
     */    
    public WaitingDialog(Frame frm)
    {
        this(frm, "대기중");
    }
    
    /**
     * 오너 프레임, 타이틀을 인자로 갖는 생성자
     * @see java.awt.Dialog#Dialog(Frame, String)
     */    
    public WaitingDialog(Frame frm, String title)
    {
        this(frm, title, "대기중 입니다.");
    }
    
    /**
     * 오너 프레임, 타이틀, 메시지를 인자로 갖는 생성자
     * @param frm 오너 프레임
     * @param title 타이틀
     * @param msg 메시지
     */    
    public WaitingDialog(Frame frm, String title, String msg)
    {
        this(frm, title, msg, new Dimension(300, 80));
    }
    
    /**
     * 오너 프레임, 타이틀, 메시지, 다이얼로그 사이즈를 인자로 갖는 생성자
     * @param frm 오너 프레임
     * @param title 타이틀
     * @param msg 메시지
     * @param size 다이얼로그 사이즈
     */
    public WaitingDialog(Frame frm, String title, String msg, Dimension size)
    {
        this(frm, title, msg, size, 0);
    }
    
    /**
     * 오너 프레임, 타이틀, 메시지, 바 종류를 인자로 갖는 생성자
     * @param frm 오너 프레임
     * @param title 타이틀
     * @param msg 메시지
     * @param size 다이얼로그 사이즈
     * @param barType 다이얼로그 바 타입
     */
    public WaitingDialog(Frame frm, String title, String msg, Dimension size, int barType)
    {
        this(frm, title, msg, size, barType, null);        
    }
    
    /**
     * 오너 프레임, 타이틀, 메시지, 증가치, 대기 시간, 바 종류, 창 사이즈 조정여부를 인자로 갖는 생성자
     * @param frm 오너 프레임
     * @param title 타이틀
     * @param msg 메시지
     * @param size 다이얼로그 사이즈
     * @param barType 다이얼로그 바 타입
     * @param resizable 크기 조정가능 여부
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
        
        cancelBtn = new JButton(" 취소 ");
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
     * 취소버튼을 얻는다.
     * @return JButton 취소버튼
     */
    public JButton getCancelButton()
    {
        return this.cancelBtn;
    }
    
    /**
     * 취소버튼 액션 리스너를 추가한다.
     * @param listener 액션 리스너
     */
    public void addButtonActionListener(ActionListener listener)
    {
        cancelBtn.addActionListener(listener);
    }
    
    /**
     * 윈도우 이벤트 수신
     * @see java.awt.Window#processWindowEvent(WindowEvent)
     */
    public void processWindowEvent(WindowEvent we)
    {
    }
    
    /**
     * 메시지를 설정한다.
     * @param msg 메시지
     */
    public void setMessage(String msg)
    {
        msgLabel.setText(msg);       
    }
    
    /**
     * 아이콘을 설정한다.
     * @param icon 아이콘
     */
    public void setIcon(Icon icon)
    {
        iconLabel.setIcon(icon);
    }

    /**
     * 대기 다이얼로그를 보인다.
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
     * 대기 다이얼로그를 종료 한다.
     */
    public void stop()
    {
        this.isDone = true;
    }
    
    /**
     * 대기 창을 중지한다.
     * @param waitingMillis 대기 시간
     */
    public void stop(long waitingMillis)
    {
        this.waitingMillis = waitingMillis;
        this.isDone = true;
    }
    
    ////////////////////////////// implements from Runnable //////////////////////////////
    
    /**
     * 대기 쓰레드 시작
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
     * 컴포넌트 리사이즈 시
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
     * 컴포넌트 이동시
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    public void componentMoved(ComponentEvent arg0)
    {
    }

    /**
     * 컴포넌트 보여질 시
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    public void componentShown(ComponentEvent arg0)
    {
    }

    /**
     * 컴포넌트 숨겨질시
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    public void componentHidden(ComponentEvent arg0)
    {
    }

    /**
     * 대기바 
     * @author Kooin
     * @version 1.0
     * @since JDK1.3.1
     */
    public class WaitingBar extends JPanel
    {
        /** 바 종류 */
        int barType;
        
        /** 바 영역 */
        Rectangle2D.Double rect;

        /** 커서 포인트 */
        float point;

        /** 대기바 배경색 */
        Color bgColor;

        /** 대기바 색 */
        Color barColor;

        /** 배경 투명도 */
        float bgAlpha;

        /** 바 투명도 */
        float barAlpha;
        
        /**
         * 바 x, 바 y, 바 넓이, 바 높이를 인자로 갖는 생성자
         * @param x 원점 x
         * @param y 원점 y
         * @param width 넓이
         * @param height 높이
         */
        public WaitingBar(double x, double y, double width, double height, int barType)
        {
            this(new Rectangle2D.Double(x, y, width, height), barType);            
        }
        
        /**
         * 바 영역을 인자로 갖는 생성자
         * @param rect 바 영역
         * @param barType 바 타입
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
         * 패인팅 오버 라이딩
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
         * 바 종류 0일 경우
         * @param g2d 그래픽스2D 객체
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
         * 바 종류 1일경우
         * @param g2d 그래픽스2D 객체
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
         * 바 타입을 설정한다.
         * @param barType
         */        
        public void setBarType(int barType)
        {
            this.barType = barType;
        }
        
        /**
         * 배경색을 설정한다.
         * @param bgColor
         */
        public void setBgColor(Color bgColor)
        {
            this.bgColor = bgColor;
        }
        
        /**
         * 바 색을 설정한다.
         * @param barColor
         */
        public void setBarColor(Color barColor)
        {
            this.barColor = barColor;
        }
        
        /**
         * 배경 투명도를 설정한다.
         * @param bgAlpha
         */
        public void setBgAlpha(float bgAlpha)
        {
            this.bgAlpha = bgAlpha;
        }
        
        /**
         * 바 투명도를 설정한다.
         * @param barAlpha
         */
        public void setBarAlpha(float barAlpha)
        {
            this.barAlpha = barAlpha;
        }

        /**
         * 현재 커서 포인트를 설정한다.
         * @param point
         */
        public void setPoint(float point)
        {
            this.point = point;
        }

        /**
         * 바 영역을 설정한다.
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
