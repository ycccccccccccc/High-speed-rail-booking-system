package com.exception;

public class OrderInfoException extends Exception {

	public OrderInfoException() {
		super("�d�L�ŦX������q��!�нT�{���d�ߤ���T�O�_���T");
	}

	public OrderInfoException(String message) {
		super(message);
	}

	public OrderInfoException(Throwable cause) {
		super(cause);
	}

	public OrderInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
