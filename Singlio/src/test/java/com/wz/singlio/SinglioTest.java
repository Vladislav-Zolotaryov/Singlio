package com.wz.singlio;

import org.junit.Assert;
import org.junit.Test;

import com.wz.singlio.eager.EagerSubject;
import com.wz.singlio.lazy.LazySubject;
import com.wz.singlio.nodefault.NoDefaultConstructor;
import com.wz.singlio.privateer.PrivateConstructor;

public class SinglioTest {
	
	@Test
	public void testRootTraversal() {
		Singlio singleo = new Singlio().initialize();
		Assert.assertEquals("Should be 2 classes loaded", 2, singleo.classesLoaded()); 
	}

	@Test
	public void testNonExistingSingleton() {
		Singlio singleo = new Singlio("com.wz.singlio.eager").initialize();
		Assert.assertNull(singleo.get(Object.class));
	}
	
	@Test
	public void testEagerSubject() {
		Singlio singleo = new Singlio("com.wz.singlio.eager").initialize();
		
		EagerSubject eagerSubject = singleo.get(EagerSubject.class);
		Assert.assertNotNull(eagerSubject);
		Assert.assertTrue("Should return the same object", eagerSubject == singleo.get(EagerSubject.class));
	}
	
	@Test(expected=NoDefaultConstructorException.class)
	public void testNoDefaultConstructor() {
		Singlio singleo = new Singlio("com.wz.singlio.nodefault").initialize();
		
		NoDefaultConstructor noDefaultConstructor = singleo.get(NoDefaultConstructor.class);
		Assert.assertNotNull(noDefaultConstructor);
		Assert.assertTrue("Should return the same object", noDefaultConstructor == singleo.get(NoDefaultConstructor.class));
	}
	
	@Test
	public void testPrivateConstructor() {
		Singlio singleo = new Singlio("com.wz.singlio.privateer").initialize();
		
		PrivateConstructor privateConstructor = singleo.get(PrivateConstructor.class);
		Assert.assertNotNull(privateConstructor);
		Assert.assertTrue("Should return the same object", privateConstructor == singleo.get(PrivateConstructor.class));
	}
	
	@Test
	public void testLazySubject() {
		Singlio singleo = new Singlio("com.wz.singlio.lazy").initialize();
		Assert.assertEquals("Should be 0 instances at the start", 0, LazySubject.getInstances());
		
		LazySubject one = singleo.get(LazySubject.class);
		LazySubject two = singleo.get(LazySubject.class);
		LazySubject three = singleo.get(LazySubject.class);
		
		Assert.assertNotNull(one);
		Assert.assertNotNull(two);
		Assert.assertNotNull(three);
		
		Assert.assertTrue("Should return the same object", one == two);
		Assert.assertTrue("Should return the same object", two == three);
		
		Assert.assertEquals("Should be 1 instance at the end", 1, LazySubject.getInstances());
	}
	
}
