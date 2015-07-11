package me.soxey6.engine.events.ticks.timer;

import me.soxey6.engine.managers.event.EventHandler;

public interface TimerHandler extends EventHandler{
	public void onTimer(TimerEvent keyUpEvent);
}
