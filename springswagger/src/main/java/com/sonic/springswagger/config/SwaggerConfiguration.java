package com.sonic.springswagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * SwaggerConfiguration
 *
 * @author Sonic
 * @since 2020/4/28
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                // 指定要展示的包
                .apis(RequestHandlerSelectors.basePackage("com.sonic.springswagger.controller"))
                // 过滤条件
                .paths(PathSelectors.regex("^/[\\s\\S]*"))
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfo("Sonic Swagger API", "Sonic Swagger API description", "v1.0", "urn:tos",
                new Contact("XXX", "XXX", "675737965@qq.com"), "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    }

}
