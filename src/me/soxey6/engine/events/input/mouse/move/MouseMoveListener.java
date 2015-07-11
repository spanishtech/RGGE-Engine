package me.soxey6.engine.events.input.mouse.move;

import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.event.EventListener;

public class MouseMoveListener extends EventListener{

	private MouseMoveHandler mouseMoveHandler;
	
	public MouseMoveListener(MouseMoveHandler mouseMoveHandler, MouseMoveEvent mouseMoveEvent) {
		super(mouseMoveHandler, mouseMoveEvent);
		this.mouseMoveHandler = mouseMoveHandler;
	}
	
	public MouseMoveListener(MouseMoveHandler mouseMoveHandler, MouseMoveEvent mouseMoveEvent, boolean strictFilter) {
		super(mouseMoveHandler, mouseMoveEvent, strictFilter);
		this.mouseMoveHandler = mouseMoveHandler;
	}
	
	@Override
	public void onEvent()
	{
		mouseMoveHandler.onEvent(getEvent());
		if(fitsFilter(getEvent()))
			onMouseMove((MouseMoveEvent) getEvent());
	}
	
	@Override
	public boolean fitsFilter(Event event)
	{
		if(isStrictFiltering())
			return (((MouseMoveEvent) event).getX()==((MouseMoveEvent)getEventFilter()).getX()&&((MouseMoveEvent) event).getY()==((MouseMoveEvent)getEventFilter()).getY());
		else
			return getEventFilter().getClass().equals(event.getClass());
	}
	
	public void onMouseMove(MouseMoveEvent mouseMoveEvent)
	{
		mouseMoveHandler.onMouseMove(mouseMoveEvent);
	}
}
