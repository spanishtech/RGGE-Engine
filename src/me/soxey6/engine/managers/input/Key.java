package me.soxey6.engine.managers.input;

import static org.lwjgl.glfw.GLFW.*;


public class Key {
	
	private int keyCode;
	private int keyAction;
	
	public Key(int keyCode, int keyState)
	{
		this.keyCode	= keyCode;
		this.keyAction	= keyState;
	}
	
	public void press()
	{
		this.keyAction = GLFW_PRESS;
	}
	
	public void release()
	{
		this.keyAction = GLFW_RELEASE;
	}
	
	public boolean isPressed()
	{
		return keyAction==GLFW_PRESS;
	}

	public boolean isReleased()
	{
		return keyAction==GLFW_RELEASE;
	}
	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getKeyAction() {
		return keyAction;
	}

	public void setKeyState(int keyState) {
		this.keyAction = keyState;
	}
	
	

}
