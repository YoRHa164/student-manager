package edu.njupt.springmvc.settings.student.exception;

public class StudentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentException() {
	}

	public StudentException(String message) {
		super(message);
	}

	public StudentException(Throwable cause) {
		super(cause);
	}

	public StudentException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
