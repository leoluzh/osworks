package com.algaworks.osworks.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.osworks.domain.validation.ValidationGroups;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})

@Entity
@SuppressWarnings("serial")
public class Cliente implements Serializable {

	@NotNull( groups = ValidationGroups.ClientId.class )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
