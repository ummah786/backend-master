package com.hesabbook.service;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.inventory.Inventory;
import com.hesabbook.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Inventory save(Inventory accountDetails) {
        return inventoryRepository.save(accountDetails);
    }

    public Inventory update(Inventory entity) {
        return inventoryRepository.save(entity);
    }

    public void delete(Inventory entity) {
        inventoryRepository.delete(entity);
    }

    public void delete(Integer id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory find(Integer id) {
        Optional<Inventory> AccountDetailsOptional = inventoryRepository.findById(id);
        return AccountDetailsOptional.orElse(null);
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public List<Inventory> getPrimaryUserId(String id) {
        return inventoryRepository.findByPrimaryUserId(id);
    }
}
