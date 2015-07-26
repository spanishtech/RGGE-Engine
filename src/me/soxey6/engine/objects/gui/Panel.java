package me.soxey6.engine.objects.gui;

import org.lwjgl.opengl.GL11;

public class Panel extends GuiElement {

	private String textureLocation;

	public Panel(String name, Gui gui, float posX, float posY, float sizeX,
			float sizeY, String textureLocation) {
		super(name, gui, posX, posY, sizeX, sizeY);
		this.textureLocation = textureLocation;
	}

	@Override
	public void render() {
		GL11.glColor4f(0.5f, 0.5f, 1.0f, 1f);
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(0, 0);
		GL11.glVertex2f(50, 0);
		GL11.glVertex2f(50, 50);
		GL11.glVertex2f(0, 50);
		GL11.glEnd();

	}

	public String getTextureLocation() {
		return textureLocation;
	}

	public void setTextureLocation(String textureLocation) {
		this.textureLocation = textureLocation;
	}

}
