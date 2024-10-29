package com.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Clases")
                        .version("1.0")
                        .description("API para la gestión de clases educativas, incluyendo operaciones CRUD y manejo de estados")
                        .contact(new Contact()
                                .name("Liang Camilo Alvarez Tierradentro")
                                .email("liangcamilo2006@gmail.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("gestion-clases")
                .packagesToScan("com.app.controller", "com.app.dto")
                .build();
    }
}
