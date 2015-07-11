package me.soxey6.utils;

public class PerformanceMonitor implements Runnable{
	
	private static PerformanceMonitor performanceMonitor;
	
	public boolean shouldStop = false;
	
	public long eps=0;
	public long fps=0;
	public long ips=0;
	public long tps=0;
	
	public PerformanceMonitor()
	{
		performanceMonitor = this;
	}
	
	public static PerformanceMonitor getResourceMonitor() {
		return performanceMonitor;
	}
	public static void setResourceMonitor(PerformanceMonitor performanceMonitor) {
		PerformanceMonitor.performanceMonitor = performanceMonitor;
	}
	
	public void monitor()
	{
		while(!shouldStop)
		{
			printStats();
			resetStats();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void printStats()
	{
		Logger.getLogger().log(Logger.getLogger().INFO, "\n---------------------------------\nEPS: "+eps+"	("+(eps==0?0:(float)((float)1000/(float)eps))+"ms)	|\nFPS: "+fps+"		("+(fps==0?0:(float)((float)1000/(float)fps))+"ms)	|\nIPS: "+ips+"		("+(ips==0?0:(float)((float)1000/(float)ips))+"ms)	|\nTPS: "+tps+"		("+(tps==0?0:(float)((float)1000/(float)tps))+"ms)	|\n---------------------------------");
	}
	
	public void resetStats()
	{
		eps=0;
		fps=0;
		ips=0;
		tps=0;
	}

	@Override
	public void run() {
		resetStats();
		monitor();
	}
}
