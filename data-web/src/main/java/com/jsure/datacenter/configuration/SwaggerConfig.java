package com.jsure.datacenter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: wuxiaobiao
 * @Description: Swagger2
 * 访问http://ip:8080/swagger-ui.html 查看restAPI接口信息
 * @Date: Created in 2018/4/10
 * @Time: 09:51
 * I am a Code Man -_-!
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jsure.datacenter.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("捷信医药大数据中心后台Restful-API接口")
                .description("I am a Code Man !!")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
