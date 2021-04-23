package br.com.dcruzb.product.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2370374783250129970L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
