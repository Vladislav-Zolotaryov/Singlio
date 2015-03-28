package com.wz.singlio;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Singlio {

	private Map<String, SingletonHolder> repository = new ConcurrentHashMap<String, SingletonHolder>();
	private String packageName;

	public Singlio() {
	}

	public Singlio(String packageName) {
		this.packageName = packageName;
	}

	/*
	 * @throws NoDefaultConstructorException if annotated class does not have a
	 * default constructor
	 */
	public Singlio initialize() {
		List<Class<?>> classes = new ClassCollector(packageName).collectClasses();
		for (Class<?> classy : classes) {
			if (AnnotationHelper.checkAnnotations(classy)) {
				repository.put(classy.getSimpleName(), new SingletonHolder(classy));
			}
		}
		return this;
	}

	public <T> T get(Class<?> classy) {
		return get(classy.getSimpleName());
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String className) {
		if (repository.get(className) == null) {
			return null;
		} else {
			return (T) repository.get(className).getInstance();
		}
	}

	public int classesLoaded() {
		int size = 0;
		for (SingletonHolder holder : repository.values()) {
			if (holder.isInstanced()) {
				size++;
			}
		}
		return size;
	}

}
