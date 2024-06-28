package com.ucc.crudservice.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import  io.swagger.v3.oas.models.info.Info;
import  org.springframework.context.annotation.Bean;
import  org.springframework.context.annotation.Configuration;


@Configuration // es una anotacion que indica le dice que esta clase va ser de configuracion
@OpenAPIDefinition //marca que esta clase va ser usada por swagger para hacer la documentacion


public class SwaggerConfig {
    //un been es un objeto intanciado,esambleado y gesteniona en el contedor de spring
    @Bean //la utilizamos dentro de una clase con metodo configuration par indicar que el retorno este registrado.
    public OpenAPI api(){ //metodo
        return new OpenAPI().info(new Info().title("Nuestra primera api creada con java/Spring").version("1.0")//creamos una nueva instancia y le asigamos a la info
                .contact(new Contact().name("Quique Espinosa").url("https://www.linkedin.com/in/enrique-espinosa-010783251"))
                .description("Esta api te permite hacer las operaciones Crud de un producto")
        );
    }
}
