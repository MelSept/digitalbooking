package com.digitalbooking.apilodgings;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "PI DigitalBooking - API Lodgings",
                description = "This is A API RESTful Project",
                termsOfService = "Education and practical purpose",
                contact = @Contact(name = "Grupo 6 Camada 12", url = "GitLab README"),
                license = @License(name = "Open Source", url = "To be defined"),
                version = "1.0"),
        servers = {
                @Server(url = "http://localhost:8081/digitalbooking/lodgings/api/v1", description = "Local Development"),
                @Server(url = "http://52.15.225.173/digitalbooking/lodgings/api/v1", description = "Production")})
@OpenAPI31
public class CustomOpenAPI {

}
