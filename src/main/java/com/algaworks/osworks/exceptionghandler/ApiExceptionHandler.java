package com.algaworks.osworks.exceptionghandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.exceptionghandler.Problema.Campo;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleNegocio( EntidadeNaoEncontradaException ex , WebRequest request ) {
		var problema = Problema
		.builder()
		.status( HttpStatus.NOT_FOUND.value() )
		.datahora( OffsetDateTime	.now() )
		.titulo(ex.getMessage())
		.build();
		return super.handleExceptionInternal( ex , problema , new HttpHeaders() , HttpStatus.NOT_FOUND , request );
	}
	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio( NegocioException ex , WebRequest request ) {
		var problema = Problema
		.builder()
		.status( HttpStatus.BAD_REQUEST.value() )
		.datahora( OffsetDateTime	.now() )
		.titulo(ex.getMessage())
		.build();
		return super.handleExceptionInternal( ex , problema , new HttpHeaders() , HttpStatus.BAD_REQUEST , request );
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		
		
		var problema = Problema.builder()
				.status(status.value())
				.datahora( OffsetDateTime.now() )
				.titulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
				.build();
		
		List<Campo> campos = ex.getBindingResult()
		.getAllErrors()
		.stream().map( error -> { 
			var mensagem = messageSource.getMessage( error , LocaleContextHolder.getLocale() );
			return Problema.Campo.builder()
				.nome( ((FieldError)error).getField() )
				.mensagem( mensagem  )
				.build(); 
		}).collect(Collectors.toList());
		
		problema.setCampos( campos );
		
		return super.handleExceptionInternal(ex, problema , headers, status, request);
		
	}
	
}
