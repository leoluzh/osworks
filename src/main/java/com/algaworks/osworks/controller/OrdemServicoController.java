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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.assemblers.OrdemServicoAssembler;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.OrdemServicoService;
import com.algaworks.osworks.model.OrdemServicoModel;
import com.algaworks.osworks.model.input.OrdemServicoInputModel;

@RestController
@RequestMapping("/v1/ordens-servico")
@SuppressWarnings("serial")
public class OrdemServicoController implements Serializable {

	@Autowired
	private OrdemServicoService service;

	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoAssembler ordemServicoAssembler;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<OrdemServicoModel> criar( @Valid @RequestBody OrdemServicoInputModel input ) {
		return ResponseEntity.ok( toModel( service.criar( toEntity( input ) ) ) );
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<OrdemServicoModel>> listar(){
		return ResponseEntity.ok( toCollectionModel( this.repository.findAll() ) );
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrdemServicoModel> buscar(  @PathVariable("id") Long id ) {
		Optional<OrdemServico> os = this.repository.findById( id );
		if( os.isPresent() ) {
			return ResponseEntity.ok( toModel( os.get() ) );
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar( @PathVariable("id") Long id ) {
		service.finalizar(id);
	}
	
	private OrdemServicoModel toModel( OrdemServico os ) {
		return this.ordemServicoAssembler.toModel( os );
	}
	
	private CollectionModel<OrdemServicoModel> toCollectionModel( List<OrdemServico> oss ){
		return this.ordemServicoAssembler.toCollectionModel( oss );
	}
	
	private OrdemServico toEntity( OrdemServicoInputModel in ) {
		return this.modelMapper.map( in , OrdemServico.class );
	}
	
}
