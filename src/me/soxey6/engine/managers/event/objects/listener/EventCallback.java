package me.soxey6.engine.managers.event.objects.listener;

import me.soxey6.engine.managers.event.objects.Event;

public interface EventCallback<T extends Event> {
	public void onEvent(T event);
}
