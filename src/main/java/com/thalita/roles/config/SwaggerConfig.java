package com.thalita.roles.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Roles Service",
                description = "A service to manage team roles",
                version = "1.0.0"
        ),
        servers = @Server(url = "http://localhost:8080")
)

@Configuration
public class SwaggerConfig {

    public OpenAPI springShopOpenAPI() {
        return new OpenAPI();
    }
}
