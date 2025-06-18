package com.monitor.app.DriveGuard.infrastructure.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todos os endpoints
                        .allowedOrigins("*") // Permite todas as origens
                        .allowedMethods("*") // Permite todos os métodos HTTP (GET, POST, PUT, DELETE, etc.)
                        .allowedHeaders("*") // Permite todos os cabeçalhos
                        .allowCredentials(false); // Não permite credenciais (cookies)
            }
        };
    }
}
