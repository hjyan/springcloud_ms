package com.zyyan.ms.term.exception;

public class UnknownActionException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UnknownActionException(String msg, Exception e) {
		super(msg, e);
	}

}
