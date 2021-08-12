package org.chaostocosmos.chaosdashboard.agent;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.chaostocosmos.chaosdashboard.common.PropertyHandler;
import org.chaostocosmos.chaosdashboard.mbeans.InfoMBean;

/**
 * ����� Ŭ���̾�Ʈ �Ŵ��� Ŭ����
 * @author 9ins
 *
 */
public class MBeanFactory {
	/**
	 * ����� Ŭ���̾�Ʈ 
	 */
	private static MBeanFactory cmMonitor = null;
	
	/**
	 * MBean �ؽ����̺�
	 */
	private Hashtable<String, InfoMBean> mbeanMap;
	
	/**
	 * ������Ƽ ���
	 */
	private String propertyPath = System.getProperty("user.dir");
	
	/**
	 * ������
	 */
	private MBeanFactory() {
		loadMBeans(propertyPath);
	}
	
	/**
	 * ����� Ŭ���̾�Ʈ �ν��Ͻ��� ��´�.
	 * @return ����� Ŭ���̾�Ʈ
	 */
	public static MBeanFactory getInstance() {
		if(cmMonitor == null) {
			cmMonitor = new MBeanFactory();
		}
		return cmMonitor;
	}
	
	/**
	 * MBean�� �ε��Ѵ�.
	 * @param propertyPath ������Ƽ ���(�ݵ�� ���丮)
	 */
	public void loadMBeans(String propertyPath) {
		File f = new File(this.propertyPath);
		if(!f.isDirectory() || !f.exists()) {
			throw new IllegalArgumentException("Property path must exist directory.");
		}
		this.mbeanMap = new Hashtable<String, InfoMBean>();
		PropertyHandler ph = new PropertyHandler(this.propertyPath);
		Properties props = ph.getProperties("mbeans.properties");
		Object[] keys = props.keySet().toArray();
		for(Object key : keys) {
			Object mbean = loadMBean(key+"");
			this.mbeanMap.put(key+"", (InfoMBean)mbean);
		}
	}
	
	/**
	 * MBean �ν��Ͻ��� ��´�.
	 * @param className Ŭ���� ��
	 * @return �ν��Ͻ�
	 */
	public Object loadMBean(String className) {
		try {
			Class cls = Class.forName(className);
			Object instance = cls.newInstance();
			return instance;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * MBean�� ��´�.
	 * @param mbeanName ���̸�
	 * @return ��
	 */
	public Object getMBean(String mbeanName) {
		return this.mbeanMap.get(mbeanName);
	}
	
	/**
	 * �ε��� MBeans�� ������� �����Ѵ�.
	 * @param server �����
	 */
	public void registerMBeansToMonitorServer(ChaosAgent server) {
		Collection collection = this.mbeanMap.values();
		for(Object value : collection) {
			server.registerMBean(value);
		}
	}
	
	/**
	 * MXBean�� Agent�� �����Ѵ�.
	 * @param server ������Ʈ
	 */
	public void registerMXBeansToMgmtServer(ChaosAgent server) {
	}
	
	/**
	 * ��� �������� ��Ż CPU Ÿ���� ��´�.
	 * @return ��Ż CPU Ÿ��(�и�������)
	 */
	public long getTotalCpuTime() {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
	    long[] ids = bean.getAllThreadIds();
	    if (! bean.isThreadCpuTimeSupported())
	        return 0L;
	    long time = 0L;
	    for (long i : ids) {	    	
	        long t = bean.getThreadCpuTime(i);
	        if (t != -1)
	            time += t;
	    }
	    return time/1000;
	}
	 
	/**
	 * ��� �������� ��Ż ����� Ÿ���� ��´�.
	 * @return ��Ż ����� Ÿ��(�и�������)
	 */
	public long getTotalUserTime() {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
	    long[] ids = bean.getAllThreadIds();
	    if (! bean.isThreadCpuTimeSupported())
	        return 0L;
	    long time = 0L;
	    for (long i : ids ) {
	        long t = bean.getThreadUserTime(i);
	        if ( t != -1 )
	            time += t;
	    }
	    return time/1000;
	}
	  
	/**
	 * ��� �������� ��Ż CPU ��뷮�� ��´�.
	 * @return ��Ż CPU ��뷮(�и�������)
	 */
	public long getTotalSystemTime() {
	    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
	    long[] ids = bean.getAllThreadIds();
	    if (! bean.isThreadCpuTimeSupported())
	        return 0L;
	    long time = 0L;
	    for (long i : ids) {
	        long tc = bean.getThreadCpuTime(i);
	        long tu = bean.getThreadUserTime(i);
	        if ( tc != -1 && tu != -1 )
	            time += (tc-tu);
	    }
	    return time/1000;
	}	
	
	/**
	 * �޸�Ǯ �� ��ü�� ��´�.
	 * @param poolName �޸�Ǯ �̸�
	 * @return �޸�Ǯ ��
	 */
	public MemoryPoolMXBean getMemoryPoolMXBean(String poolName) {
		List<MemoryPoolMXBean> list = ManagementFactory.getMemoryPoolMXBeans();
		for(MemoryPoolMXBean mxbean : list) {
			if(mxbean.getName().equals(poolName))
				return mxbean;
		}		
		throw new IllegalArgumentException("Memory pool name is incorrect!!!");
	}	
	
	/**
	 * ������Ƽ ��θ� ��´�.
	 * @return ������Ƽ ���
	 */
	public String getPropertyPath() {
		return this.propertyPath;
	}
	
	/**
	 * ������Ƽ ��θ� �����Ѵ�.
	 * @param propertyPath ������Ƽ ���
	 */
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}
}
