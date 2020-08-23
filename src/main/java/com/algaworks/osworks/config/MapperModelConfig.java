package com.algaworks.osworks.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperModelConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper(); 
	}
	
}
