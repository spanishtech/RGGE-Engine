/*
 *  KeyEvent.java	1.2		7/12/15
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

package me.soxey6.engine.events.input.key;

import me.soxey6.engine.managers.event.objects.Event;
import me.soxey6.engine.managers.input.Key;

/**
 * KeyEvent is a class used for sorting, filtering and dispatching events. The
 * event is created with the needed information about that event added to it
 * before being sent through the event system and dispatched to every listener
 * that this fits the filter of.
 * 
 * @version 1.2
 * @author Spanish Tech
 * @see Event
 */

public class KeyEvent extends Event {

	/**
	 * key The current key of the event with such information as what key and
	 * whether it's released or pressed.
	 * 
	 * @see Key
	 */
	private Key key;

	/**
	 * The constructor of the KeyEvent class.
	 * 
	 * @param key
	 *            The information about the key that will be dispatched
	 * @See Key
	 */
	public KeyEvent(Key key) {
		this.key = key;
	}

	/**
	 * GetKey() is used to retrieving the key information of this event.
	 * 
	 * @return key The key information of this event
	 * @see Key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * GetKey() is used to set the key information of this event
	 * 
	 * @param key
	 *            The key information of this event
	 * @see Key
	 */
	public void setKey(Key key) {
		this.key = key;
	}
	
	/**
	 * Returns weather the supplied event has the same properties as this
	 * 
	 * @param event
	 *            An event to match.
	 * @return weather the supplied event has the same properties as this
	 */
	public boolean matches(KeyEvent event) {
		return (event.getKey() == getKey() || event.getKey() == null);
	}
}
