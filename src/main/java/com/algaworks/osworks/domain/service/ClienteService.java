package com.algaworks.osworks.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente salvar( Cliente cliente ) {
		Cliente existente = repository.findByEmail(cliente.getEmail()); 
		if( existente != null && !existente.equals(cliente) ) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email.");
		}
		Optional.ofNullable( existente ).ifPresent( ( e ) -> cliente.setId( e.getId() ) ); 
		repository.save( cliente );
		return cliente;
	}
		
}
