package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.entity.party.CACandidate;
import com.hesabbook.service.party.CACandidateService;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hesabbook/ca")
public class CACandidateController {

    private CACandidateService caCandidateService;

    @GetMapping("/all/{id}")
    public BusinessResponse findByPrimaryUserId(@PathVariable("id") String id) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<CACandidate> accountDetailResponse = caCandidateService.findByPrimaryUserId(id);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(accountDetailResponse);
        return businessResponse;
    }

    @PostMapping("/save")
    public BusinessResponse saveManageUser(@RequestBody CACandidate caCandidate) {
        BusinessResponse businessResponse = new BusinessResponse();
        CACandidate accountDetailResponse = caCandidateService.save(caCandidate);
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
            caCandidateService.delete(id);
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
