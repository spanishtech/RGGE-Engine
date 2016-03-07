package me.soxey6.engine.managers.module;

import java.util.ArrayList;

import me.soxey6.engine.main.Wrapper;

public class ModuleManager{

	public static ModuleManager moduleManager;
	
	public ArrayList<Module> modules = new ArrayList<Module>();
	
	public ModuleManager(){
		moduleManager = this;
	}
}
