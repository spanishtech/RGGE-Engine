package me.soxey6.engine.objects.game;

/**
 * A way of dealing with version
 * 
 * @author soxey
 *
 */
public class Version {

	// Totally not nicked from LWJGL I promise
	/** Current version of the game. */
	public final int VERSION_MAJOR;
	public final int VERSION_MINOR;
	public final int VERSION_REVISION;

	/** The development state of the current build. */
	public final BuildType BUILD_TYPE;

	public Version(int VERSION_MAJOR, int VERSION_MINOR, int VERSION_REVISION,
			BuildType BUILD_TYPE) {
		this.VERSION_MAJOR = VERSION_MAJOR;
		this.VERSION_MINOR = VERSION_MINOR;
		this.VERSION_REVISION = VERSION_REVISION;

		this.BUILD_TYPE = BUILD_TYPE;
	}

	/** Returns the game version. */
	public String getVersion() {
		return String.valueOf(VERSION_MAJOR) + '.' + VERSION_MINOR + '.'
				+ VERSION_REVISION + BUILD_TYPE.postfix;
	}

	/** The development state of the current build. */
	public enum BuildType {
		/** Work in progress, unstable. */
		ALPHA("a"),
		/** Feature complete, unstable. */
		BETA("b"),
		/** Feature complete, stable, official release. */
		STABLE("");

		final String postfix;

		BuildType(String postfix) {
			this.postfix = postfix;
		}
	}
}
