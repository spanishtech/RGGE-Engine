package me.soxey6.engine.managers.scene;

import java.util.HashMap;

import me.soxey6.engine.events.scene.FocusEvent;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.utils.Logger;

/**
 * This manager will manage different scenes and allow scene switching and force
 * the logic upon them when needed.
 * 
 * @author pchilds
 *
 */
public class SceneManager {
	private static SceneManager sceneManager;

	private Scene currentScene;

	private HashMap<String, Scene> scenes = new HashMap<String, Scene>();

	public SceneManager() {
		sceneManager = this;
		currentScene = null;
	}

	/**
	 * Returns a scene by the name
	 * 
	 * @param String
	 *            name - The name of the Scene
	 * @return Scene - The scene with the matching name (Null if none.)
	 */
	public Scene getScene(String name) {
		return getScenes().get(name);
	}

	/**
	 * Switches a scene by changing focus
	 * 
	 * @param String
	 *            sceneName - The name of the same to change to.
	 */
	public void switchScene(String sceneName) {
		// Set the found boolean to false
		boolean found = false;
		Logger.getLogger().log(Logger.getLogger().INFO,
				"Switching to scene: " + sceneName);
		// Iterate through each scene
		for (String key : getScenes().keySet()) {
			// Check if the scene is focused
			if (getScenes().get(key).isFocused()) {
				Logger.getLogger().log(Logger.getLogger().DEBUG,
						"Unfocusing: " + key);

				EventManager.getEventManager().dispatch(
						new FocusEvent(getScenes().get(key), false));
			}
			if ((key == sceneName)) {
				found = true;
				currentScene = getScenes().get(key);
				Logger.getLogger().log(Logger.getLogger().DEBUG,
						"Focusing: " + key);
				EventManager.getEventManager().dispatch(
						new FocusEvent(getScenes().get(key), true));
				getScenes().get(key).setFocused(true);
			}
		}
		// If the scene is not there
		if (!found) {
			// Catch event
			Logger.getLogger().log(Logger.getLogger().WARNING,
					"No scene by name: " + sceneName);
		}

	}

	/**
	 * Adds a scene to the array if it has not already been added.
	 * 
	 * @param Scene
	 *            scene - The scene to add
	 */
	public void addScene(Scene scene) {
		Logger.getLogger().log(Logger.getLogger().INFO,
				"Adding scene: " + scene.getName());
		if (getScenes().containsKey(scene.getName())) {
			Logger.getLogger().log(Logger.getLogger().WARNING,
					"Scene already added");
		} else {
			getScenes().put(scene.getName(), scene);
		}

	}

	public void removeScene(Scene scene) {
		getScenes().remove(scene);
	}

	public void removeScene(String sceneName) {
		for (int i = 0; i <= getScenes().size() - 1; i++)
			if (getScenes().get(i).getName().equalsIgnoreCase(sceneName))
				getScenes().remove(i);
	}

	public static SceneManager getSceneManager() {
		return sceneManager;
	}

	public static void setSceneManager(SceneManager sceneManager) {
		SceneManager.sceneManager = sceneManager;
	}

	public HashMap<String, Scene> getScenes() {
		return scenes;
	}

	public void setScenes(HashMap<String, Scene> scenes) {
		this.scenes = scenes;
	}

	public Scene getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(Scene currentScene) {
		this.currentScene = currentScene;
	}
}
