package com.hesabbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@RestController
@SpringBootApplication
public class HesabbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(HesabbookApplication.class, args);
    }

    @GetMapping("/hello")
    public String value() {
        return "hllo";
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Allow all origins
        config.addAllowedOrigin("*");
        // Allow all HTTP methods
        config.addAllowedMethod("*");
        // Allow all headers
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
