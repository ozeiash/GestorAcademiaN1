package com.academia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraDeNegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RegraDeNegocioException(String message) {
		super(message);
	}
}