package me.soxey6.engine.events.ticks.timer;

import me.soxey6.engine.managers.event.Event;

public class TimerEvent extends Event{
	
	
	private long interval;
	
	public TimerEvent( long l )
	{
		this.interval = l;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
}
