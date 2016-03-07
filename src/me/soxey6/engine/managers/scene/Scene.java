package me.soxey6.engine.managers.scene;

import me.soxey6.engine.events.scene.FocusEvent;
import me.soxey6.engine.events.scene.SceneCreateEvent;
import me.soxey6.engine.main.Engine;
import me.soxey6.engine.main.Wrapper;
import me.soxey6.engine.managers.event.objects.listener.EventListener;
import me.soxey6.engine.objects.gui.Gui;

/**
 * Scene class: This class is used to segment code into different levels or
 * screens. This allows things such as menus and multiple levels to be created
 * It is recomended to destroy these objects when not in use and recreate them
 * when you need them You can unfocus and focus them, when they are unfocused to
 * logic, rendering, or input processing is done. NOTICE: Place your code here
 * if it DOES NOT need to be executed at ANY time. These scenes are more
 * efficient then running all the code from the main game class.
 * 
 * @author spanish
 *
 */
public class Scene extends Wrapper {
	private final boolean LIMIT_LOGIC = true;
	private final long LOGIC_INCREMENT_MS = 100;

	private String name;
	private String title;
	private boolean focused;

	private Engine game;
	private Gui gui;

	private long lastLogicTime;

	private EventListener<FocusEvent> focusListener = new EventListener<FocusEvent>(FocusEvent.class, event -> {
		getLogger().log(DEBUG, event.isFocused());
		onFocus(event.isFocused());
	});

	public Scene(String name) {
		// Setup variables
		this.name = name;
		this.title = name;
		this.game = Engine.getEngine();

		this.gui = new Gui(this);
		this.getSceneManager().addScene(this);

		getEventManager().dispatch(new SceneCreateEvent(this));
		// Log the creation
		this.getLogger().log(this.getLogger().DEBUG, "Creating Scene: " + this.getName());
	}

	public void onFocus(boolean focused) {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isFocused() {
		return focused;
	}

	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	public Engine getGame() {
		return game;
	}

	public void setGame(Engine game) {
		this.game = game;
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	public long getLastLogicTime() {
		return lastLogicTime;
	}

	public void setLastLogicTime(long lastLogicTime) {
		this.lastLogicTime = lastLogicTime;
	}

	public boolean isLimitLogic() {
		return LIMIT_LOGIC;
	}

	public long getLogicIncrementMS() {
		return LOGIC_INCREMENT_MS;
	}

	public EventListener<FocusEvent> getFocusListener() {
		return focusListener;
	}

	public void setFocusListener(EventListener<FocusEvent> focusListener) {
		this.focusListener = focusListener;
	}

	public void finalize() throws Throwable {
		getSceneManager().getScenes().remove(this);
		focusListener.finalize();
		super.finalize();
	}

}
