package me.soxey6.engine.managers.event.objects.listener;

import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.managers.event.objects.Event;

public class EventListener<T extends Event> implements Runnable{
	private T currentEvent;
	private Class<T> eventFilter;
	private EventCallback<T> eventCallback;

	public EventListener(Class<T> eventFilter, EventCallback<T> eventCallback){
		this.eventFilter = eventFilter;
		this.eventCallback = eventCallback;
		EventManager.getEventManager().registerListener(this);
	}
	
	public void onEvent(T event)
	{
		getEventCallback().onEvent(event);
	}

	public void onEvent()
	{
		getEventCallback().onEvent(getCurrentEvent());
	}
	

	public Class<T> getEventFilter() {
		return eventFilter;
	}

	public void setEventFilter(Class<T> eventFilter) {
		this.eventFilter = eventFilter;
	}

	public EventCallback<T> getEventCallback() {
		return eventCallback;
	}

	public void setEventCallback(EventCallback<T> eventCallback) {
		this.eventCallback = eventCallback;
	}

	@Override
	public void run() {
		if(getCurrentEvent()!=null){
			onEvent();
		}
	}

	public T getCurrentEvent() {
		return currentEvent;
	}

	public void setCurrentEvent(T currentEvent) {
		this.currentEvent = currentEvent;
	}
	
	public void finalize() throws Throwable{
		EventManager.getEventManager().removeListener(this);
		super.finalize();
	}
}
