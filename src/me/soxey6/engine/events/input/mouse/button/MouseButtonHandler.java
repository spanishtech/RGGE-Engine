package me.soxey6.engine.events.input.mouse.button;

import me.soxey6.engine.managers.event.EventHandler;

public interface MouseButtonHandler extends EventHandler{
	public void onMouseButton(MouseButtonEvent mouseButtonEvent);
}
