package com.hesabbook.controller;

import com.hesabbook.entity.bank.BankAccount;
import com.hesabbook.service.bank.BankAccountService;
import com.hesabbook.utils.BusinessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bankaccounts")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public BusinessResponse getAllBankAccounts() {
        BusinessResponse businessResponse = new BusinessResponse();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(bankAccountService.getAllBankAccounts());
        return businessResponse;
    }

    @GetMapping("/{id}")
    public BusinessResponse getBankAccountById(@PathVariable Long id) {
        BusinessResponse businessResponse = new BusinessResponse();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(bankAccountService.getBankAccountById(id).orElse(null));
        return businessResponse;
    }

    @PostMapping
    public BusinessResponse createBankAccount(@RequestBody BankAccount bankAccount) {
        BusinessResponse businessResponse = new BusinessResponse();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(bankAccountService.createBankAccount(bankAccount));
        return businessResponse;
    }

    @PutMapping("/{id}")
    public BusinessResponse updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccountDetails) {
        BusinessResponse businessResponse = new BusinessResponse();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(bankAccountService.updateBankAccount(id, bankAccountDetails));
        return businessResponse;

    }

    @DeleteMapping("/{id}")
    public BusinessResponse deleteBankAccount(@PathVariable Long id) {
        BusinessResponse businessResponse = new BusinessResponse();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        bankAccountService.deleteBankAccount(id);
        return businessResponse;

    }
}