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


    public BusinessResponse saveBulk(List<LinkedHashMap<String, String>> linkedHashMap, String primaryUserId, String secondaryUserId) {
        BusinessResponse businessResponse = new BusinessResponse();
        List<Inventory> Inventory = linkedHashMap.stream()
                .map(part->mapToInventory(part,primaryUserId,secondaryUserId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        List<Inventory> InventoryList = inventoryRepository.saveAllAndFlush(Inventory);
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(InventoryList);
        return businessResponse;
    }


    public Inventory mapToInventory(LinkedHashMap<String, String> linkedHashMap, String primaryUserId, String secondaryUserId) {
        Inventory inventory = new Inventory();
        inventory.setItem(linkedHashMap.get("col0"));
        inventory.setItemCode(linkedHashMap.get("col1"));
        inventory.setItemDescription(linkedHashMap.get("col3"));
        inventory.setBarCodeValue(linkedHashMap.get("col3"));
        inventory.setMrp(linkedHashMap.get("col4"));
        inventory.setSalePrice(linkedHashMap.get("col5"));
        inventory.setSalePriceTax(linkedHashMap.get("col6"));
        inventory.setPurchasePrice(linkedHashMap.get("col7"));
        inventory.setPurchasePriceTax(linkedHashMap.get("col8"));
        inventory.setRackNo(linkedHashMap.get("col9"));
        inventory.setCategory(linkedHashMap.get("col10"));
        inventory.setCompanyName(linkedHashMap.get("col11"));
        inventory.setGst(linkedHashMap.get("col12"));
        inventory.setSupplier(linkedHashMap.get("col13"));
        inventory.setWarehouse(linkedHashMap.get("col14"));
        inventory.setHsn(linkedHashMap.get("col15"));
        inventory.setBatchNo(linkedHashMap.get("col16"));
        inventory.setMfgDate(linkedHashMap.get("col17"));
        inventory.setExpireDate(linkedHashMap.get("col18"));
        inventory.setSalt(linkedHashMap.get("col19"));
        inventory.setPackageItems(linkedHashMap.get("col20"));
        inventory.setLowStock(linkedHashMap.get("col21"));
        inventory.setLowStockCheckBox(linkedHashMap.get("col22"));
        inventory.setTotalStock(linkedHashMap.get("col23"));
        inventory.setUnitNo(linkedHashMap.get("col24"));
        inventory.setChallanNo(linkedHashMap.get("col25"));
        boolean flag = areAllFieldsNull(inventory);
        inventory.setPrimary_user_id(primaryUserId);
        inventory.setSecondary_user_id(secondaryUserId);

        if (!flag) {
            return inventory;
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
