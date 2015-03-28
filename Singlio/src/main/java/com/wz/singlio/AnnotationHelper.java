package com.wz.singlio;

import java.lang.annotation.Annotation;

public class AnnotationHelper {

	private AnnotationHelper() {}
	
	static boolean checkAnnotations(Class<?> classy) {
		Annotation[] annotations = classy.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Singleton) {
				return true;
			}
		}
		return false;
	}
	
	static Annotation findAnnotation(Class<?> classy, Class<? extends Annotation> targetAnnotation) {
		Annotation[] annotations = classy.getAnnotations();
		for (Annotation annotation : annotations) {
			if (targetAnnotation.isInstance(annotation)) {
				return annotation;
			}
		}
		return null;
	}

}
