package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.entity.account.ManageUsers;
import com.hesabbook.service.account.ManageUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/manageuser")
public class ManageUserController {

    @Autowired
    private ManageUserService manageUserService;

    @GetMapping("/all")
    public List<ManageUsers> getAllMangeUser() {
        return manageUserService.findAll();
    }


    @PostMapping("/save")
    public ManageUsers saveManageUser(@RequestBody ManageUsers manageUsers){
        return manageUserService.save(manageUsers);
    }
}
