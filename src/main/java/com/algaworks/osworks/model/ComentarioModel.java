package com.algaworks.osworks.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ComentarioModel implements Serializable {

	private Long id;
	private String descricao;
	private OffsetDateTime dataEnvio;
	
}
