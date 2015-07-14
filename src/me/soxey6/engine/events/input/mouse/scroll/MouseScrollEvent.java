/*
 *  MouseScrollEvent.java	1.0		7/13/15
 * 
 *  This file is part of RGGE-Engine.
 *
 *  RGGE-Engine is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  RGGE-Engine is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RGGE-Engine.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.soxey6.engine.events.input.mouse.scroll;

import me.soxey6.engine.managers.event.Event;

/**
 * MouseScrollEvent is a class used for sorting, filtering and dispatching events.
 * The event is created with the needed information about that event added to it before being sent through the event system and dispatched to every listener that this fits the filter of.
 * 
 * @version		1.0
 * @author 		Spanish Tech
 * @see			Event
 */
public class MouseScrollEvent extends Event{
	
	
	private int dScroll;
	
	/**
	 * Constructs a MouseScrollEvent object with the required information
	 * @param dScroll The delta scroll of the event
	 */
	public MouseScrollEvent( int dScroll )
	{
		this.setdScroll(dScroll);
	}

	/**
	 * Returns the delta scroll of the event
	 * 
	 * @return the delta scroll of the event
	 */
	public int getdScroll() {
		return dScroll;
	}

	/**
	 * Sets the delta scroll of the event
	 * 
	 * @param dScroll the delta scroll to set
	 */
	public void setdScroll(int dScroll) {
		this.dScroll = dScroll;
	}


}
