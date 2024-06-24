package com.hesabbook.service.inventory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.hesabbook.entity.ProductKeyValuePair;
import com.hesabbook.entity.inventory.Inventory;
import com.hesabbook.repository.InventoryRepository;
import com.hesabbook.service.ProductKeyValueService;
import com.hesabbook.utils.BusinessResponse;
import com.hesabbook.utils.CommonUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductKeyValueService productKeyValueService;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ProductKeyValueService productKeyValueService) {
        this.inventoryRepository = inventoryRepository;
        this.productKeyValueService = productKeyValueService;
    }

    public Inventory save(Inventory accountDetails) {
        if (Objects.nonNull(accountDetails)) {
            String gstTax = accountDetails.getGst();
            String salePriceTaxType = accountDetails.getSalePriceTax();
            if (StringUtils.isNotBlank(salePriceTaxType) && salePriceTaxType.equalsIgnoreCase(CommonUtils.WITH_OUT_TAX)) {
                if (StringUtils.isNotBlank(gstTax)) {
                    int gstNumber = Integer.parseInt(gstTax);
                    String salePriceValue = accountDetails.getSalePrice();
                    if (StringUtils.isNotBlank(salePriceValue)) {
                        double salePriceValueDouble = Double.parseDouble(salePriceValue);
                        double actualSalePrice = salePriceValueDouble + (salePriceValueDouble * gstNumber / 100);
                        accountDetails.setActualSalePrice(String.valueOf(actualSalePrice));
                    }
                }
            } else if (StringUtils.isNotBlank(salePriceTaxType) && salePriceTaxType.equalsIgnoreCase(CommonUtils.WITH_TAX)) {
                if (StringUtils.isNotBlank(gstTax)) {
                    int gstNumber = Integer.parseInt(gstTax);
                    String salePriceValue = accountDetails.getSalePrice();
                    if (StringUtils.isNotBlank(salePriceValue)) {
                        double salePriceValueDouble = Double.parseDouble(salePriceValue);
                        double actualSalePrice = salePriceValueDouble / (1 + gstNumber / 100.0);
                        accountDetails.setSalePrice(String.valueOf(actualSalePrice));
                        accountDetails.setActualSalePrice(salePriceValue);
                    }
                }
            }
        }
        if (Objects.nonNull(accountDetails)) {
            String gstTax = accountDetails.getGst();
            String purchasePriceTaxType = accountDetails.getPurchasePriceTax();
            if (StringUtils.isNotBlank(purchasePriceTaxType) && purchasePriceTaxType.equalsIgnoreCase(CommonUtils.WITH_OUT_TAX)) {
                if (StringUtils.isNotBlank(gstTax)) {
                    int gstNumber = Integer.parseInt(gstTax);
                    String purchasePriceValue = accountDetails.getPurchasePrice();
                    if (StringUtils.isNotBlank(purchasePriceValue)) {
                        double purchasePriceValueDouble = Double.parseDouble(purchasePriceValue);
                        double actualPurchasePrice = purchasePriceValueDouble + (purchasePriceValueDouble * gstNumber / 100);
                        accountDetails.setActualPurchasePrice(String.valueOf(actualPurchasePrice));
                    }
                }
            } else if (StringUtils.isNotBlank(purchasePriceTaxType) && purchasePriceTaxType.equalsIgnoreCase(CommonUtils.WITH_TAX)) {
                if (StringUtils.isNotBlank(gstTax)) {
                    int gstNumber = Integer.parseInt(gstTax);
                    String purchasePriceValue = accountDetails.getPurchasePrice();
                    if (StringUtils.isNotBlank(purchasePriceValue)) {
                        double purchasePriceValueDouble = Double.parseDouble(purchasePriceValue);
                        double actualPurchasePrice = purchasePriceValueDouble / (1 + gstNumber / 100.0);
                        accountDetails.setPurchasePrice(String.valueOf(actualPurchasePrice));
                        accountDetails.setActualPurchasePrice(purchasePriceValue);
                    }
                }
            }
        }
        List<ProductKeyValuePair> productKeyValuePairList = List.of(
                extracted("company", accountDetails.getCompanyName(), accountDetails),
                extracted("category", accountDetails.getCategory(), accountDetails)
        );
        productKeyValueService.saveAll(productKeyValuePairList);

        return inventoryRepository.save(accountDetails);
    }

    private ProductKeyValuePair extracted(String company, String entity, Inventory entity1) {
        ProductKeyValuePair productkeyValuePair = new ProductKeyValuePair();
        productkeyValuePair.setKes(company);
        productkeyValuePair.setValue(entity);
        productkeyValuePair.setPrimary_user_id(entity1.getPrimary_user_id());
        productkeyValuePair.setSecondary_user_id(entity1.getSecondary_user_id());
        return productkeyValuePair;
        //  productKeyValueService.save(productkeyValuePair);
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
        return inventoryRepository.findById(id).orElse(null);
    }

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public List<Inventory> getPrimaryUserId(String id) {
        return inventoryRepository.findByPrimaryUserId(id);
    }

    public BusinessResponse saveBulk(List<LinkedHashMap<String, String>> linkedHashMap, String primaryUserId, String secondaryUserId) {
        List<Inventory> inventories = linkedHashMap.stream()
                .map(part -> mapToInventory(part, primaryUserId, secondaryUserId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<Inventory> savedInventories = inventoryRepository.saveAllAndFlush(inventories);

        BusinessResponse businessResponse = new BusinessResponse();
        businessResponse.setCode(200);
        businessResponse.setStatus("SUCCESS");
        businessResponse.setResponse(savedInventories);

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
        inventory.setPrimary_user_id(primaryUserId);
        inventory.setSecondary_user_id(secondaryUserId);

        if (areAllFieldsNull(inventory)) {
            return null;
        }
        return inventory;
    }

    private boolean areAllFieldsNull(Inventory obj) {
        return Arrays.stream(obj.getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .allMatch(field -> {
                    try {
                        return Objects.isNull(field.get(obj));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
