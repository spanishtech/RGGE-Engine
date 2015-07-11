package me.soxey6.engine.events.input.mouse.move;

import me.soxey6.engine.managers.event.Event;

public class MouseMoveEvent extends Event{

	private float x;
	private float y;
	
	public MouseMoveEvent( float x, float y)
	{

		
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
