package me.soxey6.engine.managers.input;

import me.soxey6.engine.main.Settings;

/**
 * A very basic class used for registering clicks and their locations.
 * Please us this instead of Vector2F
 * @author spanish
 */
public class Click {
	private int mouseButton;
	private float x;
	private float y;
	private ClickState clickState;
	
	public Click(int x, int y, int mouseButton)
	{
		try {
			this.setX((float)x/Settings.getSettings().getSetting("RESOLUTION_X").getValueInt());
			this.setY((float)y/Settings.getSettings().getSetting("RESOLUTION_Y").getValueInt());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.mouseButton= mouseButton;
		this.clickState = ClickState.CLICKED;
	}
	
	public Click(int x, int y, int mouseButton , ClickState clickState)
	{
		try {
			this.setX((float)x/Settings.getSettings().getSetting("RESOLUTION_X").getValueInt());
			this.setY((float)y/Settings.getSettings().getSetting("RESOLUTION_Y").getValueInt());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		this.mouseButton= mouseButton;
		this.clickState = clickState;
	}
	
	public Click(float x, float y, int mouseButton)
	{
		this.setX(x);
		this.setY(y);
		this.mouseButton= mouseButton;
		this.clickState = ClickState.CLICKED;
	}
	
	public Click(float x, float y, int mouseButton , ClickState clickState)
	{
		this.setX(x);
		this.setY(y);
		this.mouseButton= mouseButton;
		this.clickState = clickState;
	}
	

	public void press()
	{
		this.clickState = ClickState.CLICKED;
	}
	
	public void release()
	{
		this.clickState = ClickState.RELEASED;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public int getMouseButton() {
		return mouseButton;
	}

	public void setMouseButton(int mouseButton) {
		this.mouseButton = mouseButton;
	}

	public ClickState getClickState() {
		return clickState;
	}

	public void setClickState(ClickState clickState) {
		this.clickState = clickState;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
