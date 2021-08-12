package org.chaostocosmos.chaosdashboard.client;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class ChaosGraphConfig implements Serializable {
	/**
	 * �׷��� Ÿ��
	 */
	public int graphType;
	
	/**
	 * �׷��� Ÿ��Ʋ ��Ʈ��
	 */
	public String graphTitle;
	
	/**
	 * �׷��� ����
	 */
	public int graphWidth;
	
	/**
	 * �׷��� ����
	 */
	public int graphHeight;
	
	/**
	 * �׷��� x�� �ε��� ����
	 */
	public int xIndexNum;
	
	/**
	 * �׷��� Y �ε��� ���
	 */
	public double[] graphYIndexes;
	
	/**
	 * �� ���ÿ���
	 */
	public boolean isLabel;
	
	/**
	 * y�� �Ѱ谪
	 */
	public double limit;
	
	/**
	 * y�� ���� ��Ʈ��
	 */
	public String unit;
	
	/**
	 * ����� ���ÿ���
	 */
	public boolean isPersent;
	
	/**
	 * �׷��� ��� ���� ���
	 */
	public GraphElementConfig[] elementConfigs;
	
	/**
	 * 
	 * GraphElementConfig : 
	 * Description : 
	 * 
	 *  
	 * Modification Information  
	 *  ---------   ---------   -------------------------------
	 *  2014. 4. 9.	9INS	�����ۼ�
	 *  
	 * @author 9INS
	 * @since 2014. 4. 9.
	 * @version 1.0
	 * @see Copyright ChaosToCosmos
	 *
	 */
	public static class GraphElementConfig {
		/**
		 * ��Ҹ�
		 */
		public String elementName;
		
		/**
		 * ���� �޼ҵ� ��
		 */
		public String mapping;
		
		/**
		 * ��� ��
		 */
		public Color color;

		@Override
		public String toString() {
			return "GraphElementConfig [elementName=" + elementName
					+ ", mapping=" + mapping + ", color=" + color + "]";
		}
	}

	@Override
	public String toString() {
		return "ChaosGraphConfig [graphType=" + graphType + ", graphTitle="
				+ graphTitle + ", graphWidth=" + graphWidth + ", graphHeight="
				+ graphHeight + ", xIndexNum=" + xIndexNum + ", graphYIndexes="
				+ Arrays.toString(graphYIndexes) + ", isLabel=" + isLabel
				+ ", limit=" + limit + ", unit=" + unit + ", isPersent="
				+ isPersent + ", elementConfigs="
				+ Arrays.toString(elementConfigs) + "]";
	}	
}
