package me.soxey6.engine.objects.render;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_HIDDEN;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.ByteBuffer;

import me.soxey6.engine.events.ticks.render.RenderEvent;
import me.soxey6.engine.main.Game;
import me.soxey6.engine.main.Settings;
import me.soxey6.engine.managers.event.EventManager;
import me.soxey6.engine.objects.Setting;
import me.soxey6.utils.Logger;

import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
/**
 * Wrapper for rendering
 */
public class Display implements Runnable
{
	private static Display display;
	
	private long windowHandler;
	
	boolean shouldStop = false;
	
	private String name;
	
	private boolean fullscreen;
		
	private float sizeX;
	private float sizeY;
	

	public Display(String s, boolean fullscreen)
	{
		display = this;
		this.fullscreen = fullscreen;
		this.name = s; 
	}
	
	public int shouldClose()
	{
		return glfwWindowShouldClose(windowHandler);
	}
	
	public void render()
	{
		Game.getGame().setDisplay(this);
		// Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( glfwInit() != GL11.GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
 
        // Configure our window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); // the window will be resizable
 
 
        // Create the window
        
 
        // Get the resolution of the primary monitor
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        //Create a window.
        sizeX = GLFWvidmode.width(vidmode);
        sizeY = GLFWvidmode.height(vidmode);
        Settings.getSettings().addSetting(new Setting<Boolean>("FULLSCREEN", fullscreen));
        Settings.getSettings().addSetting(new Setting<Integer>("RESOLUTION_X", fullscreen?GLFWvidmode.width(vidmode):800));
        Settings.getSettings().addSetting(new Setting<Integer>("RESOLUTION_Y", fullscreen?GLFWvidmode.height(vidmode):600));
        windowHandler = glfwCreateWindow(fullscreen?GLFWvidmode.width(vidmode):800, fullscreen?GLFWvidmode.height(vidmode):600, name, fullscreen? glfwGetPrimaryMonitor():NULL, NULL);
        if ( windowHandler == NULL )
            throw new RuntimeException("Failed to create the GLFW window");
        
        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandler);
        GLContext.createFromCurrent();
        // Enable v-sync
        glfwSwapInterval(1);
 
        // Make the window visible
        glfwShowWindow(windowHandler);
        
        // Setup OpenGL for use
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
		 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
 
        	GL11.glEnable(GL11.GL_BLEND);
        	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
 
        	GL11.glViewport(0,0,fullscreen?GLFWvidmode.width(vidmode):800, fullscreen?GLFWvidmode.height(vidmode):600);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, fullscreen?GLFWvidmode.width(vidmode):800, fullscreen?GLFWvidmode.height(vidmode):600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
    	glfwSetInputMode(windowHandler, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
    	
        GLContext.createFromCurrent();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		while(glfwWindowShouldClose(windowHandler) == GL_FALSE && !shouldStop)
		{
			preRender();
			Game.getGame().getPerformanceMonitor().fps++;
			EventManager.getEventManager().dispatch(new RenderEvent());
			postRender();
			Game.getGame().getPerformanceMonitor().ips++;
		}
		Game.getGame().threadCleanup();
	}
	
	public void stop()
	{
		shouldStop = true;
	}
	
	public void preRender()
	{
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

	}
	
	public void postRender()
	{
		glfwSwapBuffers(windowHandler); // swap the color buffers
		glfwPollEvents(); // Poll for events (Must be done on this thread sadly)
	}
	
	/**
	 * @deprecated
	 */
	public void update()
	{
	}
	
	public void destroy() throws Throwable
	{
		Logger.getLogger().log(Logger.getLogger().INFO, "Render thread closing");
        glfwDestroyWindow(windowHandler);
        glfwTerminate();
		Logger.getLogger().log(Logger.getLogger().INFO, "Render thread closed");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sx) {
		this.sizeX = sx;
	}

	public float getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sy) {
		this.sizeY = sy;
	}

	public static Display getDisplay() {
		return display;
	}

	public static void setDisplay(Display display) {
		Display.display = display;
	}

	@Override
	public void run() {
		render();
	}

	public long getWindowHandler() {
		return windowHandler;
	}

	public void setWindowHandler(long windowHandler) {
		this.windowHandler = windowHandler;
	}

	public boolean isShouldStop() {
		return shouldStop;
	}

	public void setShouldStop(boolean shouldStop) {
		this.shouldStop = shouldStop;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}
}
