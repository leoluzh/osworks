package com.algaworks.osworks.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@Entity
@EqualsAndHashCode(of= {"id"})

@SuppressWarnings("serial")
public class Comentario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	private OrdemServico ordemServico;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private OffsetDateTime dataEnvio;
	
}
