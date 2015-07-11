package me.soxey6.engine.events.input.mouse.move;

import me.soxey6.engine.managers.event.EventHandler;

public interface MouseMoveHandler extends EventHandler{
	public void onMouseMove(MouseMoveEvent keyUpEvent);
}
