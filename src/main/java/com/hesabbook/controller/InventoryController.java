package com.hesabbook.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import com.hesabbook.entity.inventory.Inventory;
import com.hesabbook.service.inventory.InventoryService;
import com.hesabbook.utils.BusinessResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/upload")
    public BusinessResponse uploadExcel(@RequestBody List<LinkedHashMap<String, String>> linkedHashMap) throws IOException {
        BusinessResponse businessResponse = inventoryService.saveBulk(linkedHashMap);
        return businessResponse;
    }

    @GetMapping("/all")
    public BusinessResponse getAllMangeUser() {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Inventory> accountDetailResponse = inventoryService.findAll();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }


    @GetMapping("/all/{id}")
    public BusinessResponse findByPrimaryUserId(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Inventory> accountDetailResponse = inventoryService.getPrimaryUserId(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/save")
    public BusinessResponse saveManageUser(@RequestBody Inventory inventory) {
        BusinessResponse businessResponse = new BusinessResponse();
        Inventory accountDetailResponse = inventoryService.save(inventory);
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
            inventoryService.delete(id);
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

    private String cellToString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
