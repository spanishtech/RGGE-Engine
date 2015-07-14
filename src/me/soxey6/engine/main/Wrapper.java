package me.soxey6.engine.main;

import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.managers.file.FileManager;
import me.soxey6.engine.managers.input.InputManager;
import me.soxey6.engine.managers.scene.SceneManager;
import me.soxey6.engine.managers.sound.SoundManager;
import me.soxey6.engine.managers.time.TimeManager;
import me.soxey6.utils.Logger;
import me.soxey6.utils.RenderingUtils;

/**
 * This wrapper is used for calling other classes that are semi-static. Managers and such. This should be extended to most classes in the game as it allows access to them easily.
 * @author pchilds
 *
 */
public class Wrapper{
	// All the classes we need.
	private Engine game;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private FileManager fileManager;
	private InputManager inputManager;
	private SoundManager soundManager;
	private RenderingUtils renderingUtils;

	private TimeManager timer;
	private Logger logger;
	private Settings settings;
	
	public Wrapper()
	{
		// Make sure none of the variables are null when created.
		this.game=Engine.getEngine();
		this.eventManager = EventManager.getEventManager();
		this.sceneManager = SceneManager.getSceneManager();
		this.fileManager  = FileManager.getFileManager();
		this.inputManager = InputManager.getInputManager();
		this.soundManager = SoundManager.getSoundManager();
		this.renderingUtils = RenderingUtils.getRenderingUtils();
		this.timer = TimeManager.getTimeManager();
		this.logger = Logger.getLogger();
		this.settings = Settings.getSettings();
	}

	
	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public InputManager getInputManager() {
		return inputManager;
	}


	public void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	public SoundManager getSoundManager() {
		return soundManager;
	}


	public void setSoundManager(SoundManager soundManager) {
		this.soundManager = soundManager;
	}

	public RenderingUtils getRenderingUtils() {
		return renderingUtils;
	}

	public void setRenderingUtils(RenderingUtils renderingUtils) {
		this.renderingUtils = renderingUtils;
	}

	/**
	 * @return the timer
	 */
	public TimeManager getTimer()
	{
		return timer;
	}


	/**
	 * @param timer the timer to set
	 */
	public void setTimer(TimeManager timer)
	{
		this.timer = timer;
	}


	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public Engine getGame() {
		return game;
	}

	public void setGame(Engine game) {
		this.game = game;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}


	public FileManager getFileManager() {
		return fileManager;
	}


	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

}
