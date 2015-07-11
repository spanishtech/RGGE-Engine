package me.soxey6.engine.managers.input;

import me.soxey6.engine.main.Settings;

public class MousePos {
	private float x;
	private float y;
	
	public MousePos(int x, int y)
	{
		try {
			setX((float)x/Settings.getSettings().getSetting("RESOLUTION_X").getValueInt());
			setY((float)y/Settings.getSettings().getSetting("RESOLUTION_Y").getValueInt());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public MousePos(float x, float y)
	{
		setX(x);
		setY(y);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setX(int x) {
		try {
			this.x = (float)x/Settings.getSettings().getSetting("RESOLUTION_X").getValueInt();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setY(int y) {
		try {
			this.y = (float)y/Settings.getSettings().getSetting("RESOLUTION_Y").getValueInt();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
