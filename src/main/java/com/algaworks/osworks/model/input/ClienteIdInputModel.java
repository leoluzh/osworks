package com.algaworks.osworks.model.input;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data

@SuppressWarnings("serial")
public class ClienteIdInputModel implements Serializable {

	@NotNull
	private Long id;
	
}
