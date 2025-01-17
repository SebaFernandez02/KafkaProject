package com.usuarios.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de registro de Usuarios")
                        .description("Documentación de la API para el sistema de registro de usuarios")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Sebastián Fernández")
                                .url("https://github.com/SebaFernandez02")
                                .email("safernandez@hiberus.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8100").description("Servidor Local")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación Completa")
                        .url("https://swagger.io"));
    }
}
