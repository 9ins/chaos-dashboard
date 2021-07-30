/*
 * Filename : ProgressDialog.java
 * Class : ProgressDialog
 */
package org.chaos.mgmt.client.ui;

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
 * ProgressDialog 클래스 
 * 시스템 시작시 초기화 진행바를 구현한 클래스 디폴트 최소값은 0, 최대값은 100, 진행간격은 0.5초로
 * 셋팅. 진행 상황에 따라 setProgress메서드를 호출함으로서 진행상테를 셋팅해야 한다.
 * 
 * @author Kooin-Shin
 * @version 1.0
 */
public class ProgressDialog extends JDialog implements Runnable
{
    /** 진행바 창의 부모 프레임 */
    Frame parent;
    
    /** 타이틀 이미지 */
    Image img;
    
    /** 창에 도시할 메시지 */    
    String msg;
    
    /** 메시지 라벨 */
    JLabel jl;
    
    /** 상태바 */
    JProgressBar jpb;
    
    /** 상태바 갱신 쓰레드 */
    Thread thr;
    
    /** 최소값 */
    int min = 0;
    
    /** 최대값 */
    int max = 100;
    
    /** 갱신간격 */
    int sleep = 500;
    
    /** 화면 넓이 */
    int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    
    /** 화면 높이 */
    int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    /** 현재까지 총 진행량 */
    int total;
    
    /** 기본 갱신 량 */
    int amount;
    
    /** 갱신 수를 채크 */
    int count;
    
    /**
     * 메시지, 갱신량을 인자로 갖는 생성자
     * @param msg 메시지
     * @param amount 갱신량
     */
    public ProgressDialog(String msg, int amount)
    {
        super();
        this.msg = msg;
        this.amount = amount%100;
        initComp();
    }

    /**
     * 부모프레임, 메시지, 갱신량을 인자로 갖는 생성자
     * @param parent 부모 프레임
     * @param msg 메시지
     * @param amount 갱신량
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
     * 부모프레임, 메시지, 갱신량, 이미지를 인자로 갖는 생성자
     * @param parent 부모 프레임
     * @param msg 메시지
     * @param amount 갱신량
     * @param img 타이틀 이미지
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
     * 컨포넌트 초기화
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
     * 상태바 시작
     */
    public void begin()
    {
        setVisible(true);
        thr = new Thread(this);
        thr.start();
    }
    
    /**
     * 상태바를 갱신 시킬 쓰레드
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
     * 메시지와 상태값을 바꾼다.
     * @param msg 메시지
     * @param percent 상태값
     * @param waiting 기다릴 밀리 초 값
     */
    public void setProgress(String msg, int percent, int waiting)
    {
        setValue(percent);
        setMessage(msg);
        setWaiting(waiting);
    }
    
    /**
     * 메시지를 바꾼다.
     * @param msg 메시지
     */
    public void setMessage(String msg)
    {
        jl.setText(msg);
    }
    
    /**
     * 상태값을 바꾼다.
     * @param percent 상태값
     */
    public void setValue(int percent)
    {
        if(percent<0 || percent>100)
            return;
        total = percent;
        jpb.setValue(percent);
    }
    
    /**
     * 지정한 밀리초만큼 기다린다.
     * @param miliseconds 밀리초
     */
    public void setWaiting(int miliseconds)
    {
        try{ Thread.sleep(miliseconds); }
        catch(Exception e){}
    }
}    
