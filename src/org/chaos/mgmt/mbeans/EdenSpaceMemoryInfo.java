package org.chaos.mgmt.mbeans;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;

import org.chaos.mgmt.agent.MBeanFactory;

/**
 * 대부분의 객체가 저장되는 메모리 정보 클래스
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
