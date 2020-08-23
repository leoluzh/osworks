package com.algaworks.osworks.domain.exception;

@SuppressWarnings("serial")
public class NegocioException extends RuntimeException {

	public NegocioException( String mensagem ) {
		super(mensagem);
	}
	
}
