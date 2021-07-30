package org.chaos.mgmt.agent;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.chaos.mgmt.common.PropertyHandler;
import org.chaos.mgmt.mbeans.InfoMBean;

/**
 * 모니터 클라이언트 매니저 클래스
 * @author 9ins
 *
 */
public class MBeanFactory {
	/**
	 * 모니터 클라이언트 
	 */
	private static MBeanFactory cmMonitor = null;
	
	/**
	 * MBean 해쉬테이블
	 */
	private Hashtable<String, InfoMBean> mbeanMap;
	
	/**
	 * 프로퍼티 경로
	 */
	private String propertyPath = System.getProperty("user.dir");
	
	/**
	 * 생성자
	 */
	private MBeanFactory() {
		loadMBeans(propertyPath);
	}
	
	/**
	 * 모니터 클라이언트 인스턴스를 얻는다.
	 * @return 모니터 클라이언트
	 */
	public static MBeanFactory getInstance() {
		if(cmMonitor == null) {
			cmMonitor = new MBeanFactory();
		}
		return cmMonitor;
	}
	
	/**
	 * MBean을 로드한다.
	 * @param propertyPath 프로퍼티 경로(반드시 디렉토리)
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
	 * MBean 인스턴스를 얻는다.
	 * @param className 클래스 명
	 * @return 인스턴스
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
	 * MBean을 얻는다.
	 * @param mbeanName 빈이름
	 * @return 빈
	 */
	public Object getMBean(String mbeanName) {
		return this.mbeanMap.get(mbeanName);
	}
	
	/**
	 * 로드한 MBeans를 빈즈서버에 설정한다.
	 * @param server 빈즈서버
	 */
	public void registerMBeansToMonitorServer(ChaosAgent server) {
		Collection collection = this.mbeanMap.values();
		for(Object value : collection) {
			server.registerMBean(value);
		}
	}
	
	/**
	 * MXBean을 Agent에 설정한다.
	 * @param server 에이전트
	 */
	public void registerMXBeansToMgmtServer(ChaosAgent server) {
	}
	
	/**
	 * 모든 쓰레드의 토탈 CPU 타임을 얻는다.
	 * @return 토탈 CPU 타임(밀리세컨즈)
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
	 * 모든 쓰레드의 토탈 사용자 타임을 얻는다.
	 * @return 토탈 사용자 타임(밀리세컨즈)
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
	 * 모든 쓰레드의 토탈 CPU 사용량을 얻는다.
	 * @return 토탈 CPU 사용량(밀리세컨즈)
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
	 * 메모리풀 빈 객체를 얻는다.
	 * @param poolName 메모리풀 이름
	 * @return 메모리풀 빈
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
	 * 프로퍼티 경로를 얻는다.
	 * @return 프로퍼티 경로
	 */
	public String getPropertyPath() {
		return this.propertyPath;
	}
	
	/**
	 * 프로퍼티 경로를 설정한다.
	 * @param propertyPath 프로퍼티 경로
	 */
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}
}
