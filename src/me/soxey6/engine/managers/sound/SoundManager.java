package me.soxey6.engine.managers.sound;

public class SoundManager {
	private static SoundManager soundManager;

	public SoundManager() {
		soundManager = this;
	}

	public static SoundManager getSoundManager() {
		return soundManager;
	}

	public static void setSoundManager(SoundManager soundManager) {
		SoundManager.soundManager = soundManager;
	}

}
