package com.hesabbook.service;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.ProductKeyValues;
import com.hesabbook.repository.ProductKeyValuesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductKeyValueService {
    @Autowired
    private ProductKeyValuesRepository productKeyValuesRepository;

    public ProductKeyValues save(ProductKeyValues accountDetails) {
        return productKeyValuesRepository.save(accountDetails);
    }

    public void delete(ProductKeyValues entity) {
        productKeyValuesRepository.delete(entity);
    }

    public void delete(Integer id) {
        productKeyValuesRepository.deleteById(id);
    }

    public ProductKeyValues find(Integer id) {
        Optional<ProductKeyValues> AccountDetailsOptional = productKeyValuesRepository.findById(id);
        return AccountDetailsOptional.orElse(null);
    }

    public List<ProductKeyValues> findAll() {
        return productKeyValuesRepository.findAll();
    }

    public List<ProductKeyValues> getPrimaryUserId(String id) {
        return productKeyValuesRepository.findByPrimaryUserId(id);
    }
}
