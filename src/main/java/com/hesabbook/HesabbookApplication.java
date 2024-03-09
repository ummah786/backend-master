package com.hesabbook;

import java.util.Random;

import com.hesabbook.entity.account.User;
import com.hesabbook.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private UserService userService;

    @PostMapping("/user/temp")
    public User saveTempUser(@RequestBody User user) {
        Random random = new Random();
        int randomData = random.nextInt(90000000) + 10000000;
        user.setPrimary_user_id(String.valueOf(randomData));
        User userResponse = userService.save(user);
        return userResponse;
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
