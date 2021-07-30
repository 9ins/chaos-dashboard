package org.chaos.mgmt.client;

import java.awt.Color;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class ChaosGraphConfig implements Serializable {
	/**
	 * 그래프 타입
	 */
	public int graphType;
	
	/**
	 * 그래프 타이틀 스트링
	 */
	public String graphTitle;
	
	/**
	 * 그래프 넓이
	 */
	public int graphWidth;
	
	/**
	 * 그래프 높이
	 */
	public int graphHeight;
	
	/**
	 * 그래프 x축 인덱스 갯수
	 */
	public int xIndexNum;
	
	/**
	 * 그래프 Y 인덱스 목록
	 */
	public float[] graphYIndexes;
	
	/**
	 * 라벨 전시여부
	 */
	public boolean isLabel;
	
	/**
	 * y축 한계값
	 */
	public float limit;
	
	/**
	 * y축 단위 스트링
	 */
	public String unit;
	
	/**
	 * 백분율 전시여부
	 */
	public boolean isPersent;
	
	/**
	 * 그래프 요소 설정 목록
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
	 *  2014. 4. 9.	9INS	최초작성
	 *  
	 * @author 9INS
	 * @since 2014. 4. 9.
	 * @version 1.0
	 * @see Copyright ChaosToCosmos
	 *
	 */
	public static class GraphElementConfig {
		/**
		 * 요소명
		 */
		public String elementName;
		
		/**
		 * 메핑 메소드 명
		 */
		public String mapping;
		
		/**
		 * 요소 색
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
