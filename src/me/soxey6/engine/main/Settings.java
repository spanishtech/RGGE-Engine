package me.soxey6.engine.main;

import java.util.HashMap;

import me.soxey6.engine.objects.Setting;
import me.soxey6.utils.Logger;

/**
 * This is where global variables should be stored and altered.
 * @author pchilds
 *
 */
@SuppressWarnings("rawtypes")
public class Settings{
	// A hashmap of the settings for easy searching and retrieving of them
	private HashMap<String, Setting> settingsList = new HashMap<String, Setting>();
	private static Settings settings;
	
	public Settings()
	{
		settings=this;
	}
	
	/**
	 * Add a setting to the hashmap for easy retrieval 
	 * @param String name - The name that the setting will be retrieved with
	 * @param Setting setting - The setting to store
	 */
	public void addSetting(String name,Setting setting)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Adding setting "+name);
		settingsList.put(name, setting);
	}
	
	/**
	 * Add a setting to the hashmap for easy retrieval 
	 * @param Setting setting - The setting to store
	 */
	public void addSetting(Setting setting)
	{
		Logger.getLogger().log(Logger.getLogger().DEBUG, "Adding setting "+setting.getName());
		settingsList.put(setting.getName(), setting);
	}
	
	/**
	 * Retrieves a setting by name
	 * @param String name - The name that the setting will be retrieved
	 */
	public Setting getSetting(String name)
	{
		return settingsList.get(name);
	}

	
	/**
	 * This function will save all settings currently loaded
	 */
	public void saveSettings()
	{
		//TODO: Add the ability to save settings with GSON in a json format.
	}
	
	
	/**
	 * This function will load all settings saved
	 */
	public void LoadSettings()
	{
		//TODO: Add the ability to load settings with GSON in a json format.
	}
	

	public HashMap<String, Setting> getSettingsList() {
		return settingsList;
	}

	public void setSettingsList(HashMap<String, Setting> settingsList) {
		this.settingsList = settingsList;
	}

	public static Settings getSettings() {
		return settings;
	}

	public static void setSettings(Settings settings) {
		Settings.settings = settings;
	}
	
}
