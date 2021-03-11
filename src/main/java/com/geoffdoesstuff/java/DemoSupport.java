package com.geoffdoesstuff.java;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * This class supports my "demos", which can be run independently or from the MainDemoApp menu. The code in this class
 * uses reflection to find and execute any demo classes it finds.
 */
public final class DemoSupport {

	/**
	 *   This Regular Expression matches either "file:" at the start of the text or ! anywhere:
	 *
	 *   start of section                (
	 *   match start of text              ^
	 *   match literal                     file:
	 *   end of section                         )
	 *   match part before or the part after     |
	 *   start of section                         (
	 *   match literal                             !
	 *   end of section                             )
	 */
	private static final String REGEX = "(^file:)|(!)";

	/**
	 * Hide the constructor as this is a class of static methods.
	 */
	private DemoSupport() {
	}

	/**
	 * Based on the specified Java package name, we get the "resources" for this package from the class loader and then
	 * seek all the classnames, to return a list of strings.
	 * @param demoClasspath package name
	 * @return all matching class names as a List of Strings, sorted alphabetically
	 */
	public static List<String> getDemoClasses(String demoClasspath) {
		final String demoClassPathSlash = demoClasspath.replace('.', '/');

		List<String> classNames = new ArrayList<>();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Enumeration<URL> resources = classLoader.getResources(demoClassPathSlash);
			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();
				switch (resource.getProtocol()) {
					case "file":
						// classes are being loaded from .class files on the file system
						classNames.addAll(findClassesInDirectory(resource.getFile(), demoClasspath));
						break;
					case "jar":
						// classes have been packaged into a JAR file, so we need to parse it
						classNames.addAll(findClassesInJarFile(resource.getFile().split(REGEX)[1], demoClassPathSlash));
						break;
					default:
						// was not expecting to be here, but if we are, output some information
						System.out.println("Unexpected class loader resource protocol - " + resource.getFile());
						System.out.println("Resource: " + resource.getClass().getName() + " - " + resource.toString());
						System.out.println("  " + resource.getProtocol());
						System.out.println("  " + resource.getPath());
						System.out.println("  " + resource.getFile().getClass().getName());
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classNames.stream().sorted().collect(Collectors.toList());
	}

	/**
	 * First we get the Class object for the specified String className. Then we search for and hopefully find the
	 * "public static void main(String[] args)" method, which we can then just execute.
	 * @param className fully qualified classname
	 */
	public static void executeDemoClass(String className) {
		try {
			Class<?> theClass = Class.forName(className);
			String msg = "Class: " + theClass.getName();
			System.out.println(msg);
			char[] line = new char[msg.length()];
			Arrays.fill(line, 0, line.length, '~');
			System.out.println(line);
			// the main method is static, so we don't need to instantiate the class and make an object
			Method main = theClass.getDeclaredMethod("main", String[].class);
			Object[] params = new Object[1];
			main.invoke(null, params);
			System.out.println(line);
			System.out.println();
		} catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private static List<String> findClassesInDirectory(String dirName, String packageName) {
		List<String> classes = new ArrayList<>();
		File directory = new File(dirName);
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		if (files == null) {
			return classes;
		}
		for (File file : files) {
			if (file.isDirectory()) {
				// this is a recursive call
				classes.addAll(findClassesInDirectory(file.getName(), packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class") && !file.getName().contains("$")) {
				// here we have a class file but without a $ in the name, which usually indicates an inner class
				classes.add(packageName + "." + file.getName().substring(0, file.getName().length() - 6));
			}
		}
		return classes;
	}

	private static List<String> findClassesInJarFile(String jarName, String packageName) {
		List<String> classes = new ArrayList<>();
		try {
			JarFile jar = new JarFile(jarName);
			Enumeration<JarEntry> enumOfJar = jar.entries();
			while (enumOfJar.hasMoreElements()) {
				JarEntry jarEntry = enumOfJar.nextElement();
				if (!jarEntry.isDirectory()) {
					String className = jarEntry.getName();
					if (className.startsWith(packageName)) {
						if (!className.contains("$")) {
							className = className.substring(0, className.length() - 6); //remove ".class" from the end
							className = className.replace('/', '.'); //change / characters to .
							classes.add(className);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classes;
	}
}
