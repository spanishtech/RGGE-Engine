package me.soxey6.engine.managers.module;

import java.util.ArrayList;

import me.soxey6.utils.Version;

public class ModuleMeta {

	// The name of the module
	final public String NAME;

	// The current version
	final public Version VERSION;

	// What order this shall be loaded in
	final public int LOAD_ORDER;

	// What modules are required to work.
	final public ArrayList<ModuleMeta> REQUIRED_MODULES;

	public ModuleMeta(String name, Version version, int loadOrder, ArrayList<ModuleMeta> requiredModules) {
		NAME = name;
		VERSION = version;
		LOAD_ORDER = loadOrder;
		REQUIRED_MODULES = requiredModules;
	}

}
