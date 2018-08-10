package com.zyyan.ms.term.exception;

public class LogFepMessageFailedException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public LogFepMessageFailedException(String msg, Exception e) {
		super(msg, e);
	}

}
