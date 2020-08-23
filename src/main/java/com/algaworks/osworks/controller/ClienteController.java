package com.algaworks.osworks.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.assemblers.ClienteModelAssembler;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.ClienteService;
import com.algaworks.osworks.model.ClienteModel;
import com.algaworks.osworks.model.input.ClienteInputModel;

@RestController
@RequestMapping("/v1/clientes")
@SuppressWarnings("serial")
public class ClienteController implements Serializable {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ClienteService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ClienteModelAssembler  clienteModelAssembler;
	
	@GetMapping
	public ResponseEntity<CollectionModel<ClienteModel>> listar(){
		return ResponseEntity.ok( toCollection( repository.findAll() ) );	
	}
	
	@GetMapping("{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<ClienteModel> buscar( @PathVariable Long id ) {
		Optional<Cliente> cliente = repository.findById( id );
		if( cliente.isPresent() ) {
			return ResponseEntity.ok( toModel( cliente.get() ) );
		}
		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	public void criar( @Valid @RequestBody ClienteInputModel input ) {
		service.salvar( toEntity( input ) );
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ClienteModel> atualizar( @PathVariable Long id ,  @RequestBody Cliente cliente ) {
		if( !this.repository.existsById( id ) ) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(id);
		service.salvar( cliente );
		return ResponseEntity.ok(toModel(cliente));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> excluir( @PathVariable Long id ){
		if( !this.repository.existsById(id) ) {
			return ResponseEntity.notFound().build();
		} 
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	private Cliente toEntity( ClienteInputModel model ) {
		return this.modelMapper.map( model , Cliente.class ); 
	}
	
	private ClienteModel toModel( Cliente entity ) {
		return this.clienteModelAssembler.toModel(entity);
	}
	
	private CollectionModel<ClienteModel> toCollection( List<Cliente> entities ){
		return this.clienteModelAssembler.toCollectionModel(entities);
	}
	
}
