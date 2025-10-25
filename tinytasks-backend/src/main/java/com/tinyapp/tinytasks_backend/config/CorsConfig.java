package com.tinyapp.tinytasks_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer () {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings (CorsRegistry registry){
                registry.addMapping("/**") // Aplica a todas las rutas /api/tareas
                        .allowedOrigins(
                                "http://localhost:5500",
                                "http://127.0.0.1:5500",
                                "http://localhost:3000"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*") // permite todos los encabezados
                        .allowCredentials(false); // cambiar a true si se manejan cookies/sesiones
            }

        };
    }
}
