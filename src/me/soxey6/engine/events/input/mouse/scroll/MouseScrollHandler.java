package me.soxey6.engine.events.input.mouse.scroll;

import me.soxey6.engine.managers.event.EventHandler;

public interface MouseScrollHandler extends EventHandler{
	public void onMouseScroll(MouseScrollEvent keyUpEvent);
}
