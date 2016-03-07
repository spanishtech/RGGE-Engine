package me.soxey6.engine.managers.setting;

import me.soxey6.engine.main.Wrapper;

/**
 * This class is pretty terrible I really need to rewrite it.
 *
 */
public class Setting<T> extends Wrapper {

	private String name;
	private T value;
	private boolean enabled;

	public Setting(String name, T value) {
		this.name = name;
		this.value = value;
		this.enabled = true;
	}

	public void update(T value) {
		this.value = value;
	}

	public int getValueInt() throws Throwable {
		if (value instanceof Integer)
			return (int) value;
		// Will throw an exception
		return new Integer(null);
	}

	public String getValueString() {
		if (value instanceof Integer)
			return (String) value;
		return null;
	}

	public Boolean getValueBool() {
		if (value instanceof Boolean)
			return (Boolean) value;
		return null;
	}

	public Float getValueFloat() {
		if (value instanceof Float)
			return (Float) value;
		return null;
	}

	public Double getValueDouble() {
		if (value instanceof Double)
			return (Double) value;
		return null;
	}

	public Long getValueLong() {
		if (value instanceof Long)
			return (Long) value;
		return null;
	}

	public void toggle() {
		enabled = !enabled;
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}
