package com.tietoevry.socialnetworkfordogs.configuration.security

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import java.util.Arrays
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components().addSecuritySchemes(
                    "bearer-jwt",
                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                        .`in`(SecurityScheme.In.HEADER).name("Authorization")
                )
            ).addSecurityItem(SecurityRequirement().addList("bearer-jwt", listOf("read", "write")))
            .info(
                Info().title("Social Network For Dogs API documentation")
                    .contact(Contact().name("Dmitry Safenreiter").email("d.safenreiter@g.nsu.ru"))
                    .contact(Contact().name("Darya Sofronova").email("d.sofronova@g.nsu.ru"))
            )
    }
}
