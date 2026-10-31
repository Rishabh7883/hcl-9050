package com.springcorefundamentals.jwtdemo.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
 info = @Info(
 title = "Demo API",
 version = "Version 1.0",
 contact = @Contact(
 name = "Xtranity",
 email = "dhannesh@gmil.com",
 url = "https://xtranity.com"
 ),
 license = @License(
 name = "Apache 2.0",
 url = "https://www.apache.org/licenses/LICENSE-2.0"
 ),
 description = "Spring Boot RESTful API Demo by Dhanesh"
 )
)
@SecurityScheme(
 name = "jwt-demo-api",
 scheme = "bearer",
 type = SecuritySchemeType.HTTP,
 in = SecuritySchemeIn.HEADER,
 bearerFormat = "JWT"
)
public class OpenApiConfig {
}