package me.soxey6.engine.events.scene;

import me.soxey6.engine.managers.event.objects.Event;
import me.soxey6.engine.managers.scene.Scene;

public class SceneCreateEvent extends Event{
	private Scene sceneCreated;

	public SceneCreateEvent(Scene sceneCreated){
		this.sceneCreated = sceneCreated;
	}
	
	public Scene getSceneCreated() {
		return sceneCreated;
	}

	public void setSceneCreated(Scene sceneCreated) {
		this.sceneCreated = sceneCreated;
	}
}
