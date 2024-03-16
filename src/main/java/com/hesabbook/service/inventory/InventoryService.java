package com.hesabbook.service.inventory;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.hesabbook.entity.inventory.Inventory;
import com.hesabbook.repository.InventoryRepository;
import com.hesabbook.utils.BusinessResponse;

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


    public BusinessResponse saveBulk(List<LinkedHashMap<String, String>> linkedHashMap) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Inventory> Inventory = linkedHashMap.stream()
                .map(this::mapToInventory)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Inventory> InventoryList = inventoryRepository.saveAllAndFlush(Inventory);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(InventoryList);
        return businessResponse;
    }


    public Inventory mapToInventory(LinkedHashMap<String, String> linkedHashMap) {
        Inventory Inventory = new Inventory();
        /*Inventory.setPName(linkedHashMap.get("col0"));
        Inventory.setMobileNumber(linkedHashMap.get("col1"));
        Inventory.setEmail(linkedHashMap.get("col2"));
        Inventory.setBillingAddress(linkedHashMap.get("col3"));
        Inventory.setShippingAddress(linkedHashMap.get("col4"));
        Inventory.setCompany(linkedHashMap.get("col5"));
        Inventory.setGstNumber(linkedHashMap.get("col6"));
        Inventory.setPartyCategory(linkedHashMap.get("col7"));
        Inventory.setCreditLimit(linkedHashMap.get("col8"));
        Inventory.setCreditPeriod(linkedHashMap.get("col9"));
        Inventory.setCreditPeriodType(linkedHashMap.get("col10"));
        Inventory.setOpeningBalance(linkedHashMap.get("col11"));
        Inventory.setOpeningBalanceType(linkedHashMap.get("col12"));*/
        boolean flag = areAllFieldsNull(Inventory);
        if (!flag) {
            return Inventory;
        } else {
            return null;
        }
    }

    public boolean areAllFieldsNull(Inventory obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
