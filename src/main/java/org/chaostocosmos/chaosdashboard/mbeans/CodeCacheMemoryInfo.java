package org.chaostocosmos.chaosdashboard.mbeans;

import org.chaostocosmos.chaosdashboard.agent.MBeanFactory;

public class CodeCacheMemoryInfo implements CodeCacheMemoryInfoMBean {

	@Override
	public long getTimeStemp() {		
		return System.currentTimeMillis();
	}

	@Override
	public String getName() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Code Cache").getName();
	}

	@Override
	public String getType() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Code Cache").getType().toString();
	}

	@Override
	public long getUsageThreshold() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Code Cache").getUsageThreshold();
	}

	@Override
	public long getCommited() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Code Cache").getUsage().getCommitted();
	}

	@Override
	public long getInit() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Code Cache").getUsage().getInit();
	}

	@Override
	public long getMax() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Code Cache").getUsage().getMax();
	}

	@Override
	public long getUsed() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Code Cache").getUsage().getUsed();
	}
}
