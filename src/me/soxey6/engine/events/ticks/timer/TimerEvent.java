/*
 *  TimerEvent.java	1.0		7/13/15
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

package me.soxey6.engine.events.ticks.timer;

import me.soxey6.engine.managers.event.objects.Event;

/**
 * TimerEvent is a class used for sorting, filtering and dispatching events. The
 * event is created with the needed information about that event added to it
 * before being sent through the event system and dispatched to every listener
 * that this fits the filter of.
 * 
 * @version 1.0
 * @author Spanish Tech
 * @see Event
 */
public class TimerEvent extends Event {

	private long interval;

	/**
	 * Constructs a TimerEvent object with the required information
	 * 
	 * @param interval
	 *            The interval in MS of this event
	 */
	public TimerEvent(long interval) {
		this.interval = interval;
	}

	/**
	 * Returns the interval of the event
	 * 
	 * @return the interval of the event
	 */
	public long getInterval() {
		return interval;
	}

	/**
	 * Sets the interval of the event
	 * 
	 * @param interval
	 *            the interval to set
	 */
	public void setInterval(long interval) {
		this.interval = interval;
	}
	
	/**
	 * Returns weather the supplied event has the same properties as this
	 * @param event An event to match.
	 * @return weather the supplied event has the same properties as this
	 */
	public boolean matches(TimerEvent event)
	{
		return (event.getInterval() == getInterval()||event.getInterval()==0);
	}
}
