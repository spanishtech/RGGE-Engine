package me.soxey6.engine.managers.event;

import java.util.ArrayList;
import java.util.HashMap;

import me.soxey6.engine.events.ticks.render.RenderEvent;
import me.soxey6.engine.events.ticks.timer.TimerEvent;
import me.soxey6.engine.main.Engine;

/**
 * This class is used for dispatching, hooking and managing events.
 * It is effective at creating efficient and optimized calls to other classes and objects.
 * @author pchilds
 *
 */
@SuppressWarnings({"rawtypes","unchecked"})

public class EventManager {
		
	private HashMap<Class<? extends Event>, ArrayList<EventListener>> eventListeners;
	private static EventManager eventManager;
	public EventManager()
	{
		eventManager=this;
		eventListeners = new HashMap<Class<? extends Event>, ArrayList<EventListener>>();
	}

	/**
	 * Used for dispatching certain events to Listeners.
	 * @param String eventName - The name of the event to trigger.
	 */
	public void dispatch(Event event)
	{

		if(eventListeners.containsKey(event.getClass())){
			for(int i = 0; i<eventListeners.get(event.getClass()).size(); i++)
			{
				Engine.getEngine().getPerformanceMonitor().eps++;
				if(eventListeners.get(event.getClass()).get(i)!=null)
					if(needCurrentThread(event))
					{
						eventListeners.get(event.getClass()).get(i).setEvent(event);
						new Thread(eventListeners.get(event.getClass()).get(i)).start();
					}else{
						eventListeners.get(event.getClass()).get(i).setEvent(event);
						eventListeners.get(event.getClass()).get(i).onEvent();
					}
			}
		}
			
				
	}
	
	private boolean needCurrentThread(Event event) {
		
		return  !(((event instanceof TimerEvent) &&
				((TimerEvent) event).getInterval()<500) ||
				event instanceof RenderEvent);
	}

	/**
	 * Used for adding listeners
	 * @param EventListener eventListener The listener to add
	 */
	public void addListener(EventListener eventListener)
	{
		if(eventListeners.containsKey(eventListener.getEventFilter().getClass()))
			eventListeners.get(eventListener.getEventFilter().getClass()).add(eventListener);
		else
		{
			ArrayList<EventListener> tmpArray = new ArrayList<EventListener>();
			tmpArray.add(eventListener);
			eventListeners.put(eventListener.getEventFilter().getClass(), tmpArray);
		}
	}
	
	/**
	 * This function removes an event listener
	 * @param EventListener eventListener
	 */
	public void removeListener(EventListener eventListener)
	{
		eventListeners.remove(eventListener);
	}
	
	/**
	 * This function removes all event listeners that call to the provided handler.
	 * @param EventHandler eventHandler the handler used to identify the listeners
	 */
	public void removeListeners(EventHandler eventHandler)
	{
	    for(Class<? extends Event> key: eventListeners.keySet())
	    {
	    	int i = 0;
	    	while(i<eventListeners.get(key).size())
	    	{
	    		if(eventListeners.get(key).get(i).getEventHandler()==eventHandler)
	    			eventListeners.get(key).remove(i++);
	    	}

	    }
	}
	
	public static EventManager getEventManager() {
		return eventManager;
	}
	public static void setEventManager(EventManager eventManager) {
		EventManager.eventManager = eventManager;
	}

}
