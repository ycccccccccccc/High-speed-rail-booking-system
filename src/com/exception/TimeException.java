package com.exception;

public class TimeException extends Exception {

	public TimeException() {
		super("��J�ɶ����~�A�Э��s��J�ο�JR��^");
	}

	public TimeException(String message) {
		super(message);
	}

	public TimeException(Throwable cause) {
		super(cause);
	}

	public TimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
