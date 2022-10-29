package com.gft.exceptions;

public class UsuarioDuplicadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioDuplicadoException(String msg) {
		super(msg);
	}
}
