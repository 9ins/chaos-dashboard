package org.chaostocosmos.chaosdashboard.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import org.chaostocosmos.chaosdashboard.client.ui.ColumnData;
import org.chaostocosmos.chaosdashboard.client.ui.DefaultPanel;
import org.chaostocosmos.chaosdashboard.client.ui.PagingTable;
import org.chaostocosmos.chaosdashboard.client.ui.PagingTableData;
import org.chaostocosmos.chaosdashboard.client.ui.WaitingDialog;
import org.chaostocosmos.chaosdashboard.common.Logger;
import org.chaostocosmos.chaosdashboard.common.PropertyHandler;
import org.chaostocosmos.chaosgraph.Graph;
import org.chaostocosmos.chaosgraph.GraphElement;
import org.chaostocosmos.chaosgraph.GraphElements;
import org.chaostocosmos.chaosgraph.GraphConstants.GRAPH;
import org.chaostocosmos.chaosgraph.awt2d.AreaGraph;
import org.chaostocosmos.chaosgraph.awt2d.BarGraph;
import org.chaostocosmos.chaosgraph.awt2d.CircleGraph;
import org.chaostocosmos.chaosgraph.awt2d.GraphPanel;
import org.chaostocosmos.chaosgraph.awt2d.LineGraph;


/**
 * ����� Ŭ���̾�Ʈ ȭ�� Ŭ����
 * @author 9ins
 *
 */
public class ChaosMgmtClientFrame extends JFrame implements ActionListener {
	private Hashtable<String, Graph> graph;
	private Color[] graphColors = {new Color(150, 150, 230), new Color(230, 150, 150), new Color(64, 128, 128), new Color(255, 145, 5), new Color(255, 229, 5), 
			new Color(5, 240, 255), new Color(150, 240, 150), new Color(150, 150, 230), new Color(128, 128, 0), new Color(192, 192, 192)};	
	
	private Hashtable<String, DefaultPanel> dPanel;
	private Hashtable<String, PagingTable> pTable;
	private Hashtable<String, JButton> btns;
    
    private JTextArea errTextArea;
    private DefaultPanel errPanel;
    private JScrollPane errJsp;
    private String btnStr = "START";
    private JButton startAllBtn, connectBtn, exitBtn;
    /**
     * ����� Ŭ���̾�Ʈ
     */
    private ChaosMgmtClient client;
    
    /**
     * ����͸� �̹��� ���
     */
    public String imagePath = PropertyHandler.getProperty("client.properties", "CLIENT_GRAPH_IMAGE_PATH");    
    
    /**
     * ����Ʈ ���� ��Ʈ��
     */
    public static final String mainLabelStr = PropertyHandler.getProperty("client.properties", "CLIENT_TITLE");
    
    /**
     * ȭ�� ����, ����
     */
    public static final int frmWidth = Toolkit.getDefaultToolkit().getScreenSize().width, frmHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    /**
     * ����� ���� ��ü �ؽ� ���̺�
     */
    Hashtable<String, ChaosMgmtConfig> configHash;    
	
	/**
	 * ���� ��� ���̾�α�
	 */
	WaitingDialog waitingDlg;
    
    /**
     * ������
     * @param titie ������ Ÿ��Ʋ
     * @param client ����� Ŭ���̾�Ʈ
     */
	public ChaosMgmtClientFrame(String titie, ChaosMgmtClient client) {
		super(titie);
		this.client = client;
		this.configHash = ChaosMgmtClient.getMonitorConfigBeans();		
		initGUI();
	}
	
