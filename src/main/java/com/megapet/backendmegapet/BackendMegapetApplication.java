package com.megapet.backendmegapet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@SpringBootApplication
public class BackendMegapetApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMegapetApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(final CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedHeaders("Access-Control-Allow-Origin",
								"*",
								"Access-Control-Allow-Methods",
								"POST, GET, OPTIONS, PUT, DELETE",
								"Access-Control-Allow-Headers",
								"Origin, X-Requested-With, Content-Type, Accept")
						.allowedOrigins("*")
						.allowedMethods("*");
				WebMvcConfigurer.super.addCorsMappings(registry);
			}
		};
	}
}
