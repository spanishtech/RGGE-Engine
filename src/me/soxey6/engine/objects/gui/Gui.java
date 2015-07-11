package me.soxey6.engine.objects.gui;

import java.util.ArrayList;

import me.soxey6.engine.managers.scene.Scene;
/**
 * This is a gui class that stores Gui elements and manages them to a small extent, every scene should have one.
 * @author pchilds
 *
 */
public class Gui {
	
	public Gui(Scene scene)
	{
		this.scene=scene;
	}
	
	private Scene scene;
	private ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	
	
	/**
	 * @return the guiElements
	 */
	public ArrayList<GuiElement> getGuiElements()
	{
		return guiElements;
	}

	/**
	 * @param guiElements the guiElements to set
	 */
	public void setGuiElements(ArrayList<GuiElement> guiElements)
	{
		this.guiElements = guiElements;
	}

	/**
	 * @return the scene
	 */
	public Scene getScene()
	{
		return scene;
	}

	/**
	 * @param scene the scene to set
	 */
	public void setScene(Scene scene)
	{
		this.scene = scene;
	}


}
