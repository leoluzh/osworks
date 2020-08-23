package com.algaworks.osworks.assemblers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.osworks.controller.ClienteController;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.model.ClienteModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler extends RepresentationModelAssemblerSupport<Cliente,ClienteModel> {

	@Autowired
	private ModelMapper mapper;
	
	
	public ClienteModelAssembler() {
		super(ClienteController.class,ClienteModel.class);
	}
	
	@Override
	public ClienteModel toModel(Cliente entity) {
		ClienteModel model = mapper.map(entity,ClienteModel.class);
		model.add(linkTo(methodOn(ClienteController.class).buscar(entity.getId())).withSelfRel());
		return model;
	}

	@Override
	public CollectionModel<ClienteModel> toCollectionModel(Iterable<? extends Cliente> entities){
		CollectionModel<ClienteModel> models = super.toCollectionModel(entities);
		models.add(linkTo(methodOn(ClienteController.class).listar()).withSelfRel());
		return models;
	}
	
}
