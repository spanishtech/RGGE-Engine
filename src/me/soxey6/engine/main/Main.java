package me.soxey6.engine.main;

import java.io.File;

/**
 * The entry point of the game that then sets up the game object making almost
 * everything non-static.
 * 
 * @author Pat Childs || Soxey6
 * @version Dev-0.0.2
 */
public class Main {
	private static Engine game;
	private final static String gameName = "";

	private static Repairer repairer;

	public static void main(String[] args) {
		if (args.length > 0)
			repair();

		System.setProperty("org.lwjgl.librarypath",
				new File("native/").getAbsolutePath());
		setGame(new Engine(gameName));

	}

	public static Engine getGame() {
		return game;
	}

	public static void setGame(Engine game) {
		Main.game = game;
	}

	private static void repair() {
		(repairer = new Repairer()).startRepairs();
		if (repairer.isSuccessful()) {
			System.out.println("[Repairer] Program was repaired successfully");
			repairer.markSuccessful();
			System.exit(0);
		} else {
			System.out
					.println("[Repairer] Program was not repaired. Please contact the dev for further assistance");
			repairer.dump();
			System.exit(-1);
		}

	}

}