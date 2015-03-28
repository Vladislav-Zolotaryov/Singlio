package com.wz.singlio;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Singlio {

	private Map<String, Object> repository = new ConcurrentHashMap<String, Object>();
	
	public Singlio initialize() {
		try {
			URL resources = getClass().getClassLoader().getResource("");
			File root = new File(resources.getFile());
			
			List<Class<?>> classes = new ClassCollector().collectClasses(root);
			for (Class<?> classy : classes) {
				Annotation[] annotations = classy.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof Singleton) {
						repository.put(classy.getSimpleName(), classy.newInstance());	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
		
	@SuppressWarnings("unchecked")
	public <T> T get(String className) {
		return (T) repository.get(className);
	}
	
	public static void main(String[] args) {
		Singlio singlio = new Singlio().initialize();
		ClassCollector singleton = singlio.get("ClassCollector");
		System.out.println(singleton);
	}
	
}
