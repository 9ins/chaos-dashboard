package org.chaostocosmos.chaosdashboard.client;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.chaostocosmos.chaosdashboard.client.ui.ColumnData;
import org.chaostocosmos.chaosgraph.GraphConstants;
import org.chaostocosmos.chaosgraph.NotMatchGraphTypeException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLChaosMgmtConfigFactory {
	/**
	 * ���������� ��´�.
	 * @param xmlDir
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Hashtable<String, ChaosMgmtConfig> getChaosMgmtConfig(String xmlDir) throws ParserConfigurationException, IOException, SAXException {
		File f = new File(xmlDir);
		if(!f.isDirectory() || !f.exists()) {
			throw new IllegalArgumentException("XML path must exist directory!!!");
		}
		File[] list = f.listFiles();
		Hashtable<String, ChaosMgmtConfig> configs = new Hashtable<String, ChaosMgmtConfig>(list.length);
		for(int i=0; i<list.length; i++) {
			Document doc = parseToDocuments(list[i].getAbsolutePath());
			configs.put(list[i].getName(), convertToMgmtConfigs(doc));
		}
		return configs;
	}
	
	/**
	 * XML������ Document��ü�� ��´�.
	 * @param xmlFile
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static Document parseToDocuments(String xmlFile) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();		
		Document doc = docBuilder.parse(xmlFile);
		doc.normalize();
		return doc;
	}
	
	/**
	 * Document�� ���� ��ü�� ��´�.
	 * @param docs
	 * @return
	 */
	public static ChaosMgmtConfig convertToMgmtConfigs(Document docs) {		
		ChaosMgmtConfig conf = new ChaosMgmtConfig();
		Element root = docs.getDocumentElement();
		NodeList nodeList1 = root.getChildNodes();
		
		conf.beanId = root.getAttribute("id");
		conf.beanType = root.getAttribute("type");
		conf.className = root.getAttribute("class_name");
		conf.objectName = root.getAttribute("object_name");
		
		for(int i=0; i<nodeList1.getLength(); i++) {
			Node node1 = nodeList1.item(i);
			if(node1.getNodeName().equals("monitoring")) {
				Element ele = (Element)node1;
				String str = ele.getAttribute("is_monitoring");
				if(!str.equals(""))
					conf.isMonitoring = Boolean.parseBoolean(str);
				str = ele.getNodeValue();
				if(str != null)
					conf.monitoringInterval = Long.parseLong(str);
			} else if(node1.getNodeName().equals("agent")) {
				ChaosAgentConfig agentConf = new ChaosAgentConfig();
				Element ele = (Element)node1;
				String str = ele.getAttribute("service_url");
				if(!str.equals(""))
					agentConf.serviceUrl = str;
				str = ele.getAttribute("retry_interval");
				if(!str.equals(""))
					agentConf.retryInterval = Long.parseLong(str);
				else
					agentConf.retryInterval = 1000;
				conf.agentConfig = agentConf;
			} else if(node1.getNodeName().equals("graph")) {
				ChaosGraphConfig graphConf = new ChaosGraphConfig();
				Element ele2 = (Element)node1;
				String str = ele2.getAttribute("type").toLowerCase();
				if(str.equals("area")) {
					graphConf.graphType = GraphConstants.AREA_GRAPH;
				} else if(str.equals("bar")) {
					graphConf.graphType = GraphConstants.BAR_GRAPH;
				} else if(str.equals("line")) {
					graphConf.graphType = GraphConstants.LINE_GRAPH;
				} else if(str.equals("circle")) {
					graphConf.graphType = GraphConstants.CIRCLE_GRAPH;
				} else {
					throw new NotMatchGraphTypeException("Graph type is't supported.");
				}
				graphConf.graphTitle = ele2.getAttribute("title");
				graphConf.graphWidth = Integer.parseInt(ele2.getAttribute("graph_width"));
				graphConf.graphHeight = Integer.parseInt(ele2.getAttribute("graph_height"));
				graphConf.xIndexNum = Integer.parseInt(ele2.getAttribute("x_num"));
				str = ele2.getAttribute("limit");
				if(!str.equals(""))
					graphConf.limit = Float.parseFloat(str);
				graphConf.unit = ele2.getAttribute("unit");
				graphConf.isLabel = Boolean.parseBoolean(ele2.getAttribute("is_label"));
				graphConf.isPersent = Boolean.parseBoolean(ele2.getAttribute("is_Per"));
				str = ele2.getAttribute("y_index");
				StringTokenizer st = new StringTokenizer(str, ",");
				double[] vals = new double[st.countTokens()];
				for(int j=0; j<vals.length; j++)
					vals[j] = Double.parseDouble(st.nextToken());
				graphConf.graphYIndexes = vals;
				
				NodeList nodeList2 = ele2.getChildNodes();
				ArrayList<ChaosGraphConfig.GraphElementConfig> list = new ArrayList<ChaosGraphConfig.GraphElementConfig>(); 
				for(int j=0; j<nodeList2.getLength(); j++) {
					Node node2 = nodeList2.item(j);
					if(node2.getNodeName().equals("element")) {
						ChaosGraphConfig.GraphElementConfig elementConfig = new ChaosGraphConfig.GraphElementConfig();
						Element ele3 = (Element)node2;
						elementConfig.elementName = ele3.getAttribute("name");
						elementConfig.mapping = ele3.getAttribute("mapping");
						str = ele3.getAttribute("color");
						st = new StringTokenizer(str, ",");
						int[] color = new int[st.countTokens()];
						for(int k=0; k<color.length; k++)
							color[k] = Integer.parseInt(st.nextToken());
						elementConfig.color = new Color(color[0], color[1], color[2]);
						list.add(elementConfig);		
					}
				}
				ChaosGraphConfig.GraphElementConfig[] elementConfigs = new ChaosGraphConfig.GraphElementConfig[list.size()];
				for(int j=0; j<elementConfigs.length; j++)
					elementConfigs[j] = list.get(j);
				graphConf.elementConfigs = elementConfigs;
				conf.graphConfig = graphConf;
			} else if(node1.getNodeName().equals("table")) {
				Element ele = (Element)node1;
				ChaosTableConfig tableConf = new ChaosTableConfig();
				tableConf.title = ele.getAttribute("title");
				tableConf.width = Integer.parseInt(ele.getAttribute("width"));
				tableConf.height = Integer.parseInt(ele.getAttribute("height"));
				NodeList nodeList2 = ele.getChildNodes();
				ArrayList<ChaosTableConfig.ChaosColumnConfig> list = new ArrayList<ChaosTableConfig.ChaosColumnConfig>();
				for(int j=0; j<nodeList2.getLength(); j++) {
					Node node2 = nodeList2.item(j);
					if(node2.getNodeName().equals("column")) {
						ChaosTableConfig.ChaosColumnConfig columnConfig = new ChaosTableConfig.ChaosColumnConfig();
						Element ele3 = (Element)node2;
						columnConfig.columnName = ele3.getAttribute("name");
						columnConfig.mapping = ele3.getAttribute("mapping");
						columnConfig.width = Integer.parseInt(ele3.getAttribute("width"));
						String str = ele3.getAttribute("align").toLowerCase();
						if(str.equals("center")) {
							columnConfig.align = ColumnData.CELL_ALIGH_CENTER;
						} else if(str.equals("left")) {
							columnConfig.align = ColumnData.CELL_ALIGN_LEFT;
						} else if(str.equals("right")){
							columnConfig.align = ColumnData.CELL_ALIGN_RIGHT;
						} else {
							throw new RuntimeException("Input align is't valid.");
						}
						columnConfig.editable = Boolean.parseBoolean(ele3.getAttribute("editable"));
						str = ele3.getAttribute("color");
						StringTokenizer st = new StringTokenizer(str, ",");
						int[] color = new int[st.countTokens()];
						for(int k=0; k<color.length; k++)
							color[k] = Integer.parseInt(st.nextToken());
						columnConfig.color = new Color(color[0], color[1], color[2]);
						list.add(columnConfig);
					}
				}
				ChaosTableConfig.ChaosColumnConfig[] columnConfigs = new ChaosTableConfig.ChaosColumnConfig[list.size()];
				for(int j=0; j<columnConfigs.length; j++)
					columnConfigs[j] = list.get(j);
				tableConf.columnConfigs = columnConfigs;
				conf.tableConfig = tableConf;
			}
		}
		return conf;
	}
}
