package org.chaostocosmos.chaosdashboard.mbeans;

import org.chaostocosmos.chaosdashboard.agent.MBeanFactory;

public class TenuredGenMemoryInfo implements TenuredGenMemoryInfoMBean {

	@Override
	public long getTimeStemp() {		
		return System.currentTimeMillis();
	}

	@Override
	public String getName() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Tenured Gen").getName();
	}

	@Override
	public String getType() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Tenured Gen").getType().toString();
	}

	@Override
	public long getUsageThreshold() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Tenured Gen").getUsageThreshold();
	}

	@Override
	public long getCommited() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Tenured Gen").getUsage().getCommitted();
	}

	@Override
	public long getInit() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Tenured Gen").getUsage().getInit();
	}

	@Override
	public long getMax() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Tenured Gen").getUsage().getMax();
	}

	@Override
	public long getUsed() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Tenured Gen").getUsage().getUsed();
	}
}
