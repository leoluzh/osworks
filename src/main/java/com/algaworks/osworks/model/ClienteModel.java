package com.algaworks.osworks.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

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
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable {

	@NotNull
	private Long id;
	
	@Size(min = 1 , max = 60 )
	@NotBlank
	private String nome;
	
	@Email
	private String email;
	
	@Size(min = 2 , max = 20)
	@NotBlank
	private String telefone;
		
}
