package com.example.schedule_composer.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Schedule Composer API",
                version = "1.0",
                description = ""
        )
)
public class OpenApiConfig {
}
