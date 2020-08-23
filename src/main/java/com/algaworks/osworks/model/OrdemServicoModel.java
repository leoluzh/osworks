package com.algaworks.osworks.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algaworks.osworks.domain.model.StatusOrdemServico;
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
@JsonRootName(value = "ordemServico")
@Relation(collectionRelation = "ordensServico")
@JsonInclude(Include.NON_NULL)

@SuppressWarnings("serial")
public class OrdemServicoModel extends RepresentationModel<OrdemServicoModel> implements Serializable {

	private Long id;
	private ClienteResumoModel cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
}

