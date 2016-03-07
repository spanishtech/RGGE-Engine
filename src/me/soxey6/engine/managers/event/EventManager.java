package me.soxey6.engine.managers.event;

import java.util.ArrayList;
import java.util.HashMap;

import me.soxey6.engine.managers.event.objects.Event;
import me.soxey6.engine.managers.event.objects.listener.EventListener;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EventManager {
	private static EventManager eventManager;

	private final long THREAD_CREATION_DELAY = 10;

	private long lastRun = 0;

	private HashMap<Class<? extends Event>, ArrayList<EventListener>> eventListenerList;

	public EventManager() {
		eventManager = this;
		eventListenerList = new HashMap<Class<? extends Event>, ArrayList<EventListener>>();
	}

	public void dispatch(Event event) {
		getEventListenerList().entrySet().stream().filter(entry -> entry.getKey().equals(event.getClass()))
		        .forEach(entry -> entry.getValue().stream()
		                .filter(eventListener -> eventListener.getEventFilter().getClass().equals(event.getClass()))
		                .forEach(eventListener -> {
			                if (needsNewThread()) {
				                eventListener.setCurrentEvent(event);
				                new Thread(eventListener).start();
			                } else {
				                eventListener.onEvent(event);
			                }

		                }));
	}

	public boolean needsNewThread() {
		// TODO: Do threading
		if (System.currentTimeMillis() >= lastRun + THREAD_CREATION_DELAY) {
			lastRun = System.currentTimeMillis();
			return true;
		} else {
			return false;
		}
	}

	public void registerListener(EventListener eventListener, Class<? extends Event> eventFilter) {
		// If the Filter is already registered and has a working array
		if (getEventListenerList().containsKey(eventFilter)
		        || getEventListenerList().get(eventFilter) != null) {
			getEventListenerList().get(eventFilter).add(eventListener);
		} else {
			ArrayList<EventListener> tmpArray = new ArrayList<EventListener>();
			tmpArray.add(eventListener);
			getEventListenerList().put(eventFilter, tmpArray);
		}

	}

	public void registerListener(EventListener eventListener) {
		// If the Filter is already registered and has a working array
		if (getEventListenerList().containsKey(eventListener.getEventFilter())
		        || getEventListenerList().get(eventListener.getEventFilter()) != null) {
			getEventListenerList().get(eventListener.getEventFilter()).add(eventListener);
		} else {
			ArrayList<EventListener> tmpArray = new ArrayList<EventListener>();
			tmpArray.add(eventListener);
			getEventListenerList().put(eventListener.getEventFilter(), tmpArray);
		}

	}
	
	public void removeListener(EventListener eventListener, Class<? extends Event> eventFilter) {
		if (getEventListenerList().containsKey(eventFilter)
		        || getEventListenerList().get(eventFilter) != null) {
			getEventListenerList().get(eventFilter).remove(eventListener);
		}
	}
	
	public void removeListener(EventListener eventListener) {
		if (getEventListenerList().containsKey(eventListener.getEventFilter())
		        || getEventListenerList().get(eventListener.getEventFilter()) != null) {
			getEventListenerList().get(eventListener.getEventFilter()).remove(eventListener);
		}
	}

	public static EventManager getEventManager() {
		return eventManager;
	}

	public static void setEventManager(EventManager eventManager) {
		EventManager.eventManager = eventManager;
	}

	public HashMap<Class<? extends Event>, ArrayList<EventListener>> getEventListenerList() {
		return eventListenerList;
	}

	public void setEventListenerList(HashMap<Class<? extends Event>, ArrayList<EventListener>> eventListenerList) {
		this.eventListenerList = eventListenerList;
	}
	
	public void finalize() throws Throwable{
		setEventListenerList(new HashMap<Class<? extends Event>, ArrayList<EventListener>>());
		super.finalize();
	}
}
