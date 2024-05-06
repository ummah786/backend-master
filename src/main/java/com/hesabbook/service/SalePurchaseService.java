package com.hesabbook.service;

import java.util.List;

import com.hesabbook.entity.salepurchase.SalePurchase;
import com.hesabbook.repository.SalePurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalePurchaseService {
    @Autowired
    private SalePurchaseRepository salePurchaseRepository;

    public SalePurchase save(SalePurchase salePurchase) {
        return salePurchaseRepository.save(salePurchase);
    }

    public SalePurchase update(SalePurchase entity) {
        return salePurchaseRepository.save(entity);
    }

    public void delete(SalePurchase entity) {
        salePurchaseRepository.delete(entity);
    }

    public void delete(Integer id) {
        salePurchaseRepository.deleteById(id);
    }

    public List<SalePurchase> findByPrimaryUserId(String id) {
        return salePurchaseRepository.findByPrimaryUserId(id);
    }

}
