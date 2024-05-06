package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.entity.salepurchase.SalePurchase;
import com.hesabbook.service.SalePurchaseService;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/sale/purchase")
public class SalePurchaseController {
    @Autowired
    private SalePurchaseService salePurchaseService;

    @GetMapping("/all/{id}")
    public BusinessResponse findByPrimaryUserId(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<SalePurchase> salePurchases = salePurchaseService.findByPrimaryUserId(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(salePurchases);
        return businessResponse;
    }

    @PostMapping("/save")
    public BusinessResponse saveManageUser(@RequestBody SalePurchase manageUsers) {
        BusinessResponse businessResponse = new BusinessResponse();
        SalePurchase salePurchases = salePurchaseService.save(manageUsers);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(salePurchases);
        return businessResponse;
    }

    @PostMapping("/delete/{id}")
    public BusinessResponse deleteManageUser(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        String flag = null;
        if (id != null) {
            salePurchaseService.delete(id);
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
