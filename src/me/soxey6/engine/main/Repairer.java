package me.soxey6.engine.main;

import java.util.ArrayList;


import me.soxey6.engine.objects.Setting;
@SuppressWarnings("rawtypes")
public class Repairer {
	private boolean successfulRepairBefore;
	
	private boolean successful;
	
	private ArrayList<Setting> defaultSettings;
	
	public Repairer()
	{
		//TODO: add default settings to the array
		//TODO: Setup values
	}
	
	public void startRepairs()
	{
		//TODO: Start repairing
	}
	
	public void markSuccessful()
	{
		//TODO: Mark successful
	}
	
	public void dump()
	{
		//TODO: Produce a report on why it is dying.
	}

	public boolean isSuccessfulRepairBefore() {
		return successfulRepairBefore;
	}

	public void setSuccessfulRepairBefore(boolean successfulRepairBefore) {
		this.successfulRepairBefore = successfulRepairBefore;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public ArrayList<Setting> getDefaultSettings() {
		return defaultSettings;
	}

	public void setDefaultSettings(ArrayList<Setting> defaultSettings) {
		this.defaultSettings = defaultSettings;
	}
}
