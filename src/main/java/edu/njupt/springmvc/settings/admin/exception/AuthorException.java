package edu.njupt.springmvc.settings.admin.exception;

public class AuthorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthorException() {
		super();
	}

	public AuthorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthorException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorException(String message) {
		super(message);
	}

	public AuthorException(Throwable cause) {
		super(cause);
	}

}
