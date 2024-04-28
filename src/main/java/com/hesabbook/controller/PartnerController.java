package com.hesabbook.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.Address;
import com.hesabbook.entity.party.Partner;
import com.hesabbook.service.party.PartyService;
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
@RequestMapping("/hesabbook/partner")
public class PartnerController {
    @Autowired
    private PartyService partyService;

    @PostMapping("/upload")
    public BusinessResponse uploadExcel(@RequestBody List<LinkedHashMap<String, String>> linkedHashMap) throws IOException {
        BusinessResponse businessResponse = partyService.saveBulk(linkedHashMap);
        return businessResponse;
    }

    @GetMapping("/all")
    public BusinessResponse getAllMangeUser() {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Partner> accountDetailResponse = partyService.findAll();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @GetMapping("/all/{id}")
    public BusinessResponse findByPrimaryUserId(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Partner> accountDetailResponse = partyService.findByPrimaryPartnerId(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/save")
    public BusinessResponse saveManageUser(@RequestBody Partner partner) {
        BusinessResponse businessResponse = new BusinessResponse();
        Partner accountDetailResponse = partyService.save(partner);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }


    @PostMapping({"/save/address", "/save/address/{addressId}"})
    public BusinessResponse savePartnerAddress(@RequestBody Partner partner, @PathVariable(value = "addressId", required = false) Integer addressId) {
        BusinessResponse businessResponse = new BusinessResponse();
        Partner accountDetailResponse = null;
        if (checkForAddessInsertUpdate(partner)) {
            accountDetailResponse = partyService.find(partner.getId());
            List<Address> addresses = new ArrayList<>();
            addresses.addAll(accountDetailResponse.getMultipleShippingAddress());
            addresses.addAll(partner.getMultipleShippingAddress());
            accountDetailResponse.setMultipleShippingAddress(addresses);
            accountDetailResponse = partyService.save(accountDetailResponse);
        }
        if (addressId != null) {
            partyService.savePartnerAddress(partner.getMultipleShippingAddress().get(0), addressId);
            accountDetailResponse = partyService.find(partner.getId());
        }
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    private boolean checkForAddessInsertUpdate(Partner partner) {
        Optional<Address> addressOptional = partner.getMultipleShippingAddress().stream().filter(x -> x.getId() == null).findFirst();
        if (addressOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/delete/{id}")
    public BusinessResponse deleteManageUser(@PathVariable("id") Integer id) {
        BusinessResponse businessResponse = new BusinessResponse();
        String flag = null;
        if (id != null) {
            partyService.delete(id);
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
