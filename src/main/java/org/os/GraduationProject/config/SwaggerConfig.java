package org.os.GraduationProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("org.os.GraduationProject.controller"))//扫描路径
            .paths(PathSelectors.any())//定义哪些路径的接口需要生成文档
            .build();
    }

    private ApiInfo apiInfo() {
        Contact  contact = new Contact("EMSTAN", "http://www.apesource.com", "junmoxie72@gmail.com");
        return new ApiInfoBuilder()
            .title("健康医疗预约平台")//文档首页标题
            .version("1.0")//文档版本
            .description("健康医疗预约平台后端接口说明文档")//文档描述信息
            .contact(contact)//创建者信息
            .build();
    }
}
