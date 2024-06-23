package com.burakozkan138.cinemabookingsystem.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Secured Test App"), security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(type = SecuritySchemeType.HTTP, bearerFormat = "jwt", name = "bearerAuth", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {

}