package me.soxey6.engine.managers.event;


/**
 * This class is used for listening for events
 * @author spanish
 *
 */
public class EventListener<T, S extends Event> implements Runnable{
	private S eventFilter;
	private T parent;
	private S event;
	
	private boolean strictFiltering;
	
	public EventListener(T parent, S event)
	{
		this.parent = parent;
		this.eventFilter = event;
		this.strictFiltering = false;
	}

	public EventListener(T parent, S event, boolean strictFiltering)
	{
		this.parent = parent;
		this.eventFilter = event;
		this.strictFiltering = true;
	}
	

	@Override
	public void run() {
		onEvent(event);
	}
	
	protected void onEvent(){
		
		if(fitsFilter(event)){
			onEvent(event);
		}
	}
	
	/**
	 * This function is triggered for every event dispatched that matches the filter, this should be overridden
	 * @param event An event containing the information of the event 
	 */
	public void onEvent(S event)
	{
	}
	
	
	public boolean fitsFilter(S event)
	{
		if(strictFiltering)
			return false;
		else
			return eventFilter.getClass().equals(event.getClass());
	}

	public S getEventFilter() {
		return eventFilter;
	}

	public void setEventFilter(S eventFilter) {
		this.eventFilter = eventFilter;
	}

	public T getEventHandler() {
		return parent;
	}

	public void setEventHandler(T eventHandler) {
		this.parent = eventHandler;
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

	public void setEvent(S event) {
		this.event = event;
	}
}