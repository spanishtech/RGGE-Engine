package me.soxey6.engine.events.ticks.render;

import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.event.EventListener;

public class RenderListener extends EventListener{

	private RenderHandler renderHandler;
	
	public RenderListener(RenderHandler renderHandler, RenderEvent renderEvent) {
		super(renderHandler, renderEvent);
		this.renderHandler = renderHandler;
	}
	
	public RenderListener(RenderHandler renderHandler, RenderEvent renderEvent, boolean strictFilter) {
		super(renderHandler, renderEvent, strictFilter);
		this.renderHandler = renderHandler;
	}
	
	@Override
	public void onEvent()
	{
		renderHandler.onEvent(getEvent());
		if(fitsFilter(getEvent()))
			onTimer((RenderEvent) getEvent());
	}
	
	@Override
	public boolean fitsFilter(Event event)
	{
		if(isStrictFiltering())
			return true;
		else
			return getEventFilter().getClass().equals(event.getClass());
	}
	
	public void onTimer(RenderEvent renderEvent)
	{
		renderHandler.onRender(renderEvent);
	}
}
