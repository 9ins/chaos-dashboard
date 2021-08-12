/**
 * 
 */
package org.chaostocosmos.chaosdashboard.client;

import java.awt.Color;
import java.util.Arrays;

/**  
 * ChaosTableConfig : 
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
public class ChaosTableConfig {
	/**
	 * ���̺� Ÿ��Ʋ
	 */
	public String title;
	
	/**
	 * ���̺� ����
	 */
	public int width;
	
	/**
	 * ���̺� ����
	 */
	public int height;
	
	/**
	 * ���̺� �÷� ���� ��ü ���
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
	 *  2014. 4. 9.	9INS	�����ۼ�
	 *  
	 * @author 9INS
	 * @since 2014. 4. 9.
	 * @version 1.0
	 * @see Copyright ChaosToCosmos
	 *
	 */
	public static class ChaosColumnConfig {
		/**
		 * �÷���
		 */
		public String columnName;
		
		/**
		 * ���� �޼ҵ� ��
		 */
		public String mapping;
		
		/**
		 * �÷� ����
		 */
		public int width;
		
		/**
		 * �÷� ����
		 */
		public int align;
		
		/**
		 * ���� ���ɿ���
		 */
		public boolean editable;
		
		/**
		 * �÷� ��
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
