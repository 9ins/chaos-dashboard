package org.chaostocosmos.chaosdashboard.mbeans;

import org.chaostocosmos.chaosdashboard.agent.MBeanFactory;

/**
 * CPU ������� ��ü
 * @author 9ins
 *
 */
public class CpuUsageInfo implements CpuUsageInfoMBean {
	
	private long totalCpuUsageAvgSum;
	
	private long totalUserUsageAvgSum;
	
	private long totalSysUsageAvgSum;
	
	@Override
	public long getTotalCpuTime() {
		return MBeanFactory.getInstance().getTotalCpuTime();
	}

	@Override
	public long getTotalUserTime() {
		return MBeanFactory.getInstance().getTotalUserTime();
	}

	@Override
	public long getTotalSysTime() {
		return MBeanFactory.getInstance().getTotalSystemTime();
	}

	@Override
	public int getTotalCpuUsage() {
		//x : 100 = val : total
		//100 * val = x * total
		//x = (100 * val) / total
		int usage = (int)((100 * getTotalCpuTime())/getTotalCpuTime());
		this.totalCpuUsageAvgSum += usage;
		return usage;
	}

	@Override
	public int getTotalUserUsage() {
		int usage = (int)((100 * getTotalUserTime())/getTotalCpuTime());
		this.totalUserUsageAvgSum += usage;
		return usage;
	}

	@Override
	public int getTotalSysUsage() {
		int usage = (int)((100 * getTotalSysTime())/getTotalCpuTime());
		this.totalSysUsageAvgSum += usage;
		return usage;
	}

	@Override
	public int getTotalCpuUsageAvg() {
		int avg = (int)((100 * this.totalCpuUsageAvgSum)/this.totalCpuUsageAvgSum);
		return avg;
	}

	@Override
	public int getTotalUserUsageAvg() {
		int avg = (int)((100 * this.totalUserUsageAvgSum)/this.totalCpuUsageAvgSum);
		return 0;
	}

	@Override
	public int getTotalSysUsageAvg() {
		int avg = (int)((100 * this.totalSysUsageAvgSum)/this.totalCpuUsageAvgSum);
		return 0;
	}

	@Override
	public long getTimeStemp() {
		return System.currentTimeMillis();
	}
}
