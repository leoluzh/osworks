package com.algaworks.osworks.model.input;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

@Data
@SuppressWarnings("serial")
public class ComentarioInputModel implements Serializable {

	@NotBlank
	private String descricao;
	
}
