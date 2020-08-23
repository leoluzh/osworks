package com.algaworks.osworks.exceptionghandler;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

@JsonInclude(Include.NON_NULL)
@SuppressWarnings("serial")
public class Problema implements Serializable {

	private Integer status;
	private OffsetDateTime datahora;
	private String titulo;
	private List<Campo> campos;
	
	@Data
	@Builder
	public static class Campo {
		private String nome;
		private String mensagem;
	}
	
}
