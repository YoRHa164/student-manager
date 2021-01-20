package edu.njupt.springmvc.settings.student.exception;

public class StudentScoreQueryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentScoreQueryException() {
		super();
	}

	public StudentScoreQueryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StudentScoreQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentScoreQueryException(String message) {
		super(message);
	}

	public StudentScoreQueryException(Throwable cause) {
		super(cause);
	}

}
