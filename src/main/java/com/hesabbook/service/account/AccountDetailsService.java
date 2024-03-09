package com.hesabbook.service.account;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.account.AccountDetails;
import com.hesabbook.entity.account.ManageUsers;
import com.hesabbook.repository.AccountDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService {
    @Autowired
    AccountDetailsRepository accountDetailsRepository;

    public AccountDetails save(AccountDetails accountDetails) {
        return accountDetailsRepository.save(accountDetails);
    }

    public AccountDetails update(AccountDetails entity) {
        return accountDetailsRepository.save(entity);
    }

    public void delete(AccountDetails entity) {
        accountDetailsRepository.delete(entity);
    }

    public void delete(Integer id) {
        accountDetailsRepository.deleteById(id);
    }

    public AccountDetails find(Integer id) {
        Optional<AccountDetails> AccountDetailsOptional = accountDetailsRepository.findById(id);
        return AccountDetailsOptional.orElse(null);
    }

    public List<AccountDetails> findAll() {
        return accountDetailsRepository.findAll();
    }

    public List<AccountDetails> findByPrimaryUserId(String id) {
        return accountDetailsRepository.findByPrimaryUserId(id);
    }

}
