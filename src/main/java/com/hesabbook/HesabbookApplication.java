package com.hesabbook;

import java.util.Random;

import com.hesabbook.entity.account.User;
import com.hesabbook.service.UserService;
import com.hesabbook.service.UserServiceImpl;
import com.hesabbook.utils.BusinessResponse;

import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private UserServiceImpl userServiceimpl;

    @PostMapping("/login")
    public BusinessResponse login(@RequestBody User user) {
        BusinessResponse businessResponse = new BusinessResponse();
        User userResponse = userServiceimpl.authenticate(user.getMobileNumber(), user.getTempPassword());
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(userResponse);
        return businessResponse;
    }


    @PostMapping("/user/temp")
    public User saveTempUser(@RequestBody User user) {
        Random random = new Random();
        if (StringUtils.isNotBlank(user.getFirstName())) {
            int randomNumber = random.nextInt(90000) + 10000;
            user.setPrimary_user_id(user.getFirstName().trim() + randomNumber);
        } else if (StringUtils.isNotBlank(user.getMobileNumber())) {
            int randomNumber = random.nextInt(900) + 100;
            user.setPrimary_user_id(user.getMobileNumber().trim() + randomNumber);
        } else if (StringUtils.isNotBlank(user.getEmail())) {
            int randomNumber = random.nextInt(900) + 100;
            user.setPrimary_user_id(user.getEmail().trim() + randomNumber);
        } else {
            int randomData = random.nextInt(90000000) + 10000000;
            user.setPrimary_user_id(String.valueOf(randomData));
        }
        User userResponse = userService.save(user);
        return userResponse;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
