package com.algaworks.osworks.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.OrdemServicoService;
import com.algaworks.osworks.model.OrdemServicoInputModel;
import com.algaworks.osworks.model.OrdemServicoModel;

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
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrdemServicoModel criar( @Valid @RequestBody OrdemServicoInputModel input ) {
		return toModel( service.criar( toEntity( input ) ) );
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar(){
		return toCollectionModel( this.repository.findAll() );
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
		return this.modelMapper.map( os , OrdemServicoModel.class );
	}
	
	private List<OrdemServicoModel> toCollectionModel( List<OrdemServico> oss ){
		return oss.stream().map( os -> toModel(os) ).collect(Collectors.toList());
	}
	
	private OrdemServico toEntity( OrdemServicoInputModel in ) {
		return this.modelMapper.map( in , OrdemServico.class );
	}
	
}
