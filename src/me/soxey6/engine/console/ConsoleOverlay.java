package me.soxey6.engine.console;

public class ConsoleOverlay {

	private static ConsoleOverlay consoleOverlay;

	private int curserLocation;
	private String content;

	public ConsoleOverlay() {
		consoleOverlay = this;
		content = "";
		curserLocation = 0;
	}
	
	public void newLetter(int keyCode) {

	}

	public void newLetter(String keyName) {

	}

	public void delLetter() {

	}

	public void onEnter() {

	}

	public static ConsoleOverlay getConsoleOverlay() {
		return consoleOverlay;
	}

	public static void setConsoleOverlay(ConsoleOverlay consoleOverlay) {
		ConsoleOverlay.consoleOverlay = consoleOverlay;
	}

	public int getCurserLocation() {
		return curserLocation;
	}

	public void setCurserLocation(int curserLocation) {
		this.curserLocation = curserLocation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
