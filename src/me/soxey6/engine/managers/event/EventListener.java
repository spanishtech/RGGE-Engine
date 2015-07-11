package me.soxey6.engine.managers.event;


/**
 * This class is used for listening for events
 * @author spanish
 *
 */
public class EventListener implements Runnable{
	private Event eventFilter;
	private EventHandler eventHandler;
	private Event event;
	
	private boolean strictFiltering;
	
	public EventListener(EventHandler eventHandler, Event event)
	{
		this.eventHandler = eventHandler;
		this.eventFilter = event;
		this.strictFiltering = false;
	}

	public EventListener(EventHandler eventHandler, Event event, boolean strictFiltering)
	{
		this.eventHandler = eventHandler;
		this.eventFilter = event;
		this.strictFiltering = true;
	}
	

	@Override
	public void run() {
		onEvent();
	}
	
	public void onEvent()
	{
		if(event!=null)
			eventHandler.onEvent(event);
	}
	
	
	public boolean fitsFilter(Event event)
	{
		if(strictFiltering)
			return false;
		else
			return eventFilter.getClass().equals(event.getClass());
	}

	public Event getEventFilter() {
		return eventFilter;
	}

	public void setEventFilter(Event eventFilter) {
		this.eventFilter = eventFilter;
	}

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	public boolean isStrictFiltering() {
		return strictFiltering;
	}

	public void setStrictFiltering(boolean strictFiltering) {
		this.strictFiltering = strictFiltering;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}