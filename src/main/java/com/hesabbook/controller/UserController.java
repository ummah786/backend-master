package com.hesabbook.controller;

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
    public User saveTempUser(@RequestBody User user){
       User userResponse= userService.save(user);
       return userResponse;
    }
}
