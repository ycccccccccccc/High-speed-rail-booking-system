package com.exception;

public class UserException extends Exception {

	public UserException() {
		super("�d�L�������Ҧr��/�@�Ӹ��X!�нT�{��J�O�_���T�A�Ϊ̿�JR��^");
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
