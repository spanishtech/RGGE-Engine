package me.soxey6.engine.objects.game;

/**
 * Gives basic meta-data about the game.
 * 
 * @author soxey
 *
 */
public class GameMetaData {

	/** The name of the game */
	public final String GAME_NAME;

	/** The order in which this should be loaded */
	public final int LOAD_ORDER;

	/** The version of the game */
	public final Version GAME_VERSION;

	public GameMetaData(String gameName, int loadOrder, Version version) {
		this.GAME_NAME = gameName;
		this.LOAD_ORDER = loadOrder;
		this.GAME_VERSION = version;
	}
}