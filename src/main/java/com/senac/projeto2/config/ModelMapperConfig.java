package com.senac.projeto2.config;

import com.senac.projeto2.dto.request.JogoDTORequest;
import com.senac.projeto2.entity.Jogo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
