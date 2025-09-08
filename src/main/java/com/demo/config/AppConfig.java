package com.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class AppConfig {

       @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Supertruck Toll Calculator")
                .version("2.0") // <-- Set a valid OpenAPI version here
                .description("API documentation for Toll Calculator")
            );
    }


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Skip null values during mapping
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}