	/**
	 * ȭ�� �ʱ�ȭ
	 */
	private void initGUI() {
		this.waitingDlg = new WaitingDialog(this, "������....", "����͸� ������Ʈ�� ������ �Դϴ�.");
		this.waitingDlg.addButtonActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				waitingDlg.stop();
			}			
		});
		
		this.graph = new Hashtable<String, Graph>();
		this.dPanel = new Hashtable<String, DefaultPanel>();
		this.pTable = new Hashtable<String, PagingTable>();
		this.btns = new Hashtable<String, JButton>();
		
	    for(ChaosMgmtConfig bean : this.configHash.values()) {
	    	System.out.println(bean.beanId);
	    	try {
	    		if(bean.graphConfig != null) {
	    			ChaosGraphConfig graphBean = bean.graphConfig;
    				String graphTitle = graphBean.graphTitle;
    				int graphWidth = graphBean.graphWidth;
    				int graphHeight = graphBean.graphHeight;
    				ChaosGraphConfig.GraphElementConfig elementConfigs[] = graphBean.elementConfigs;
    				
    				Object[] elements = new Object[elementConfigs.length];
    				Color[] colors = new Color[elementConfigs.length];
		            double[] values = new double[graphBean.xIndexNum];
		            List<Object> xIndex = new ArrayList<Object>(graphBean.xIndexNum);
		            List<Double> yIndex = Arrays.stream(graphBean.graphYIndexes).boxed().collect(Collectors.toList());
		            String graphUnit = graphBean.unit;

					GraphElements graphElements = new GraphElements(GRAPH.AREA, xIndex, yIndex);
    				
    				for(int i=0; i<elements.length; i++) {
						GraphElement ge = new GraphElement(elementConfigs[i].elementName, elementConfigs[i].color, elementConfigs[i].elementName+""+i, Color.GRAY, values);
						System.out.println(ge.toString());
						graphElements.addElement(ge);
    				}
		            Graph graph;
	    			if(graphBean.graphType == Graph.AREA_GRAPH) {
	    				graph = new AreaGraph(graphElements, 600, 400);
	    			} else if(graphBean.graphType == Graph.BAR_GRAPH) {
				        graph = new BarGraph(graphElements, 600, 400);
	    			} else if(graphBean.graphType == Graph.LINE_GRAPH) {
				        graph = new LineGraph(graphElements, 600, 400);
	    			} else if(graphBean.graphType == Graph.CIRCLE_GRAPH) {
				        graph = new CircleGraph(graphElements, 600, 400);
	    			} else {
	    				throw new RuntimeException("�־��� �׷��� Ÿ���� �������� �ʽ��ϴ�.");
	    			}
			        graph.setShowGridY(true);
			        graph.setShowGridX(false);
			        graph.setShowBg(false);
			        graph.setShowGraphXY(false);
			        graph.setLimit(yIndex.get(yIndex.size()-1));
			        graph.setIndent(10, 60, 20, 10);
			        graph.setUnit(graphUnit);
			        this.graph.put(bean.beanId, graph);
			        
					ChaosTableConfig tableConfig = bean.tableConfig;
					DefaultPanel dPanel = new DefaultPanel(tableConfig.title, BorderFactory.createEtchedBorder(), new BorderLayout());
					GraphPanel gPanel = new GraphPanel(graph, graph.getGraphWidth(), graph.getGraphHeight());
					gPanel.setPreferredSize(new Dimension(graphWidth+10, graphHeight+10));
					JScrollPane jsp = new JScrollPane(gPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					jsp.getHorizontalScrollBar().setUnitIncrement(16);
					dPanel.addElement(jsp, "Center");
					
					ChaosTableConfig.ChaosColumnConfig[] columnConfigs = tableConfig.columnConfigs;
					ColumnData[] cd = new ColumnData[columnConfigs.length];
					for(int j=0; j<cd.length; j++)
						cd[j] = new ColumnData(ColumnData.CELL_TEXTFIELD, columnConfigs[j].columnName, columnConfigs[j].width, columnConfigs[j].align, columnConfigs[j].color, columnConfigs[j].editable);
					
					PagingTableData pTableData = new PagingTableData(cd);
					PagingTable pTable = new PagingTable(pTableData, graphWidth, graphHeight);
					this.pTable.put(bean.beanId, pTable);
					JButton btn = new JButton("START");
					btn.setEnabled(false);
					btn.addActionListener(this);
					this.btns.put(bean.beanId, btn);
					pTable.addButton(btn);
					dPanel.addElement(pTable, "South");	
					this.dPanel.put(bean.beanId, dPanel);
	    		}
	    	} catch(Exception e) {
	    		Logger.getInstance().println(e, ChaosMgmtClientFrame.class);
	    	}
	    }
		
		try {
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			JPanel mainPanel = new JPanel(new BorderLayout());
			JSplitPane splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			int pointX = 10;
			int pointY = 10;

			JLabel mainLabel = new JLabel(this.mainLabelStr, JLabel.LEFT);
			mainLabel.setFont(new Font(mainLabel.getFont().getName(), Font.BOLD, 20));
			mainLabel.setForeground(Color.BLUE);
			//mainLabel.setBounds(pointX, pointY, mainLabel.getPreferredSize().width, mainLabel.getPreferredSize().height);
			centerPanel.add(mainLabel);
			pointY += mainLabel.getPreferredSize().height;

			for(DefaultPanel graphPanel : this.dPanel.values()) {
				centerPanel.add(graphPanel);
			}
			
			JPanel downPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			startAllBtn = new JButton("START ALL");
			startAllBtn.setEnabled(false);
			startAllBtn.addActionListener(this);
			downPanel.add(startAllBtn);
			connectBtn = new JButton("CONNECT");
			connectBtn.addActionListener(this);
			downPanel.add(connectBtn);
			exitBtn = new JButton("EXIT");
			exitBtn.addActionListener(this);
			downPanel.add(exitBtn);
			
			centerPanel.setPreferredSize(new Dimension(frmWidth, pointY));
			JScrollPane centerPanelJsp = new JScrollPane(centerPanel);
			centerPanelJsp.getVerticalScrollBar().setUnitIncrement(16);
			splitPanel.add(centerPanelJsp);
			
			this.errPanel = new DefaultPanel("Error log", BorderFactory.createEtchedBorder(), new BorderLayout());
			this.errTextArea = new JTextArea(10, 200);
			this.errTextArea.setEditable(false);
			this.errJsp = new JScrollPane(this.errTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			this.errPanel.addElement(this.errJsp, "Center");
			splitPanel.add(errPanel);
			splitPanel.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().height-250);
			mainPanel.add(mainLabel, "North");
			mainPanel.add(splitPanel, "Center");
			mainPanel.add(downPanel, "South");
			getContentPane().add(mainPanel);
			setSize(frmWidth, frmHeight-100);
			//setResizable(false);
		} catch (Exception e) {
			Logger.getInstance().println(e, ChaosMgmtClientFrame.class);
		}
	}

	/**
	 * ��ư �̺�Ʈ ������
	 */
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("START") || ae.getActionCommand().equals("STOP")) {
			Object[] keys = this.btns.keySet().toArray();
			for(int i=0; i<keys.length; i++) {
				JButton btn = this.btns.get(keys[i]);
				if(btn == ae.getSource())
					processButton(keys[i]+"", btn);					
			}
		} else if(ae.getActionCommand().equals("START ALL")) {
			Object[] keys = this.btns.keySet().toArray();
			for(int i=0; i<keys.length; i++) {
				JButton btn = this.btns.get(keys[i]);
				btn.setText("START");
				processButton(keys[i]+"", btn);
			}
			this.startAllBtn.setText("STOP ALL");
		} else if(ae.getActionCommand().equals("STOP ALL")) {
			Object[] keys = this.btns.keySet().toArray();
			for(int i=0; i<keys.length; i++) {
				JButton btn = this.btns.get(keys[i]);
				btn.setText("STOP");
				processButton(keys[i]+"", btn);
			}
			this.startAllBtn.setText("START ALL");
		} else if(ae.getActionCommand().equals("CONNECT")) {
			//this.connectBtn.setText("DISCONNECT");
			this.client.connect();
		} else if(ae.getActionCommand().equals("DISCONNECT")) {
			this.connectBtn.setText("CONNECT");
			this.client.disconnectAll();
		} else if(ae.getActionCommand().equals("EXIT")) {
			close();
		}
	}
	
	/**
	 * ��ư ó�� ���μ���
	 * @param idx ��ư �ε���
	 * @param btn ��ư
	 */
	public void processButton(String bName, JButton btn) {
		if(btn.getText().equals("START")) {
			ChaosMgmtConfig cBean = this.configHash.get(bName);
			//cBean.setMonitoring(true);
			btn.setText("STOP");
		} else {
			ChaosMgmtConfig cBean = this.configHash.get(bName);
			//cBean.setMonitoring(false);
			btn.setText("START");
		}						
	}
	
	/**
	 * ��� ��ư Ȱ��ȭ
	 */
	public void enableButtons() {
		for(JButton btn : this.btns.values())
			btn.setEnabled(true);
		this.startAllBtn.setEnabled(true);
		this.connectBtn.setEnabled(true);
	}
	
	/**
	 * ��� ��ư ��Ȱ��ȭ
	 */
	public void disableButtons() {
		for(JButton btn : this.btns.values())
			btn.setEnabled(false);	
		this.startAllBtn.setEnabled(false);
		this.connectBtn.setEnabled(false);
	}
	
	/**
	 * ȭ���� �����Ѵ�.
	 */
	public void close() {
		int ret = JOptionPane.showConfirmDialog(this, "Do you wanna exit monitor clinet?", "exit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(ret == JOptionPane.OK_OPTION) {
			super.dispose();
			this.waitingDlg.stop();
			this.client.disconnectAll();
			this.client.exit();
		}			
	}
}
