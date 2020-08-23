package com.algaworks.osworks.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.osworks.controller.OrdemServicoController;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.model.OrdemServicoModel;

@Component
public class OrdemServicoAssembler extends RepresentationModelAssemblerSupport<OrdemServico,OrdemServicoModel> {

	@Autowired
	private ModelMapper mapper;
	
	public OrdemServicoAssembler() {
		super(OrdemServicoController.class,OrdemServicoModel.class);
	}
	
	@Override
	public OrdemServicoModel toModel(OrdemServico entity) {
		OrdemServicoModel model = mapper.map(entity,OrdemServicoModel.class);
		model.add(linkTo(methodOn(OrdemServicoController.class).buscar(entity.getId())).withSelfRel());
		return model;
	}

	@Override
	public CollectionModel<OrdemServicoModel> toCollectionModel(Iterable<? extends OrdemServico> entities){
		CollectionModel<OrdemServicoModel> models = super.toCollectionModel(entities);
		models.add(linkTo(methodOn(OrdemServicoController.class).listar()).withSelfRel());
		return models;
	}
	
}
