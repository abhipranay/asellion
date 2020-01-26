package com.asellion.config;

import io.swagger.models.*;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends HttpServlet {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        Info info = new Info()
                .title("Asellion Assignment")
                .description("")
                .contact(new Contact()
                        .email("github.com/abhipranay"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

        ServletContext context = config.getServletContext();
        Swagger swagger = new Swagger().info(info);
        swagger.externalDocs(new ExternalDocs("Find code here", "https://github.com/abhipranay/asellion"));
        swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
        swagger.tag(new Tag()
                .name("Hibernate Search")
                .description("Google for your entities")
                .externalDocs(new ExternalDocs("Find out more", "http://hibernate.org/search/")));
        context.setAttribute("swagger", swagger);
    }
}
