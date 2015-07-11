package me.soxey6.engine.events.input.keys;

import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.event.EventListener;

public class KeyListener extends EventListener{

	private KeyHandler keyDownHandler;
	
	public KeyListener(KeyHandler keyDownHandler, KeyEvent keyDownEvent) {
		super(keyDownHandler, keyDownEvent);
		this.keyDownHandler = keyDownHandler;
	}
	
	
	public KeyListener(KeyHandler keyDownHandler, KeyEvent keyDownEvent, boolean strictFilter) {
		super(keyDownHandler, keyDownEvent, strictFilter);
		this.keyDownHandler = keyDownHandler;
	}
	
	@Override
	public void onEvent()
	{
		keyDownHandler.onEvent(getEvent());
		if(fitsFilter(getEvent()))
			onKeyDown((KeyEvent) getEvent());
	}
	
	@Override
	public boolean fitsFilter(Event event)
	{
		return isStrictFiltering()?(((KeyEvent) event).getKey().getKeyCode()==((KeyEvent)getEventFilter()).getKey().getKeyCode()&&((KeyEvent) event).getKey().getKeyAction()==((KeyEvent)getEventFilter()).getKey().getKeyAction()):getEventFilter().getClass().equals(event.getClass());
	}
	
	public void onKeyDown(KeyEvent keyDownEvent)
	{
		keyDownHandler.onKeyDown(keyDownEvent);
	}
}
