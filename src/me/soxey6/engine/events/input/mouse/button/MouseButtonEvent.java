package me.soxey6.engine.events.input.mouse.button;

import me.soxey6.engine.managers.event.Event;

public class MouseButtonEvent extends Event{
	
	
	private float x;
	private float y;
	private int mouseButton;
	private int action;
	
	public MouseButtonEvent( float x, float y, int mouseButton, int action )
	{
		this.x = x;
		this.y = y;
		this.mouseButton = mouseButton;
		this.action = action;
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

	public int getMouseButton() {
		return mouseButton;
	}

	public void setMouseButton(int mouseButton) {
		this.mouseButton = mouseButton;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}


}
