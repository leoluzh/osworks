package com.algaworks.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrdemServico;
import com.algaworks.osworks.domain.repository.ComentarioRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;
	
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar( OrdemServico os ) {
		os.setStatus(StatusOrdemServico.ABERTO);
		os.setDataAbertura(OffsetDateTime.now());
		return repository.save( os );
	}
	
	public void finalizar( Long osId ) {
		OrdemServico os = buscar(osId);
		os.finalizar();
		this.repository.save(os);
	}

	public Comentario adicionarComentario( Long osId , String descricao ) {
		OrdemServico os = buscar(osId);
		return this.comentarioRepository.save( toEntity(descricao, os) );
	}
	
	public Comentario toEntity( String descricao , OrdemServico os ) {
		return Comentario
				.builder()
					.descricao(descricao)
					.dataEnvio(OffsetDateTime.now())
					.ordemServico( os )
				.build();
	}

	private OrdemServico buscar(Long osId) {
		return repository.findById( osId ).orElseThrow( () -> new EntidadeNaoEncontradaException("Ordem de servico não encontrada!") );
	}
		
}
