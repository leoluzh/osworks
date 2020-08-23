package com.algaworks.osworks.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ClienteModel implements Serializable {

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
