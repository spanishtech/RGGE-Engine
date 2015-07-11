package me.soxey6.engine.events.input.keys;

import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.input.Key;

public class KeyEvent extends Event{
	
	private Key key;
	
	public KeyEvent(Key key)
	{
		this.key = key;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
}
