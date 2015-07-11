package me.soxey6.engine.main;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.managers.file.FileManager;
import me.soxey6.engine.managers.input.InputManager;
import me.soxey6.engine.managers.scene.SceneManager;
import me.soxey6.engine.managers.sound.SoundManager;
import me.soxey6.engine.managers.time.TimeManager;
import me.soxey6.engine.objects.render.Display;
import me.soxey6.game.scenes.Splash;
import me.soxey6.utils.Logger;
import me.soxey6.utils.PerformanceMonitor;
import me.soxey6.utils.RenderingUtils;
/**
 * The game class is the entire game in a single class that can be created with an instance.
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.2
 */
public class Game
{
	private final boolean GLOBAL_LIMIT_LOGIC = false;
	private final long GLOBAL_LOGIC_INCREMENT_MS = 100;
	private final boolean SHOW_SPLASH = true;
	private final int SPLASH_LENGTH_MS = 5000;
	
	private String gameName;
	
	private Display display;
	private Thread renderThread;
	
	private InputManager inputManager;
	private Thread inputThread;
	
	private PerformanceMonitor performanceMonitor;
	private Thread performanceThread;
	
	private TimeManager timer;
	private Thread timerThread;
	
	public boolean closeRequested = false;
	
	private static Game game;
	private EventManager eventManager;
	private SceneManager sceneManager;
	private FileManager fileHandler;
	private SoundManager soundManager;
	private Logger logger;
	private RenderingUtils renderingUtils;
	private Settings settings;
		/**
	 * The game object is the whole game itself. Creating a new one will create a new game.
	 * Note: This can have numerous instances at once.
	 * @param String gameName
	 */
	public Game(String gameName)
	{
		game=this;
		
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
		
		// Sets the game name from the constructor
		this.gameName=gameName;
		
		// Creates an instance of the error handler class and sets it for later use.
		
		// Initializes the display and openGL
		initDisplay();
		
		// Done after avoid issues
		getLogger().log(getLogger().DEBUG, "Creating Rendering utils instance");
		this.renderingUtils = new RenderingUtils();
		
		// Engine splash
		if(SHOW_SPLASH)
			// Magikarp use
			splash();
		
		//Creates the objects to be used in the game.
		initGame();
		initSettings();
		//Starts the game loop
		getLogger().log(getLogger().DEBUG, "Starting game loop");
		gameLoop();
		getLogger().log(getLogger().DEBUG, "Game loop left, cleanup started");
		
		// When game loop exits, it cleans up everything.
		cleanUp();
	}

	public void threadCleanup()
	{
		this.closeRequested=true;
		this.performanceMonitor.shouldStop = true;
		this.inputManager.shouldStop = true;
		this.timer.shouldStop = true;
		getLogger().log(getLogger().INFO, "RIP");
	}
	
	/**
	 * Cleans up by <b>destroying</b> the window and OpenGL setup
	 * @return Void
	 * @param None
	 */
	private void cleanUp()
	{
		getLogger().log(getLogger().INFO, "Cleaning up");
		try {
			display.destroy();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * All the game logic, rendering and input is processed here.
	 * This will continue to loop until the window is 
	 */
	private int gameLoop()
	{
		// Loops while the window has not attempted to be requested.
		while(!closeRequested)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * This will render the engine splash, because #YOLO
	 */
	private void splash() {
		
	}
	
	/**
	 * This is where the game initializes (After setting up rendering)
	 * All code that needs to be run at the beginning and only once should be put here.
	 */
	private void initGame()
	{
		getLogger().log(getLogger().DEBUG, display.getWindowHandler());//"Creating Input Manager Instance");
		inputThread = new Thread(inputManager = new InputManager());
		inputThread.start();
		getLogger().log(getLogger().DEBUG, "Initializing Game");
		new Splash();
		getSceneManager().switchScene("SPLASH");
	}

	/**
	 * This is where the display is initialized.
	 */
	private void initDisplay()
	{
		getLogger().log(getLogger().DEBUG, "Initializing Display");
		// This create a new window called whatever is passed through the constructor of this object.
		renderThread = new Thread((display = new Display(this.gameName, false)));
		renderThread.start();
		// Setup openGL
		getLogger().log(getLogger().DEBUG, "Setting up OpenGL instance");
	}

	/**
	 * This function initializes basic settings
	 */
	public void initSettings()
	{
		getLogger().log(getLogger().DEBUG, "Setting up settings");
		
	}
	
	/**
	 * This function when called will update the inputs.
	 * @return Error values
	 */
	public int input()
	{
		inputManager.input();
		return 0;
	}
	
	/**
	 * All logic code should be placed including calling logic() in objects. 
	 * @return Error values
	 */
	public int logic()
	{
		// Simple, ey?
		return 0;
		
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Game.game = game;
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
