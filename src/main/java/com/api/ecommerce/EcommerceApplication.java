package com.api.ecommerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@OpenAPIDefinition(
        info = @Info(
                title = "Sistema de Ecommerce",
                version = "1.0.0",
                description = "API REST para el Trabajo Práctico Obligatorio de la materia Aplicaciones Interactivas (Primer Cuatrimestre 2025)."
        ),
        servers = {
                @Server(url = "http://localhost:8080/")
        }
)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // desactiva el form
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
