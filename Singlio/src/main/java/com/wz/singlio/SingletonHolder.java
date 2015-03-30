package com.wz.singlio;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SingletonHolder {

	private Class<?> classy;
	private Object instance;

	SingletonHolder(Class<?> classy) {
		this.classy = classy;
		handleClass();
	}

	private void handleClass() {
		Singleton annotation = (Singleton) AnnotationHelper.findAnnotation(classy, Singleton.class);
		if (!annotation.lazy()) {
			instance = createInstance();
		}
	}
	
	Object getInstance() {
		if (instance == null) {
			instance = createInstance();
		}
		return instance;
	}
	
	boolean isInstanced() {
		return instance != null;
	}
	
	private Object createPrivateInstance(Constructor<?> constructor) {
		try {
			constructor.setAccessible(true);
			return createInstanceSilent(constructor);
		} finally {
			constructor.setAccessible(false);
		}
	}
	
	private Object createInstanceSilent(Constructor<?> constructor) {
		try {
			return constructor.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} 
	}
	
	private Object createInstance() {
		try {
			Constructor<?> constructor = classy.getDeclaredConstructor();
			if (constructor.isAccessible()) {
				return createInstanceSilent(constructor);
			} else {
				return createPrivateInstance(constructor);
			}
		} catch (NoSuchMethodException e) {
			String message = String.format("Class '%s' should have a default constructor to be Singleton", classy.getSimpleName());
			throw new NoDefaultConstructorException(message);
		}
	}
	
	@Override
	public int hashCode() {
 		return 41 * classy.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SingletonHolder)) {
			return false;
		}
		
		SingletonHolder that = (SingletonHolder) obj;
		if (!classy.equals(that.classy)) {
			return false;				
		}
		return true;
	}

	
}
