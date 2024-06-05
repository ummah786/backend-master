package com.hesabbook.service.bank;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.bank.BankAccount;
import com.hesabbook.repository.BankAccountRepository;

@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getBankAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount updateBankAccount(Long id, BankAccount bankAccountDetails) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse( null);
        bankAccount.setAccountHolder(bankAccountDetails.getAccountHolder());
        bankAccount.setAccountName(bankAccountDetails.getAccountName());
        bankAccount.setAccountNumber(bankAccountDetails.getAccountNumber());
        bankAccount.setIfscCode(bankAccountDetails.getIfscCode());
        bankAccount.setBankBranch(bankAccountDetails.getBankBranch());
        bankAccount.setBalance(bankAccountDetails.getBalance());
        bankAccount.setTransactions(bankAccountDetails.getTransactions());
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse( null);
        bankAccountRepository.delete(bankAccount);
    }
}