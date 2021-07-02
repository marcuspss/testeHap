package br.com.hapvida.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConf {
	
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.hapvida"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Hapvida")
            .description("Teste Hapvida")
            .version("1.0")
            .termsOfServiceUrl("mpss.frb@gmail.com")
            .license("LICENSE FREE")
            .licenseUrl("https://github.com/marcuspss")
            .build();
    }

}
