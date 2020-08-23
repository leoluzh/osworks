package com.algaworks.osworks.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ClienteResumoModel implements Serializable {

	private Long id;
	private String nome;
	
}
