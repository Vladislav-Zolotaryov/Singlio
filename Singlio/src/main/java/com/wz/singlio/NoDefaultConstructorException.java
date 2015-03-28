package com.wz.singlio;

public class NoDefaultConstructorException extends RuntimeException {

	private static final long serialVersionUID = 2191094453824079323L;

	public NoDefaultConstructorException() {
		super();
	}

	public NoDefaultConstructorException(String message) {
		super(message);
	}

	public NoDefaultConstructorException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
