package me.soxey6.engine.managers.time;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.soxey6.engine.events.ticks.timer.TimerEvent;
import me.soxey6.engine.events.ticks.timer.TimerHandler;
import me.soxey6.engine.events.ticks.timer.TimerListener;
import me.soxey6.engine.main.Game;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.utils.Logger;

public class TimeManager implements Runnable
{
	public boolean shouldStop = false;
	
	private static TimeManager timeManager;
	private HashMap<Time,Timer> timers;
	
	public TimeManager()
	{
		timeManager=this;
		this.timers = new HashMap<Time,Timer>();
	}
	
	public void newTimer(Time time, TimerHandler timerHandler)
	{
		if(timers==null)
			timers = new HashMap<Time,Timer>();
		// Create a new timer and register the event
		EventManager.getEventManager().addListener(new TimerListener(timerHandler, new TimerEvent(time.getInterval())));
		if(!isNewTimer(time))
		{
			timers.put(time ,new Timer(String.valueOf(time.getInterval())));
			timers.get(time).schedule(new TimerTask(){
				@Override
	            public void run() {
	                EventManager.getEventManager().dispatch(new TimerEvent(time.getInterval()));
	                Game.getGame().getPerformanceMonitor().tps++;
	            }
			}, 0, time.getInterval());
		}
	}

	@SuppressWarnings("rawtypes")
	private boolean isNewTimer(Time time) {
		
	    Iterator it = timers.entrySet().iterator();
	    
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        
	        if(((Time)pair.getKey()).getInterval()==time.getInterval())
	        	return true;
	        
	        it.remove(); 
	    }
		return false;
	}

	/**
	 * @return the timer
	 */
	public static TimeManager getTimeManager()
	{
		return timeManager;
	}

	/**
	 * @param timer the timer to set
	 */
	public static void setTimeManager(TimeManager timer)
	{
		TimeManager.timeManager = timer;
	}

	@Override
	public void run() {
		while(!shouldStop)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Logger.getLogger().log(Logger.getLogger().INFO, "TimeManager thread closing");
	}
}
