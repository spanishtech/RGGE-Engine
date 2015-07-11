package me.soxey6.engine.events.input.keys;

import me.soxey6.engine.managers.event.EventHandler;

public interface KeyHandler extends EventHandler{
	public void onKeyDown(KeyEvent keyDownEvent);
}
