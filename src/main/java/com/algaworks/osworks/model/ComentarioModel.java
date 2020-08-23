package com.algaworks.osworks.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "comentario")
@Relation(collectionRelation = "comentarios")
@JsonInclude(Include.NON_NULL)

@SuppressWarnings("serial")
public class ComentarioModel extends RepresentationModel<ComentarioModel> implements Serializable {

	private Long id;
	private String descricao;
	private OffsetDateTime dataEnvio;
	
}
