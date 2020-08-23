package com.algaworks.osworks.domain.exception;

@SuppressWarnings("serial")
public class EntidadeNaoEncontradaException extends NegocioException {

	public EntidadeNaoEncontradaException( String mensagem ) {
		super( mensagem );
	}
	
}
