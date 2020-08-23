package com.algaworks.osworks.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.osworks.controller.ComentarioController;
import com.algaworks.osworks.controller.OrdemServicoController;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.model.ComentarioModel;

@Component
public class ComentarioModelAssembler extends RepresentationModelAssemblerSupport<Comentario,ComentarioModel> {

	@Autowired
	private ModelMapper mapper;
	
	public ComentarioModelAssembler() {
		super(ComentarioController.class,ComentarioModel.class);
	}

	@Override
	public ComentarioModel toModel(Comentario entity) {
		ComentarioModel model = mapper.map(entity,ComentarioModel.class);
		model.add(linkTo(methodOn(ComentarioController.class).buscar(entity.getOrdemServico().getId(),entity.getId())).withSelfRel());
		model.add(linkTo(methodOn(OrdemServicoController.class).buscar(entity.getOrdemServico().getId())).withRel("ordens-servico"));
		model.add(linkTo(methodOn(OrdemServicoController.class).buscar(entity.getOrdemServico().getId())).withRel("ordens-servico"));
		return model;
	}

	@Override
	public CollectionModel<ComentarioModel> toCollectionModel(Iterable<? extends Comentario> entities){
		CollectionModel<ComentarioModel> models = super.toCollectionModel(entities);
		return models;
	}
		
}
