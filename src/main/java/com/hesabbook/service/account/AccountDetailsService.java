package com.hesabbook.service.account;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.ProductKeyValuePair;
import com.hesabbook.entity.account.AccountDetails;
import com.hesabbook.repository.AccountDetailsRepository;
import com.hesabbook.service.ProductKeyValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService {
    @Autowired
    AccountDetailsRepository accountDetailsRepository;
    @Autowired
    private ProductKeyValueService productKeyValueService;

    public AccountDetails save(AccountDetails accountDetails) {
        ProductKeyValuePair productkeyValuePair =new ProductKeyValuePair();
        productkeyValuePair.setKes("businessName");
        productkeyValuePair.setValue(accountDetails.getBusinessName());
        productkeyValuePair.setPrimary_user_id(accountDetails.getPrimary_user_id());
        productkeyValuePair.setSecondary_user_id(accountDetails.getSecondary_user_id());
        productKeyValueService.save(productkeyValuePair);
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
