package com.hesabbook.service;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hesabbook.entity.inventory.Inventory;
import com.hesabbook.entity.salepurchase.SalePurchase;
import com.hesabbook.repository.InventoryRepository;
import com.hesabbook.repository.SalePurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hesabbook.utils.CommonUtils.CREDIT_NOTE;
import static com.hesabbook.utils.CommonUtils.FULL_PAID;
import static com.hesabbook.utils.CommonUtils.PARTIAL_PAID;
import static com.hesabbook.utils.CommonUtils.SALES_INVOICE;
import static com.hesabbook.utils.CommonUtils.SALES_RETURN;
import static com.hesabbook.utils.CommonUtils.UN_PAID;

@Service
public class SalePurchaseService {
    @Autowired
    private SalePurchaseRepository salePurchaseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public SalePurchase save(SalePurchase salePurchase) {
        Double totalAmount = 0.0;
        Double amountReceived = 0.0;
        Double balanceAmount = 0.0;
        String totalAmountString = salePurchase.getTotalAmount();
        String amountReceivedString = salePurchase.getAmountReceived();
        String balanceAmountString = salePurchase.getBalanceAmount();
        if (totalAmountString != null && !totalAmountString.isEmpty()) {
            totalAmount = Double.parseDouble(totalAmountString);
        }
        if (amountReceivedString != null && !amountReceivedString.isEmpty()) {
            amountReceived = Double.parseDouble(amountReceivedString);
        }
        if (balanceAmountString != null && !balanceAmountString.isEmpty()) {
            balanceAmount = Double.parseDouble(balanceAmountString);
        }
        if (totalAmount > 0.0) {
            Double leftAmount = totalAmount - amountReceived;
            if (leftAmount == 0.0) {
                salePurchase.setStatus(FULL_PAID);
            } else if (leftAmount.equals(totalAmount)) {
                salePurchase.setStatus(UN_PAID);
            } else if (leftAmount > 0.0) {
                salePurchase.setStatus(PARTIAL_PAID);
            }
        }
        switch (salePurchase.getBillType()) {
            case SALES_INVOICE -> {
                updateItemQuantity(salePurchase, "Reduce");
            }
            case SALES_RETURN, CREDIT_NOTE -> {
                updateItemQuantity(salePurchase, "Increment");
            }
        }


        return salePurchaseRepository.save(salePurchase);
    }

    private void updateItemQuantity(SalePurchase salePurchase, String redInc) {
        if (salePurchase == null || salePurchase.getItems() == null || redInc == null) {
            System.out.println("Invalid input: salePurchase, items, or redInc is null");
            return;
        }
        Type inventoryListType = new TypeToken<List<Inventory>>() {
        }.getType();
        List<Inventory> inventoryList = new Gson().fromJson(salePurchase.getItems(), inventoryListType);
        if (inventoryList == null || inventoryList.isEmpty()) {
            System.out.println("Invalid input: inventory list is null or empty");
            return;
        }
        switch (redInc) {
            case "Reduce" -> {
                inventoryList.forEach(inventory -> {
                    if (inventory != null && inventory.getQuantity() != null) {
                        try {
                            inventoryRepository.updateSubtractQuantity(String.valueOf(inventory.getQuantity()), inventory.getItem(), inventory.getId());
                        } catch (Exception exception) {
                            System.out.println(exception);
                        }
                    }
                });
            }
            case "Increment" -> {
                inventoryList.forEach(inventory -> {
                    if (inventory != null && inventory.getQuantity() != null) {
                        try {
                            inventoryRepository.updateAddQuantity(String.valueOf(inventory.getQuantity()), inventory.getItem(), inventory.getId());
                        } catch (Exception exception) {
                            System.out.println(exception);
                        }
                    }
                });
            }
            default -> {
                System.out.println("Invalid redInc value");
            }
        }
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


    public List<SalePurchase> findALl() {
        return salePurchaseRepository.findAll();
    }

    public List<SalePurchase> findByPrimaryUserId(String id) {
        return salePurchaseRepository.findByPrimaryUserId(id);
    }

}
