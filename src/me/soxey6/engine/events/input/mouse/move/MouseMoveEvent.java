/*
 *  MouseMoveEvent.java	1.0		7/13/15
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

package me.soxey6.engine.events.input.mouse.move;

import me.soxey6.engine.managers.event.objects.Event;

/**
 * MouseMoveEvent is a class used for sorting, filtering and dispatching events.
 * The event is created with the needed information about that event added to it
 * before being sent through the event system and dispatched to every listener
 * that this fits the filter of.
 * 
 * @version 1.0
 * @author Spanish Tech
 * @see Event
 */

public class MouseMoveEvent extends Event {

	private float x;
	private float y;

	/**
	 * Constructs a MouseMoveEvent object with the required information
	 * 
	 * @param x
	 *            The X position of the event
	 * @param y
	 *            The Y position of the event
	 */
	public MouseMoveEvent(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the X position of the event
	 * 
	 * @return the X position of the event
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the X position of the event
	 * 
	 * @param x
	 *            the X position to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Returns the Y position of the event
	 * 
	 * @return the Y position of the event
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the Y position of the event
	 * 
	 * @param y
	 *            the Y position to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Returns weather the supplied event has the same properties as this
	 * 
	 * @param event
	 *            An event to match.
	 * @return weather the supplied event has the same properties as this
	 */
	public boolean matches(MouseMoveEvent event) {
		return (event.getX() == getX() || event.getY() == getY());
	}
}
