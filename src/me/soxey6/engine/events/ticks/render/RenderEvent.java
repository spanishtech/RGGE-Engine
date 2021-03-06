/*
 *  RenderEvent.java	1.0		7/13/15
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

package me.soxey6.engine.events.ticks.render;

import me.soxey6.engine.managers.event.objects.Event;

/**
 * RenderEvent is a class used for sorting, filtering and dispatching events.
 * The event is created with the needed information about that event added to it
 * before being sent through the event system and dispatched to every listener
 * that this fits the filter of.
 * 
 * @version 1.0
 * @author Spanish Tech
 * @see Event
 */
public class RenderEvent extends Event {

	/**
	 * Constructs a RenderEvent object NOTE: There is no data for this event.
	 */
	public RenderEvent() {
	}

}
