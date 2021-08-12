package org.chaostocosmos.chaosdashboard.mbeans;

import org.chaostocosmos.chaosdashboard.agent.MBeanFactory;

/**
 * ��κ��� ��ü�� ����Ǵ� �޸� ���� Ŭ����
 * @author 9ins
 *
 */
public class EdenSpaceMemoryInfo implements EdenSpaceMemoryInfoMBean {

	@Override
	public long getTimeStemp() {		
		return System.currentTimeMillis();
	}

	@Override
	public String getName() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Eden Space").getName();
	}

	@Override
	public String getType() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Eden Space").getType().toString();
	}

	@Override
	public long getUsageThreshold() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Eden Space").getUsageThreshold();
	}

	@Override
	public long getCommited() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Eden Space").getUsage().getCommitted();
	}

	@Override
	public long getInit() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Eden Space").getUsage().getInit();
	}

	@Override
	public long getMax() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Eden Space").getUsage().getMax();
	}

	@Override
	public long getUsed() {
		return MBeanFactory.getInstance().getMemoryPoolMXBean("Eden Space").getUsage().getUsed();
	}
}
