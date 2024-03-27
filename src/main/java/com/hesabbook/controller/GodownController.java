package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.entity.inventory.Godowon;
import com.hesabbook.service.inventory.GodowonService;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/godown")
public class GodownController {
    @Autowired
    private GodowonService godowonService;

    @GetMapping("/all")
    public BusinessResponse getAllMangeUser() {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Godowon> accountDetailResponse = godowonService.findAll();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @GetMapping("/all/{id}")
    public BusinessResponse findByPrimaryUserId(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Godowon> accountDetailResponse = godowonService.getPrimaryUserId(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/save")
    public BusinessResponse saveManageUser(@RequestBody Godowon Godowon) {
        BusinessResponse businessResponse = new BusinessResponse();
        Godowon accountDetailResponse = godowonService.save(Godowon);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/delete/{id}")
    public BusinessResponse deleteManageUser(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        String flag = null;
        if (id != null) {
            godowonService.delete(id);
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
