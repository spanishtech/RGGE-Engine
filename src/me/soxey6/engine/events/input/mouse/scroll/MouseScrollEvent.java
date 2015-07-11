package me.soxey6.engine.events.input.mouse.scroll;

import me.soxey6.engine.managers.event.Event;

public class MouseScrollEvent extends Event{
	
	
	private int dScroll;
	
	public MouseScrollEvent( int dScroll )
	{
		this.setdScroll(dScroll);
	}

	public int getdScroll() {
		return dScroll;
	}

	public void setdScroll(int dScroll) {
		this.dScroll = dScroll;
	}


}
