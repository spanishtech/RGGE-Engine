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

package me.soxey6.engine.events.scene;

import me.soxey6.engine.managers.event.objects.Event;
import me.soxey6.engine.managers.scene.Scene;

/**
 * FocusEvent is a class used for sorting, filtering and dispatching events. The
 * event is created with the needed information about that event added to it
 * before being sent through the event system and dispatched to every listener
 * that this fits the filter of.
 * 
 * @version 1.0
 * @author Spanish Tech
 * @see Event
 */
public class FocusEvent extends Event {
	/** Weather the scene is being focused or unfocused. */
	private boolean focused;

	/**
	 * The scene that is involved in this event
	 * 
	 * @see Scene
	 */
	private Scene scene;

	public FocusEvent(Scene scene, boolean focused) {
		this.focused = focused;
		this.scene = scene;
	}

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * Returns weather the supplied event has the same properties as this
	 * @param event An event to match.
	 * @return weather the supplied event has the same properties as this
	 */
	public boolean matches(FocusEvent event)
	{
		return (event.isFocused() == isFocused()&&(event.getScene() == getScene()||event.getScene()==null));
	}
}
