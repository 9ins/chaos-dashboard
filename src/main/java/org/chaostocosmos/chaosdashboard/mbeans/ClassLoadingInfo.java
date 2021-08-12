package org.chaostocosmos.chaosdashboard.mbeans;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

public class ClassLoadingInfo implements ClassLoadingInfoMBean {

	@Override
	public long getTotalLoadedClassCount() {
		ClassLoadingMXBean mxbean = ManagementFactory.getClassLoadingMXBean();		
		return mxbean.getTotalLoadedClassCount();
	}

	@Override
	public long getLoadedClassCount() {
		ClassLoadingMXBean mxbean = ManagementFactory.getClassLoadingMXBean();		
		return mxbean.getLoadedClassCount();
	}

	@Override
	public long getUnloadedClassCount() {
		ClassLoadingMXBean mxbean = ManagementFactory.getClassLoadingMXBean();		
		return mxbean.getUnloadedClassCount();
	}

	@Override
	public long getTimeStemp() {
		return System.currentTimeMillis();
	}

}
