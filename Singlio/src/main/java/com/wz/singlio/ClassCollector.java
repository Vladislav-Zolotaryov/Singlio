package com.wz.singlio;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class ClassCollector {
	
	private List<Class<?>> classes;
	private String packageName = "";
	
	public List<Class<?>> collectClasses(File directory) {
		classes = new LinkedList<Class<?>>();
		traverse(directory);
		return classes;
	}
	
	private void traverse(File directory) {
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				packageName += file.getName() + ".";
				traverse(file);
			} else if (file.getName().endsWith(".class")) {
				try {
					Class<?> classy = Class.forName(packageName + file.getName().replace(".class", ""));
					classes.add(classy);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
