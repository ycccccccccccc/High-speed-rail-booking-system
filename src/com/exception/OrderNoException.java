package com.exception;

public class OrderNoException extends Exception {

	public OrderNoException() {
		super("�d�L���q��s��!�нT�{��J���q��s���O�_���T�A�Ϊ̿�JR��^");
	}

	public OrderNoException(String message) {
		super(message);
	}

	public OrderNoException(Throwable cause) {
		super(cause);
	}

	public OrderNoException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
