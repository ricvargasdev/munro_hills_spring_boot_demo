package com.xdesign.munro.exception;

public class MinGreaterThanMaxException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MinGreaterThanMaxException() {
        super("Error: 'minHeight' is greater than 'maxHeight'");
    }

}
