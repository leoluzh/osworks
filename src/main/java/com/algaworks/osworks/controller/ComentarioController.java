package com.algaworks.osworks.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.OrdemServicoService;
import com.algaworks.osworks.model.ComentarioInputModel;
import com.algaworks.osworks.model.ComentarioModel;

@RestController("/ordem-servico/{osId}/comentarios")
public class ComentarioController {

	@Autowired
	private OrdemServicoService servico;
	
	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<ComentarioModel> listar( @PathParam("osId") Long osId ){
		OrdemServico os = repository.findById( osId ).orElseThrow( () -> new EntidadeNaoEncontradaException("Ordem de servico não encontrada.") ) ;
		return toCollectionModel( os );
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar( 
			@PathParam("osId") Long osId ,
			@Valid @RequestBody ComentarioInputModel input ) {		
		Comentario comentario = servico.adicionarComentario(osId, input.getDescricao() );
		return toModel(comentario) ;
	}
	
	private ComentarioModel toModel( Comentario comentario ) {
		return modelMapper.map( comentario , ComentarioModel.class );
	}
	
	private List<ComentarioModel> toCollectionModel( OrdemServico os ){
		return os.getComentarios().stream().map( comentario -> toModel( comentario ) ).collect(Collectors.toList());
	}
	
}
