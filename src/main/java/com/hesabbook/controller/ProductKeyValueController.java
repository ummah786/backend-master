package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.entity.ProductKeyValues;
import com.hesabbook.service.ProductKeyValueService;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/product/key/value")
public class ProductKeyValueController {
    @Autowired
    private ProductKeyValueService ProductKeyValuesService;

    @GetMapping("/get/{id}")
    public BusinessResponse getById(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        ProductKeyValues ProductKeyValuesResponse = ProductKeyValuesService.find(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(ProductKeyValuesResponse);
        return businessResponse;
    }

    @PostMapping("/save")
    public BusinessResponse updateSave(@RequestBody ProductKeyValues productKeyValues) {
        BusinessResponse businessResponse = new BusinessResponse();
        ProductKeyValues ProductKeyValuess = new ProductKeyValues();
        ProductKeyValuess.setId(productKeyValues.getId());
        ProductKeyValuess.setKes(productKeyValues.getKes());
        ProductKeyValuess.setValue(productKeyValues.getValue());
        ProductKeyValuess.setPrimary_user_id(productKeyValues.getPrimary_user_id());
        ProductKeyValuess.setSecondary_user_id(productKeyValues.getSecondary_user_id());
        try {
            ProductKeyValues responseProductKeyValues = ProductKeyValuesService.save(ProductKeyValuess);
            businessResponse.setCode(200);
            businessResponse.setStatus("SUCCESS");
            businessResponse.setResponse(responseProductKeyValues);
        } catch (Exception exception) {
            businessResponse.setCode(500);
            businessResponse.setStatus("FAILURE");
            businessResponse.setException(exception);
        }
        return businessResponse;
    }

    @GetMapping("/get/primary/{id}")
    public BusinessResponse getByPrimaryUserId(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<ProductKeyValues> ProductKeyValuesResponse = ProductKeyValuesService.getPrimaryUserId(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(ProductKeyValuesResponse);
        return businessResponse;
    }

    @GetMapping("/get/company/{id}")
    public BusinessResponse getByCompany(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<String> ProductKeyValuesResponse = ProductKeyValuesService.getCompany(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(ProductKeyValuesResponse);
        return businessResponse;
    }

    @GetMapping("/get/business/{id}")
    public BusinessResponse getByBusinessName(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<String> ProductKeyValuesResponse = ProductKeyValuesService.getBusinessName(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(ProductKeyValuesResponse);
        return businessResponse;
    }

    @GetMapping("/get/category/{id}")
    public BusinessResponse getByCategory(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<String> ProductKeyValuesResponse = ProductKeyValuesService.getCategory(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(ProductKeyValuesResponse);
        return businessResponse;
    }

    @GetMapping("/get/rack/{id}")
    public BusinessResponse getByRack(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<String> ProductKeyValuesResponse = ProductKeyValuesService.getRackKey(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(ProductKeyValuesResponse);
        return businessResponse;
    }

    @GetMapping("/get/warehouse/{id}")
    public BusinessResponse getByWareHouse(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<String> ProductKeyValuesResponse = ProductKeyValuesService.getWareHouse(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(ProductKeyValuesResponse);
        return businessResponse;
    }
}
