package me.soxey6.engine.managers.event;

import java.util.ArrayList;
import java.util.HashMap;

import me.soxey6.engine.events.ticks.render.RenderEvent;
import me.soxey6.engine.main.Engine;

/**
 * This class is used for dispatching, hooking and managing events. It is
 * effective at creating efficient and optimized calls to other classes and
 * objects.
 * 
 * @author pchilds
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EventManager {

	private static final long THREAD_CREATION_INTERVAL = 50;

	private HashMap<Class<? extends Event>, ArrayList<EventListener>> eventListeners;
	private static EventManager eventManager;

	private long lastThreadCreation;

	public EventManager() {
		eventManager = this;
		eventListeners = new HashMap<Class<? extends Event>, ArrayList<EventListener>>();
		lastThreadCreation = 0;
	}

	/**
	 * Used for dispatching certain events to Listeners.
	 * 
	 * @param String
	 *            eventName - The name of the event to trigger.
	 */
	public void dispatch(Event event) {
		if (eventListeners.containsKey(event.getClass())) {
			for (int i = 0; i < eventListeners.get(event.getClass()).size(); i++) {
				Engine.getEngine().getPerformanceMonitor().eps++;
				if (eventListeners.get(event.getClass()).get(i) != null)
					if (needCurrentThread(event)) {
						eventListeners.get(event.getClass()).get(i)
								.setEvent(event);
						new Thread(eventListeners.get(event.getClass()).get(i),
								eventListeners.get(event.getClass()).get(i)
										.toString()).start();
						lastThreadCreation = System.currentTimeMillis();
					} else {
						eventListeners.get(event.getClass()).get(i)
								.setEvent(event);
						try {
							eventListeners.get(event.getClass()).get(i)
									.onEvent();
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
			}
		}

	}

	private boolean needCurrentThread(Event event) {

		return !(System.currentTimeMillis() - lastThreadCreation < THREAD_CREATION_INTERVAL || event instanceof RenderEvent);
	}

	/**
	 * Used for adding listeners
	 * 
	 * @param EventListener
	 *            eventListener The listener to add
	 */
	public void addListener(EventListener eventListener) {
		if (eventListeners.containsKey(eventListener.getEventFilter()
				.getClass()))
			eventListeners.get(eventListener.getEventFilter().getClass()).add(
					eventListener);
		else {
			ArrayList<EventListener> tmpArray = new ArrayList<EventListener>();
			tmpArray.add(eventListener);
			eventListeners.put(eventListener.getEventFilter().getClass(),
					tmpArray);
		}
	}

	/**
	 * This function removes an event listener
	 * 
	 * @param EventListener
	 *            eventListener
	 */
	public void removeListener(EventListener eventListener) {
		eventListeners.remove(eventListener);
	}

	public static EventManager getEventManager() {
		return eventManager;
	}

	public static void setEventManager(EventManager eventManager) {
		EventManager.eventManager = eventManager;
	}

}
