package me.soxey6.engine.events.ticks.render;

import me.soxey6.engine.managers.event.EventHandler;

public interface RenderHandler extends EventHandler{
	public void onRender(RenderEvent keyUpEvent);
}
