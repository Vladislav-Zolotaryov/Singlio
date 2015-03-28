package com.wz.singlio.nodefault;

import com.wz.singlio.Singleton;

@Singleton(lazy = true)
public class NoDefaultConstructor {
	
	public NoDefaultConstructor(int one, int two, String three) {}
	
}
