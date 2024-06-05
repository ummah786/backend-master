package com.hesabbook.controller;

import java.util.List;

import com.hesabbook.entity.bank.BankAccount;
import com.hesabbook.service.bank.BankAccountService;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.getBankAccountById(id).orElseThrow(() -> new ResourceNotFoundException("BankAccount", "id", null));
        return ResponseEntity.ok(bankAccount);
    }

    @PostMapping
    public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
        return bankAccountService.createBankAccount(bankAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccountDetails) {
        BankAccount updatedBankAccount = bankAccountService.updateBankAccount(id, bankAccountDetails);
        return ResponseEntity.ok(updatedBankAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.noContent().build();
    }
}