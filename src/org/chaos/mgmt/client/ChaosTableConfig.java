/**
 * 
 */
package org.chaos.mgmt.client;

import java.awt.Color;
import java.util.Arrays;

/**  
 * ChaosTableConfig : 
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
public class ChaosTableConfig {
	/**
	 * 테이블 타이틀
	 */
	public String title;
	
	/**
	 * 테이블 넓이
	 */
	public int width;
	
	/**
	 * 테이블 높이
	 */
	public int height;
	
	/**
	 * 테이블 컬럼 설정 객체 목록
	 */
	public ChaosColumnConfig[] columnConfigs;
	
	/**
	 * 
	 * ChaosTableColumnConfig : 
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
	public static class ChaosColumnConfig {
		/**
		 * 컬럼명
		 */
		public String columnName;
		
		/**
		 * 매핑 메소드 명
		 */
		public String mapping;
		
		/**
		 * 컬럼 넓이
		 */
		public int width;
		
		/**
		 * 컬럼 정렬
		 */
		public int align;
		
		/**
		 * 편집 가능여부
		 */
		public boolean editable;
		
		/**
		 * 컬럼 색
		 */
		public Color color;

		@Override
		public String toString() {
			return "ChaosColumnConfig [columnName=" + columnName + ", mapping="
					+ mapping + ", width=" + width + ", align=" + align
					+ ", editable=" + editable + ", color=" + color + "]";
		}
	}

	@Override
	public String toString() {
		return "ChaosTableConfig [title=" + title + ", width=" + width
				+ ", height=" + height + ", columnConfigs="
				+ Arrays.toString(columnConfigs) + "]";
	}
}
