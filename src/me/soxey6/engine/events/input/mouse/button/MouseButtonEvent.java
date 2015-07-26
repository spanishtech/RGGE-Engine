/*
 *  MouseButtonEvent.java	1.1		7/12/15
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

package me.soxey6.engine.events.input.mouse.button;

import me.soxey6.engine.managers.event.Event;

/**
 * MouseButtonEvent is a class used for sorting, filtering and dispatching
 * events. The event is created with the needed information about that event
 * added to it before being sent through the event system and dispatched to
 * every listener that this fits the filter of.
 * 
 * @version 1.1
 * @author Spanish Tech
 * @see Event
 */
public class MouseButtonEvent extends Event<MouseButtonEvent> {

	private float x;
	private float y;

	private int mouseButton;

	private int action;

	/**
	 * Constructs a MouseButtonEvent object with the required information
	 * 
	 * @param x
	 *            The X position of the event
	 * @param y
	 *            The Y position of the event
	 * @param mouseButton
	 *            The mouse button associated with this event
	 * @param action
	 *            The action of the button for this event
	 */
	public MouseButtonEvent(float x, float y, int mouseButton, int action) {
		this.x = x;
		this.y = y;
		this.mouseButton = mouseButton;
		this.action = action;
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
	 * Returns the button involved in this event
	 * 
	 * @return The button involved in this event
	 */
	public int getMouseButton() {
		return mouseButton;
	}

	/**
	 * Sets the button involved in this event
	 * 
	 * @param mouseButton
	 *            The button involved in this event
	 */
	public void setMouseButton(int mouseButton) {
		this.mouseButton = mouseButton;
	}

	/**
	 * Returns the action involved in this event
	 * 
	 * @return The action involved in this event
	 */
	public int getAction() {
		return action;
	}

	/**
	 * Sets the action involved in this event
	 * 
	 * @param action
	 *            The action involved in this event
	 */
	public void setAction(int action) {
		this.action = action;
	}

	/**
	 * Returns weather the supplied event has the same properties as this
	 * 
	 * @param event
	 *            An event to match.
	 * @return weather the supplied event has the same properties as this
	 */
	public boolean matches(MouseButtonEvent event) {
		return (event.getAction() == getAction() && event.getMouseButton()==getMouseButton());
	}
}
