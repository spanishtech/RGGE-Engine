package me.soxey6.engine.events.ticks.timer;

import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.event.EventListener;

public class TimerListener extends EventListener{

	private TimerHandler timeHandler;
	
	public TimerListener(TimerHandler timeHandler, TimerEvent timerEvent) {
		super(timeHandler, timerEvent);
		this.timeHandler = timeHandler;
	}
	
	public TimerListener(TimerHandler timeHandler, TimerEvent timerEvent, boolean strictFilter) {
		super(timeHandler, timerEvent, strictFilter);
		this.timeHandler = timeHandler;
	}
	
	@Override
	public void onEvent()
	{
		timeHandler.onEvent(getEvent());
		if(fitsFilter(getEvent()))
			onTimer((TimerEvent) getEvent());
	}
	
	@Override
	public boolean fitsFilter(Event event)
	{
		if(isStrictFiltering())
			return (((TimerEvent) event).getInterval()==((TimerEvent)getEventFilter()).getInterval());
		else
			return getEventFilter().getClass().equals(event.getClass());
	}
	
	public void onTimer(TimerEvent timerEvent)
	{
		timeHandler.onTimer(timerEvent);
	}
}
