package me.soxey6.utils;

import org.lwjgl.util.vector.Vector2f;


public class LogicUtils {
	public static Vector2f calculateDirectionOffset(int x, int y, int offset, int direction)
	{
		Vector2f toReturn=new Vector2f(0,0);
		if(direction==0)
		{
			toReturn = new Vector2f(x, y+offset);
		}else if(direction==2)
		{
			toReturn = new Vector2f(x, y-offset);
		}else if(direction==1)
		{
			toReturn = new Vector2f(x+offset, y);
		}
		else if(direction==3)
		{
			toReturn = new Vector2f(x-offset, y);
		}
		return toReturn;
	}

	public static Vector2f calculateDirectionOffset(float x, float y,
			float offset, int direction)
	{
		Vector2f toReturn=new Vector2f(0,0);
		if(direction==0)
		{
			toReturn = new Vector2f(x, y+offset);
		}else if(direction==2)
		{
			toReturn = new Vector2f(x, y-offset);
		}else if(direction==1)
		{
			toReturn = new Vector2f(x+offset, y);
		}
		else if(direction==3)
		{
			toReturn = new Vector2f(x-offset, y);
		}
		return toReturn;		
	}
	
	public static int unNormalize(float v, float mv)
	{
		return (int)(v*mv);
	}
}
