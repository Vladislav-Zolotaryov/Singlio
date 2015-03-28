package com.wz.singlio.lazy;

import com.wz.singlio.Singleton;

@Singleton(lazy = true)
public class LazySubject {

	private static int instances;
	
	public LazySubject() {
		instances++;
	}
	
	public static int getInstances() {
		return instances;
	}
	
}
