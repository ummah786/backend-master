package com.hesabbook.controller;

import java.util.Random;

import com.hesabbook.entity.account.User;
import com.hesabbook.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
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
}
