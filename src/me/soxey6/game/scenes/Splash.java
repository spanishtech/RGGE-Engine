package me.soxey6.game.scenes;

import me.soxey6.engine.events.input.mouse.move.MouseMoveEvent;
import me.soxey6.engine.events.input.mouse.move.MouseMoveHandler;
import me.soxey6.engine.events.input.mouse.move.MouseMoveListener;
import me.soxey6.engine.events.ticks.timer.TimerEvent;
import me.soxey6.engine.events.ticks.timer.TimerHandler;
import me.soxey6.engine.managers.event.Event;
import me.soxey6.engine.managers.scene.Scene;
import me.soxey6.engine.managers.time.Time;


public class Splash extends Scene implements MouseMoveHandler, TimerHandler{
	
	public Splash() {
		super("SPLASH");
		getEventManager().addListener(new MouseMoveListener(this, new MouseMoveEvent(0, 0)));
		for(int i = 0; i<1000; i++)
			getTimer().newTimer(new Time(10), this);
	}

	@Override
	public void onEvent(Event event) {		
	}	

	@Override
	public void onMouseMove(MouseMoveEvent mouseMoveEvent) {
		getLogger().log(0, mouseMoveEvent.getX());
	}

	@Override
	public void onTimer(TimerEvent keyUpEvent) {
	}

}
