package com.api.dragonball.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "DBZ APP", version = "1.0",
        description = "Esta API REST permite gestionar el registro de personajes de Dragon Ball Z. " +
                      "Incluye funcionalidades para crear, actualizar, eliminar y consultar información sobre los personajes, " +
                      "como su nombre, nivel de poder. Está diseñada para facilitar la administración " +
                      "y personalización de los datos de los personajes en diversas aplicaciones. \n \n Para poder utilizar los servicios nos dirigimos a la sección jwt-authentication-controller y utilizamos el servicio authenticate, y lo utilizamos con las credenciales que les asignamos o las credenciales que están en la documentación de GITHUB",
        contact = @Contact(name = "Autor: Andykingche", email = "laqm_14@hotmail.com",
        extensions = {@Extension(name = "x-github", properties = {
                @ExtensionProperty(name = "url", value = "https://github.com/AndyKingche")
        })})),
        security = {@SecurityRequirement(name = "bearerToken")}
)
@SecuritySchemes({
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP,
                        scheme = "bearer", bearerFormat = "JWT")
})
public class OpenAPIConfig {

}
