package com.example.schedule_composer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
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


        @Bean
        public GlobalOpenApiCustomizer customOpenApiCustomizer() {
                return openApi -> {
                        openApi.getComponents().getSchemas().put("LocalTime", new StringSchema().example("08:00"));
                };
        }
}
