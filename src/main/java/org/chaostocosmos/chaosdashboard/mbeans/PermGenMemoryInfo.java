package org.chaostocosmos.chaosdashboard.mbeans;

import org.chaostocosmos.chaosdashboard.agent.MBeanFactory;

public class PermGenMemoryInfo implements PermGenMemoryInfoMBean {

	@Override
	public long getTimeStemp() {		
		return System.currentTimeMillis();
	}

	@Override
	public String getName() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Perm Gen").getName();
	}

	@Override
	public String getType() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Perm Gen").getType().toString();
	}

	@Override
	public long getUsageThreshold() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Perm Gen").getUsageThreshold();
	}

	@Override
	public long getCommited() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Perm Gen").getUsage().getCommitted();
	}

	@Override
	public long getInit() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Perm Gen").getUsage().getInit();
	}

	@Override
	public long getMax() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Perm Gen").getUsage().getMax();
	}

	@Override
	public long getUsed() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Perm Gen").getUsage().getUsed();
	}
}
