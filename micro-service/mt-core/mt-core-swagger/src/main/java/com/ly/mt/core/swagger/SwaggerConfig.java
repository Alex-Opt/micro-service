package com.ly.mt.core.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@RefreshScope
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean swaggerEnable;
    @Value("${swagger.path}")
    private String swaggerPath;
    @Value("${swagger.title}")
    private String swaggerTitle;
    @Value("${swagger.desc}")
    private String swaggerDesc;
    @Value("${swagger.url}")
    private String swaggerUrl;
    @Value("${swagger.version}")
    private String swaggerVersion;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPath))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerTitle)
                .description(swaggerDesc)
                .termsOfServiceUrl(swaggerUrl)
                .version(swaggerVersion)
                .build();
    }
}