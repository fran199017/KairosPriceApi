package com.francisconicolau.prueba.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("fran199017@gmail.com");
        contact.setName("Francisco Nicolau Ausejo");

        Info info = new Info()
                .title("Prueba técnica")
                .version("1.0")
                .contact(contact)
                .description("Esta API contiene la prueba técnica");

        return new OpenAPI().info(info);
    }
}