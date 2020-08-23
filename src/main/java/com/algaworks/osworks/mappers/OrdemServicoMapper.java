package com.algaworks.osworks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.model.OrdemServicoModel;

@Mapper
public interface OrdemServicoMapper {

	OrdemServicoMapper MAPPER = Mappers.getMapper( OrdemServicoMapper.class );

	@Mappings({
		@Mapping( source = "id" , target = "id" ) ,
		@Mapping( source = "cliente.nome"  , target="nomeCliente" ) ,
		@Mapping( source = "descricao" , target = "descricao" ) , 
		@Mapping( source = "status" , target = "status" ) ,
		@Mapping( source = "dataAbertura" , target = "dataAbertura" ) ,
		@Mapping( source = "dataFinalizacao" , target = "dataFinalizacao" )
	})
	OrdemServicoModel toModel( OrdemServico entity );
	
}
