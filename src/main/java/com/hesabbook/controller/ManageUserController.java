package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.entity.account.ManageUsers;
import com.hesabbook.service.account.ManageUserService;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/all/{id}")
    public List<ManageUsers> findByPrimaryUserId(@PathVariable("id") String id) {
        return manageUserService.findByPrimaryUserId(id);
    }

    @PostMapping("/save")
    public ManageUsers saveManageUser(@RequestBody ManageUsers manageUsers) {
        return manageUserService.save(manageUsers);
    }

    @PostMapping("/delete/{id}")
    public BusinessResponse deleteManageUser(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        String flag = null;
        if (id != null) {
            manageUserService.delete(id);
            flag = "Success";
            businessResponse.setCode(200);
            businessResponse.setStatus("SUCCESS");
            businessResponse.setResponse(flag);
        } else {
            flag = "Failure";
            businessResponse.setCode(200);
            businessResponse.setStatus("FAILURE");
            businessResponse.setResponse(flag);
        }
        return businessResponse;
    }
}
