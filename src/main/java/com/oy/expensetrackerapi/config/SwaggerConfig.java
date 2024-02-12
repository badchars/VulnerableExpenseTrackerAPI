package com.oy.expensetrackerapi.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    public static Set<String> allEndpoints = new HashSet<>();

    @Bean
    public Docket commonDocketConfig() {

        return new Docket(DocumentationType.SWAGGER_2) .select()
                .apis(RequestHandlerSelectors.basePackage("com.oy.expensetrackerapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo("Expense Tracker API","Vulnerable API Application",null,null,null,null,null));
//                .tags(new Tag("Expense Tracker API", "your description"));
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
                .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
                .getHandlerMethods();
        map.forEach((key, value) -> {
            key.getDirectPaths().parallelStream().forEach(s -> allEndpoints.add(s));

        });
    }


}
