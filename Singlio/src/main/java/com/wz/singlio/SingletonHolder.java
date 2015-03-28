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
	
	private Object createInstance() {
		boolean wasAccessible = false;
		Constructor<?> constructor = null;
		try {
			constructor = classy.getDeclaredConstructor();
			
			wasAccessible = constructor.isAccessible();
			if (constructor.isAccessible()) {
				return constructor.newInstance();
			} else {
				constructor.setAccessible(true);
				return constructor.newInstance();
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			String message = String.format("Class '%s' should have a default constructor to be Singleton", classy.getSimpleName());
			throw new NoDefaultConstructorException(message);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} finally {
			if (constructor != null) {
				constructor.setAccessible(wasAccessible);
			}
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
