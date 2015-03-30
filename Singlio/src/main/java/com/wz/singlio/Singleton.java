package com.wz.singlio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks your class for instantiation in Singlio
 * @lazy instantiation by default
 */ 
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton {
	public boolean lazy() default false;
}
