package me.soxey6.engine.events.input.mouse.button;

import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.event.EventListener;

public class MouseButtonListener extends EventListener{

	private MouseButtonHandler mouseButtonHandler;
	
	public MouseButtonListener(MouseButtonHandler mouseButtonHandler, MouseButtonEvent mouseButtonEvent) {
		super(mouseButtonHandler, mouseButtonEvent);
		this.mouseButtonHandler = mouseButtonHandler;
	}
	
	public MouseButtonListener(MouseButtonHandler mouseButtonHandler, MouseButtonEvent mouseButtonEvent, boolean strictFilter) {
		super(mouseButtonHandler, mouseButtonEvent, strictFilter);
		this.mouseButtonHandler = mouseButtonHandler;
	}
	
	@Override
	public void onEvent()
	{
		mouseButtonHandler.onEvent(getEvent());
		if(fitsFilter(getEvent()))
			onMouseButton((MouseButtonEvent) getEvent());
	}
	
	@Override
	public boolean fitsFilter(Event event)
	{
		if(isStrictFiltering())
			return (((MouseButtonEvent) event).getX()==((MouseButtonEvent)getEventFilter()).getX()&&((MouseButtonEvent) event).getY()==((MouseButtonEvent)getEventFilter()).getY()&&((MouseButtonEvent) event).getMouseButton()==((MouseButtonEvent)getEventFilter()).getMouseButton());
		else
			return getEventFilter().getClass().equals(event.getClass());
	}
	
	public void onMouseButton(MouseButtonEvent mouseButtonEvent)
	{
		mouseButtonHandler.onMouseButton(mouseButtonEvent);
	}
}
