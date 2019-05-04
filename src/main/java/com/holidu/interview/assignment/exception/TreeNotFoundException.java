package com.holidu.interview.assignment.exception;

//custom student exception to throw exception if student not found
public class TreeNotFoundException extends RuntimeException {

	public TreeNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TreeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TreeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TreeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TreeNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
