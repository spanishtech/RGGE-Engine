package me.soxey6.engine.managers.classloader;

import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoader {
	private static ClassLoader classLoader;
	private URLClassLoader urlClassLoader;
	private URL[] urls;

	public ClassLoader() {
		classLoader = this;
		urlClassLoader = new URLClassLoader(urls);
	}

	public Object loadClassFromFile(URL[] locations, String classPath)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		urlClassLoader = new URLClassLoader(locations);
		Class<?> clazz = urlClassLoader.loadClass(classPath);
		return clazz.newInstance();
	}

	public static ClassLoader getClassLoader() {
		return classLoader;
	}

	public static void setClassLoader(ClassLoader classLoader) {
		ClassLoader.classLoader = classLoader;
	}
}
