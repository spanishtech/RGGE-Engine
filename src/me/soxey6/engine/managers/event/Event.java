package me.soxey6.engine.managers.event;

import me.soxey6.engine.main.Wrapper;

@SuppressWarnings("rawtypes")
public class Event<T extends Event> extends Wrapper {
	public boolean matches(T compareEvent) {
		return true;
	}
}
