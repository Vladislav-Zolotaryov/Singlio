package com.wz.singlio;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ClassCollector {
	
	private List<Class<?>> classes;
	private String packageName = "";
	
	ClassCollector() {}
	ClassCollector(String packageName) {
		if (packageName != null) {
			this.packageName = packageName + ".";
		}
	}
	
	List<Class<?>> collectClasses() {
		classes = new LinkedList<Class<?>>();
		traverse(packageName, new File(getResources().getFile()));
		return classes;
	}
	
	private void traverse(String packageName, File directory) {
		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				String packageNameInner = packageName + file.getName() + ".";
				traverse(packageNameInner, file);
			} else if (file.getName().endsWith(".class")) {
				try {
					Class<?> classy = Class.forName(packageName + file.getName().replace(".class", ""));
					classes.add(classy);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	private URL getResources() {
		if (packageName == null) {
			return getClass().getClassLoader().getResource("");
		} else {
			return getClass().getClassLoader().getResource(packageName.replace(".", "/"));
		}
	}
	
}
