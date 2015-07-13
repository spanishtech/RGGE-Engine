package me.soxey6.engine.managers.event;

public interface EventHandler<T extends Event> {
	public void onEvent(T event);
}
