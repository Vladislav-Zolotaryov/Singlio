package com.wz.singlio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/**
 * Marks your class for instantiation in Singlio
 * @lazy instantiation by default
 */ 
public @interface Singleton {
	public boolean lazy() default false;
}
