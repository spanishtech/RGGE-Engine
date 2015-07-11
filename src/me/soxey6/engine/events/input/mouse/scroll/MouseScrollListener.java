package me.soxey6.engine.events.input.mouse.scroll;

import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.event.EventListener;

public class MouseScrollListener extends EventListener{

	private MouseScrollHandler mouseScrollHandler;
	
	public MouseScrollListener(MouseScrollHandler mouseScrollHandler, MouseScrollEvent mouseScrollEvent) {
		super(mouseScrollHandler, mouseScrollEvent);
		this.mouseScrollHandler = mouseScrollHandler;
	}
	
	public MouseScrollListener(MouseScrollHandler mouseScrollHandler, MouseScrollEvent mouseScrollEvent, boolean strictFilter) {
		super(mouseScrollHandler, mouseScrollEvent, strictFilter);
		this.mouseScrollHandler = mouseScrollHandler;
	}
	
	@Override
	public void onEvent()
	{
		mouseScrollHandler.onEvent(getEvent());
		if(fitsFilter(getEvent()))
			onMouseScroll((MouseScrollEvent) getEvent());
	}
	
	@Override
	public boolean fitsFilter(Event event)
	{
		if(isStrictFiltering())
			return (((MouseScrollEvent) event).getdScroll()==((MouseScrollEvent)getEventFilter()).getdScroll());
		else
			return getEventFilter().getClass().equals(event.getClass());
	}
	
	public void onMouseScroll(MouseScrollEvent mouseScrollEvent)
	{
		mouseScrollHandler.onMouseScroll(mouseScrollEvent);
	}
}
