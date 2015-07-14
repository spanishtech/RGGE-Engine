/*
 *  Game.java	0.1		7/13/15
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

package me.soxey6.engine.main;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.managers.file.FileManager;
import me.soxey6.engine.managers.input.InputManager;
import me.soxey6.engine.managers.scene.Scene;
import me.soxey6.engine.managers.scene.SceneManager;
import me.soxey6.engine.managers.sound.SoundManager;
import me.soxey6.engine.managers.time.TimeManager;
import me.soxey6.engine.objects.render.Display;
import me.soxey6.utils.Logger;
import me.soxey6.utils.PerformanceMonitor;
import me.soxey6.utils.RenderingUtils;

/**
 * This is the main class of the engine. This is where all initialization occurs and where the engine splits of into multiple threads.
 * This is the <i>Home</i> of the engine.
 * @author 		Spanish
 * @version		Dev-0.1
 */
public class Engine
{
	private final boolean GLOBAL_LIMIT_LOGIC = false;
	private final long GLOBAL_LOGIC_INCREMENT_MS = 100;
	private final boolean SHOW_SPLASH = true;
	private final int SPLASH_LENGTH_MS = 1000;
	
	private String gameName;
	
	private Display display;
	private Thread renderThread;
	
	private InputManager inputManager;
	private Thread inputThread;
	
	private PerformanceMonitor performanceMonitor;
	private Thread performanceThread;
	
	private TimeManager timer;
	private Thread timerThread;
	
	private boolean closeRequested = false;
	
	private static Engine engine;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private FileManager fileHandler;
	private SoundManager soundManager;
	private Logger logger;
	private RenderingUtils renderingUtils;
	private Settings settings;
	
	/**
	 * This is the constructor for the game. Creating new instances of this will create new instances of then game.
	 * Note: This can have numerous instances at once.
	 * @param gameName The name of the game
	 */
	public Engine(String gameName){
		// Sets the properities of this class
		engine=this;
		this.gameName=gameName;
		
		// Start creating instances of things
		this.logger = new Logger();
		this.eventManager = new EventManager();
		getLogger().log(getLogger().INFO, "Respawned");
		
		getLogger().log(getLogger().DEBUG, "Creating file manager instance");
		this.fileHandler = new FileManager();
		
		getLogger().log(getLogger().DEBUG, "Creating Scene manager instance");
		this.sceneManager=new SceneManager();
		
		getLogger().log(getLogger().DEBUG, "Creating Settings instance");
		this.settings = new Settings();
		
		getLogger().log(getLogger().DEBUG, "Creating Sound Manager Instance");
		this.soundManager = new SoundManager();
		
		this.performanceThread = new Thread(performanceMonitor = new PerformanceMonitor());
		
		getLogger().log(getLogger().DEBUG, "Creating Timer Instance");
		this.timerThread = new Thread(timer = new TimeManager());
		timerThread.start();
				
		// Initializes the display and openGL
		initDisplay();
		
		// Done after avoid issues
		getLogger().log(getLogger().DEBUG, "Creating Rendering utils instance");
		this.renderingUtils = new RenderingUtils();
		
		// Engine splash
		if(SHOW_SPLASH)
			// Magikarp use
			splash(); // hehehehe
		
		//Creates the objects to be used in the game.
		initGame();
		
		//Starts the game loop
		getLogger().log(getLogger().DEBUG, "Starting game loop");
		gameLoop();
		getLogger().log(getLogger().DEBUG, "Game loop left, cleanup started");
		
		// When game loop exits, it cleans up everything.
		cleanUp();
	}
	
	private void cleanUp(){
		getLogger().log(getLogger().INFO, "Cleaning up");
		System.exit(0);
	}

	private int gameLoop(){
		// Loops while the window has not attempted to be requested.
		while(!closeRequested)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	private void splash() {
		
	}
	
	private void initGame(){
		getLogger().log(getLogger().DEBUG, display.getWindowHandler());//"Creating Input Manager Instance");
		inputThread = new Thread(inputManager = new InputManager());
		inputThread.start();
		getLogger().log(getLogger().DEBUG, "Initializing Game");
		try {
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File("ping2.jar").toURI().toURL() });
			Class<?> clazz = classLoader.loadClass("com.test.thing.Splash");
			Scene scene = (Scene) clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initDisplay(){
		getLogger().log(getLogger().DEBUG, "Initializing Display");
		// This create a new window called whatever is passed through the constructor of this object.
		renderThread = new Thread((display = new Display(this.gameName, false)));
		renderThread.start();
		// Setup openGL
		getLogger().log(getLogger().DEBUG, "Setting up OpenGL instance");
	}


	/**
	 * Starts stopping all other threads, and starts the cleanup.
	 */
	public void threadCleanup(){
		this.closeRequested=true;
		this.performanceMonitor.shouldStop = true;
		this.inputManager.shouldStop = true;
		this.timer.shouldStop = true;
		getLogger().log(getLogger().INFO, "RIP");
	}
	
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public static Engine getEngine() {
		return engine;
	}

	public static void setEngine(Engine engine) {
		Engine.engine = engine;
	}
	
	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public FileManager getFileHandler() {
		return fileHandler;
	}

	public void setFileHandler(FileManager fileHandler) {
		this.fileHandler = fileHandler;
	}

	/**
	 * @return the soundManager
	 */
	public SoundManager getSoundManager()
	{
		return soundManager;
	}

	/**
	 * @param soundManager the soundManager to set
	 */
	public void setSoundManager(SoundManager soundManager)
	{
		this.soundManager = soundManager;
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

	public RenderingUtils getRenderingUtils() {
		return renderingUtils;
	}

	public void setRenderingUtils(RenderingUtils renderingUtils) {
		this.renderingUtils = renderingUtils;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public boolean isGLOBAL_LIMIT_LOGIC() {
		return GLOBAL_LIMIT_LOGIC;
	}

	public long getGLOBAL_LOGIC_INCREMENT_MS() {
		return GLOBAL_LOGIC_INCREMENT_MS;
	}

	public boolean isSHOW_SPLASH() {
		return SHOW_SPLASH;
	}

	public int getSPLASH_LENGTH_MS() {
		return SPLASH_LENGTH_MS;
	}

	/**
	 * @return the eventManager
	 */
	public EventManager getEventManager()
	{
		return eventManager;
	}

	/**
	 * @param eventManager the eventManager to set
	 */
	public void setEventManager(EventManager eventManager)
	{
		this.eventManager = eventManager;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

	public void setInputManager(InputManager inputManager) {
		this.inputManager = inputManager;
	}

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Thread getRenderThread() {
		return renderThread;
	}

	public void setRenderThread(Thread renderThread) {
		this.renderThread = renderThread;
	}

	public Thread getInputThread() {
		return inputThread;
	}

	public void setInputThread(Thread inputThread) {
		this.inputThread = inputThread;
	}

	public PerformanceMonitor getPerformanceMonitor() {
		return performanceMonitor;
	}

	public void setPerformanceMonitor(PerformanceMonitor performanceMonitor) {
		this.performanceMonitor = performanceMonitor;
	}

	public Thread getPerformanceThread() {
		return performanceThread;
	}

	public void setPerformanceThread(Thread performanceThread) {
		this.performanceThread = performanceThread;
	}

	public Thread getTimerThread() {
		return timerThread;
	}

	public void setTimerThread(Thread timerThread) {
		this.timerThread = timerThread;
	}
	
	
}
