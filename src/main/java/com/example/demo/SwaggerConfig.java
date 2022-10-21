package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Cinema demo")
                .description("Cinema tickets reservation")
                .build();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true)
                .groupName("demo")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api.*"))
                .build();
    }
}