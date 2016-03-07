package me.soxey6.engine.objects.gui;

import me.soxey6.engine.events.input.mouse.button.MouseButtonEvent;
import me.soxey6.engine.events.input.mouse.move.MouseMoveEvent;
import me.soxey6.engine.events.ticks.render.RenderEvent;
import me.soxey6.engine.main.Wrapper;
import me.soxey6.engine.managers.event.objects.listener.EventListener;

/**
 * The basic GuiElement class used for creating any UI element
 */
public class GuiElement extends Wrapper {
	private String name;
	private Gui gui;
	private float posX;
	private float posY;
	private float sizeX;
	private float sizeY;
	private boolean hovered;
	private boolean clicked;
	private boolean released;

	private EventListener<MouseMoveEvent> mouseMoveListener;// = new EventListener<MouseMoveEvent	>(MouseMoveEvent.class);
	private EventListener<MouseButtonEvent> mouseButtonListener;
	private EventListener<RenderEvent> renderListener;

	/**
	 * The basic GuiElement class used for creating any UI element
	 * 
	 * @param name
	 * @param gui
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 */
	public GuiElement(String name, Gui gui, float posX, float posY,
			float sizeX, float sizeY) {
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.gui = gui;
		this.hovered = false;
		this.clicked = false;

		getGui().getGuiElements().add(this);

		getEventManager()
				.addListener(
						this.mouseMoveListener = new EventListener<GuiElement, MouseMoveEvent>(
								this, new MouseMoveEvent(0, 0)) {

							@Override
							public void onEvent(MouseMoveEvent event) {

								if (event.getX() >= posX
										&& event.getX() <= posX + sizeX
										&& event.getY() >= posY
										&& event.getY() <= posY + sizeY) {
									hovered = true;
									onHover(event.getX(), event.getY());
								} else {
									hovered = false;
								}
							}
						});

		getEventManager().addListener(
				new EventListener<GuiElement, RenderEvent>(this,
						new RenderEvent()));

	}

	/*
	 * @Override public void onRender(RenderEvent keyUpEvent) { render();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Override public void onMouseButton(MouseButtonEvent mouseButtonEvent) {
	 * 
	 * 
	 * if(isHovered()&&mouseButtonEvent.getAction()==GLFW_PRESS) { clicked =
	 * true; released = false; onClick(mouseButtonEvent.getX(),
	 * mouseButtonEvent.getY()); }else { clicked = false; released = true;
	 * onRelease(mouseButtonEvent.getX(), mouseButtonEvent.getY()); }
	 * 
	 * }
	 */

	/**
	 * Triggered when the mouse clicks within the button
	 * 
	 * @param mousePosX
	 * @param mousePosY
	 *            To be overridden only
	 */
	public void onClick(float mousePosX, float mousePosY) {

	}

	/**
	 * Triggered when the mouse hovers within the button
	 * 
	 * @param mousePosX
	 * @param mousePosY
	 *            To be overridden only
	 */
	public void onHover(float x, float y) {
	}

	/**
	 * Triggered when the mouse releases
	 * 
	 * @param mousePosX
	 * @param mousePosY
	 *            To be overridden only
	 */
	public void onRelease(float x, float y) {

	}

	public void render() {

	}

	/**
	 * @return the gui
	 */
	public Gui getGui() {
		return gui;
	}

	/**
	 * @param gui
	 *            the gui to set
	 */
	public void setGui(Gui gui) {
		this.gui = gui;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the posX
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * @return the posY
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * @return the sizeX
	 */
	public float getSizeX() {
		return sizeX;
	}

	/**
	 * @return the sizeY
	 */
	public float getSizeY() {
		return sizeY;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param posX
	 *            the posX to set
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	 * @param posY
	 *            the posY to set
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}

	/**
	 * @param sizeX
	 *            the sizeX to set
	 */
	public void setSizeX(float sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * @param sizeY
	 *            the sizeY to set
	 */
	public void setSizeY(float sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * @return the hovered
	 */
	public boolean isHovered() {
		return hovered;
	}

	/**
	 * @return the clicked
	 */
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * @param hovered
	 *            the hovered to set
	 */
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
	}

	/**
	 * @param clicked
	 *            the clicked to set
	 */
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	public EventListener<GuiElement, MouseMoveEvent> getMouseMoveListener() {
		return mouseMoveListener;
	}

	public void setMouseMoveListener(
			EventListener<GuiElement, MouseMoveEvent> mouseMoveListener) {
		this.mouseMoveListener = mouseMoveListener;
	}

	public EventListener<GuiElement, MouseButtonEvent> getMouseButtonListener() {
		return mouseButtonListener;
	}

	public void setMouseButtonListener(
			EventListener<GuiElement, MouseButtonEvent> mouseButtonListener) {
		this.mouseButtonListener = mouseButtonListener;
	}

	public EventListener<GuiElement, RenderEvent> getRenderListener() {
		return renderListener;
	}

	public void setRenderListener(
			EventListener<GuiElement, RenderEvent> renderListener) {
		this.renderListener = renderListener;
	}

}
