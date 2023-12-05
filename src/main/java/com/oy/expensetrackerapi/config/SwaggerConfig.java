package com.oy.expensetrackerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket commonDocketConfig() {

        return new Docket(DocumentationType.SWAGGER_2) .select()
                .apis(RequestHandlerSelectors.basePackage("com.oy.expensetrackerapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo("Expense Tracker API","Vulnerable API Application",null,null,null,null,null));
//                .tags(new Tag("Expense Tracker API", "your description"));
    }


}
