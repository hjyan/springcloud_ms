package com.zyyan.ms.term.exception;

public class MqttClientDeadException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MqttClientDeadException(String msg, Exception e) {
		super(msg, e);
	}
	
}
