package com.example.demo.SwaggerDoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfigration {

 @Bean
    public OpenAPI customOpenAPI() 
 {
	 
        return new OpenAPI()
                .info(new Info()
                        .title("Student Management API")
                        .description("API documentation for Employee Login, Registration, and Management")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("ranjit Mane")
                                .email("hr@example.com")
                                .url("https://google.com")
                        )
                );
    }
 
}


