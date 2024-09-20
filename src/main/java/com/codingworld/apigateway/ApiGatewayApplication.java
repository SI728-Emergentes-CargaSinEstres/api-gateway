package com.codingworld.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class ApiGatewayApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("https://cse-fundamentos.web.app", "https://business-service-v4.azurewebsites.net", "http://localhost:4200", "https://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Permitir los métodos necesarios
						.allowedHeaders("*"); // Permitir todos los encabezados
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}


