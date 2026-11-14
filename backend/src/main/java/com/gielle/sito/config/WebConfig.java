package com.gielle.sito.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Permette alle pagine del sito (anche aperte con Live Server) di inviare i moduli a questo backend. */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${gielle.sito.origini}")
    private String[] origini;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins(origini).allowedMethods("POST");
    }
}
