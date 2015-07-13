package me.soxey6.engine.managers.input;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F8;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import me.soxey6.engine.events.input.key.KeyEvent;
import me.soxey6.engine.events.input.mouse.button.MouseButtonEvent;
import me.soxey6.engine.events.input.mouse.move.MouseMoveEvent;
import me.soxey6.engine.main.Game;
import me.soxey6.engine.main.Settings;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.utils.Logger;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

/**
 * The input Manager class is used for managing the input of the users peripherals. They simply check to see if there are changes, if so, trigger events that can be hooked.
 * Use is fairly basic but this really shouldn't be touched as it will do what you want it to do.
 * @author pchilds
 *
 */
public class InputManager implements Runnable{
	private static InputManager inputManager;
	/**
	 *  Where we store keys (Even released ones)
	 */
	private ArrayList<Key> keys;

	private GLFWKeyCallback   keyCallback;
	private GLFWCursorPosCallback   mousePosCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	
	public boolean shouldStop = false;
	
	/**
	 * Where the current mouse position is stored.
	 */
	private MousePos mousePos;

	/**
	 * This is where we store the LAST 
	 */
	private Click mouseClick;
	
	/**
	 * This is the DeltaScrolled in this tick
	 */
	private int scroll = 0;
	
	
	public InputManager()
	{
		mouseClick		= null;
		mousePos 		= null;
		keys			= new ArrayList<Key>();
		inputManager	= this;
	}
	
	/**
	 * This function is triggered to update all the input information.
	 */
	public void input()
	{
		
		// Sleep for 1 second (Due to threaded operations we need to wait for the window to be created)
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
		// Setup callbacks
		setupCallbacks();
		
		// Keep looping and keep this thread alive
		while(!shouldStop)
		{
			//TODO: Add clipboard checking here
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Logger.getLogger().log(Logger.getLogger().INFO, "Input manager thread closing");
		destroy();
		Logger.getLogger().log(Logger.getLogger().INFO, "Input manager thread closed");
	}

	private void setupCallbacks()
	{
		setupKeyboardCallbacks();
		setupMouseCallbacks();
		//TODO: add a joystick callback
	}
	
	public void setupKeyboardCallbacks()
	{
		// Create a callback in GLFW
		glfwSetKeyCallback(Game.getGame().getDisplay().getWindowHandler(), keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
            	
            	//Dispatch an event
            	EventManager.getEventManager().dispatch(new KeyEvent(new Key(key, action)));
            	
            	// Hardcode the debug key
                if ( key == GLFW_KEY_F8 && action == GLFW_RELEASE && !Game.getGame().getPerformanceThread().isAlive() && !Game.getGame().getPerformanceMonitor().shouldStop)
                	Game.getGame().getPerformanceThread().start();
            }
        });
	}
	
	public void setupMouseCallbacks()
	{
		glfwSetCursorPosCallback(Game.getGame().getDisplay().getWindowHandler(), mousePosCallback = new GLFWCursorPosCallback()
		{
			@Override
			public void invoke(long window, double xpos, double ypos) {
				try {
					EventManager.getEventManager().dispatch(new MouseMoveEvent((float)xpos/Settings.getSettings().getSetting("RESOLUTION_X").getValueInt(), (float)ypos/Settings.getSettings().getSetting("RESOLUTION_Y").getValueInt()));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		glfwSetMouseButtonCallback(Game.getGame().getDisplay().getWindowHandler(), mouseButtonCallback = new GLFWMouseButtonCallback()
		{
			@Override
			public void invoke(long window, int button, int action, int mods) {
				DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
				DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
				glfwGetCursorPos(window, b1, b2);
				try {
					EventManager.getEventManager().dispatch(new MouseButtonEvent((float)b1.get(0)/Settings.getSettings().getSetting("RESOLUTION_X").getValueInt(),(float)b2.get(0)/Settings.getSettings().getSetting("RESOLUTION_Y").getValueInt(), button, action));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void destroy()
	{
		keyCallback.release();
		mousePosCallback.release();
		mouseButtonCallback.release();
	}
	
	public ArrayList<Key> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<Key> keys) {
		this.keys = keys;
	}

	public MousePos getMousePos() {
		return mousePos;
	}

	public void setMousePos(MousePos mousePos) {
		this.mousePos = mousePos;
	}

	public Click getMouseClick() {
		return mouseClick;
	}

	public void setMouseClick(Click mouseClick) {
		this.mouseClick = mouseClick;
	}

	public int getScroll() {
		return scroll;
	}

	public void setScroll(int scroll) {
		this.scroll = scroll;
	}

	public static InputManager getInputManager() {
		return inputManager;
	}

	public static void setInputManager(InputManager inputManager) {
		InputManager.inputManager = inputManager;
	}

	@Override
	public void run() {
		input();
	}
}

