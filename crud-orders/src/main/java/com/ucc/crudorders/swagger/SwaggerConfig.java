package com.ucc.crudorders.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.OpenAPI;
import  org.springframework.context.annotation.Bean;
import  org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration 
@OpenAPIDefinition 


public class SwaggerConfig {
    
    @Bean 
    public OpenAPI api() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("openapi.yaml");
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        OpenAPI openAPI = objectMapper.readValue(inputStream, OpenAPI.class);
        return   openAPI.info(openAPI.getInfo());

    }
}