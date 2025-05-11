package com.grigoryev.accountflow.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String title;

    @Value("${spring.application.description}")
    private String description;

    @Value("${spring.application.version}")
    private String version;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info()
                .title(title)
                .description(description)
                .version(version));
    }

}
