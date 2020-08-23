package com.algaworks.osworks.model;

import java.io.Serializable;

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
@JsonRootName(value = "cliente")
@Relation(collectionRelation = "clientes")
@JsonInclude(Include.NON_NULL)

@SuppressWarnings("serial")
public class ClienteResumoModel extends RepresentationModel<ClienteResumoModel> implements Serializable {

	private Long id;
	private String nome;
	
}
