package me.soxey6.engine.objects.gui;

/**
 * A class for rendering text, it defaults to centered text
 * 
 * @author Spanish
 *
 */
public class Label extends GuiElement {
	private int size;
	private String text;
	private boolean centered;

	public Label(String name, Gui gui, float posX, float posY, String text,
			int size) {
		super(name, gui, posX, posY, 0, 0);
		this.size = size;
		this.text = text;
		this.centered = true;
	}

	public Label(String name, Gui gui, float posX, float posY, String text,
			int size, boolean centered) {
		super(name, gui, posX, posY, 0, 0);
		this.size = size;
		this.text = text;
		this.centered = centered;
	}

	/**
	 * @return the size of the font to be used
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the text that this label renders
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param the
	 *            size of the font to set.
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @param the
	 *            text to change the text to
	 */
	public void setText(String text) {
		this.text = text;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}

}
