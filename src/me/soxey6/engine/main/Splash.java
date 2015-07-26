package me.soxey6.engine.main;

import me.soxey6.engine.events.input.key.KeyEvent;
import me.soxey6.engine.events.input.mouse.move.MouseMoveEvent;
import me.soxey6.engine.events.ticks.timer.TimerEvent;
import me.soxey6.engine.managers.event.EventListener;
import me.soxey6.engine.managers.input.Key;
import me.soxey6.engine.managers.scene.Scene;
import me.soxey6.engine.managers.time.Time;
import me.soxey6.utils.Logger;

public class Splash extends Scene {

	private EventListener<Splash, MouseMoveEvent> mouseMoveListener;
	private EventListener<Splash, KeyEvent> keyListener;

	public Splash() {
		super("SPLASH");
		for (int i = 0; i < 1; i++)
			getTimer().newTimer(
					new Time(1),
					new EventListener<Splash, TimerEvent>(this, new TimerEvent(
							1)));
		getEventManager()
				.addListener(
						this.mouseMoveListener = new EventListener<Splash, MouseMoveEvent>(
								this, new MouseMoveEvent(0, 0)) {
							@Override
							public void onEvent(MouseMoveEvent event) {
								Logger.getLogger().log(0, event.getX());
							}
						});
		getEventManager().addListener(
				this.keyListener = new EventListener<Splash, KeyEvent>(this,
						new KeyEvent(new Key(0, 0))) {
					@Override
					public void onEvent(KeyEvent event) {
						Logger.getLogger().log(0, event.getKey().getKeyCode());
					}
				});

	}

	@Override
	public void onFocus(boolean focused) {
	}

	public EventListener<Splash, MouseMoveEvent> getMouseMoveListener() {
		return mouseMoveListener;
	}

	public void setMouseMoveListener(
			EventListener<Splash, MouseMoveEvent> mouseMoveListener) {
		this.mouseMoveListener = mouseMoveListener;
	}

	public EventListener<Splash, KeyEvent> getKeyListener() {
		return keyListener;
	}

	public void setKeyListener(EventListener<Splash, KeyEvent> keyListener) {
		this.keyListener = keyListener;
	}

}
