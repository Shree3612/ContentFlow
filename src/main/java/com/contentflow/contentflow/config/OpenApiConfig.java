package com.contentflow.contentflow.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blogging Application API")
                        .description("APIs for managing a blogging platform, developed by Shripad Kulkarni. Provides endpoints for blog creation, management, and user interactions.")
                        .version("1.0")
                        .termsOfService("https://bloggingapplication.com/terms")
                        .contact(new Contact()
                                .name("Shripad Kulkarni")
                                .url("https://bloggingapplication.com")
                                .email("shripadkulkarni3612@gmail.com"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
