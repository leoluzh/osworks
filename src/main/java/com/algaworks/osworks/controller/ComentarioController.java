package com.algaworks.osworks.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.assemblers.ComentarioModelAssembler;
import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.ComentarioRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.OrdemServicoService;
import com.algaworks.osworks.model.ComentarioModel;
import com.algaworks.osworks.model.input.ComentarioInputModel;

@RestController("/ordens-servico/{osId}/comentarios")
public class ComentarioController {

	@Autowired
	private OrdemServicoService servico;
	
	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private ComentarioModelAssembler comentarioModelAssembler;
	
	@GetMapping
	public ResponseEntity<List<ComentarioModel>> listar( @PathParam("osId") Long osId ){
		OrdemServico os = repository.findById( osId ).orElseThrow( () -> new EntidadeNaoEncontradaException("Ordem de servico não encontrada.") ) ;
		return ResponseEntity.ok( toCollectionModel( os ) );
	}

	@GetMapping("/{comentarioId}")
	public ResponseEntity<ComentarioModel> buscar( @PathParam("osId") Long osId , @PathParam("comentarioId") Long comentarioId ){
		OrdemServico os = repository.findById( osId ).orElseThrow( () -> new EntidadeNaoEncontradaException("Ordem de servico não encontrada.") ) ;
		Comentario comentario = comentarioRepository.findById( comentarioId ).orElseThrow( () -> new EntidadeNaoEncontradaException("Comentário não encontrado."));
		return ResponseEntity.ok( toModel( comentario ) );
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ComentarioModel> adicionar( 
			@PathParam("osId") Long osId ,
			@Valid @RequestBody ComentarioInputModel input ) {		
		Comentario comentario = servico.adicionarComentario(osId, input.getDescricao() );
		return ResponseEntity.ok( toModel( comentario ) );
	}
	
	private ComentarioModel toModel( Comentario entity ) {
		return this.comentarioModelAssembler.toModel(entity);
	}
	
	private List<ComentarioModel> toCollectionModel( OrdemServico entity ){
		return entity.getComentarios().stream().map( comentario -> toModel( comentario ) ).collect(Collectors.toList());
	}
	
}
