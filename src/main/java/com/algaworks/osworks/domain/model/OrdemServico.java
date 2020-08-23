package com.algaworks.osworks.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.algaworks.osworks.domain.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.algaworks.osworks.domain.exception.NegocioException;


@Getter
@Setter
@EqualsAndHashCode(of={"id"})
@ToString

@Entity
@SuppressWarnings("serial")
public class OrdemServico implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@ConvertGroup(to=ValidationGroups.ClientId.class)
	@NotNull
	@ManyToOne	
	private Cliente cliente;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@JsonProperty( access = Access.READ_ONLY )
	@Enumerated(EnumType.STRING)
	private StatusOrdemServico status;
	
	@JsonProperty( access = Access.READ_ONLY )
	private OffsetDateTime dataAbertura;
	
	@JsonProperty( access = Access.READ_ONLY )
	private OffsetDateTime dataFinalizacao;

	@OneToMany( mappedBy = "ordemServico" )
	private List<Comentario> comentarios = new ArrayList<>();

	public void add( Comentario comentario ) {
		if( comentarios == null ) {
			comentarios = new ArrayList<>();
		}
		comentarios.add( comentario );
	}
	
	public boolean podeSerFinalizada() {
		return StatusOrdemServico.ABERTO.equals(getStatus());
	}

	public boolean naoPodeSerFinalizada() {
		return StatusOrdemServico.ABERTO.equals(getStatus());
	}
	
	public void finalizar() {
		if( naoPodeSerFinalizada() ) {
			throw new NegocioException("Ordem de servico não pode ser finalizada.");
		}
		this.status = StatusOrdemServico.FINALIZADA;
		this.dataFinalizacao = OffsetDateTime.now();
	}
	
}
