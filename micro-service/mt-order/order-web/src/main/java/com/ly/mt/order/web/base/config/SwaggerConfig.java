package com.ly.mt.order.web.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@Component
@EnableSwagger2
public class SwaggerConfig {
    @Resource
    OrderWebYml yml;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(yml.getSwaggerEnable())
                .select()
                .apis(RequestHandlerSelectors.basePackage(yml.getSwaggerPath()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(yml.getSwaggerTitle())
                .description(yml.getSwaggerDesc())
                .termsOfServiceUrl(yml.getSwaggerUrl())
                .version(yml.getSwaggerVersion())
                .build();
    }
}