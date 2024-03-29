package com.briup.apps.cms.utils;
/**
 * 自定义异常
 * @author htj
 *
 */
public class CustomerException extends RuntimeException {

	public CustomerException() {
		super();
	}

	public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerException(String message) {
		super(message);
	}

	public CustomerException(Throwable cause) {
		super(cause);
	}
	
}
