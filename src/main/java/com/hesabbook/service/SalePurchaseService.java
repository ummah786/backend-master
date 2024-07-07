package com.hesabbook.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hesabbook.entity.bank.Transaction;
import com.hesabbook.entity.inventory.Inventory;
import com.hesabbook.entity.inventory.PartyWiseReport;
import com.hesabbook.entity.inventory.StockDetails;
import com.hesabbook.entity.party.ItemWiseReport;
import com.hesabbook.entity.party.Partner;
import com.hesabbook.entity.party.Statements;
import com.hesabbook.entity.party.Transactions;
import com.hesabbook.entity.salepurchase.SalePurchase;
import com.hesabbook.repository.InventoryRepository;
import com.hesabbook.repository.PartnerRepository;
import com.hesabbook.repository.SalePurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hesabbook.utils.CommonUtils.CREDIT_NOTE;
import static com.hesabbook.utils.CommonUtils.DEBIT_NOTE;
import static com.hesabbook.utils.CommonUtils.DELIVERY_CHALLAN;
import static com.hesabbook.utils.CommonUtils.FULL_PAID;
import static com.hesabbook.utils.CommonUtils.OPEN;
import static com.hesabbook.utils.CommonUtils.PARTIAL_PAID;
import static com.hesabbook.utils.CommonUtils.PROFORMA_INVOICE;
import static com.hesabbook.utils.CommonUtils.PURCHASE_INVOICE;
import static com.hesabbook.utils.CommonUtils.PURCHASE_ORDER;
import static com.hesabbook.utils.CommonUtils.PURCHASE_RETURN;
import static com.hesabbook.utils.CommonUtils.QUOTATION;
import static com.hesabbook.utils.CommonUtils.SALES_INVOICE;
import static com.hesabbook.utils.CommonUtils.SALES_RETURN;
import static com.hesabbook.utils.CommonUtils.UN_PAID;

@Service
public class SalePurchaseService {

    @Autowired
    private PartnerRepository partnerRepository;
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
            case SALES_INVOICE, DEBIT_NOTE, PURCHASE_RETURN -> updateItemQuantity(salePurchase, "Reduce");
            case SALES_RETURN, CREDIT_NOTE, PURCHASE_INVOICE -> updateItemQuantity(salePurchase, "Increment");
            case PURCHASE_ORDER, DELIVERY_CHALLAN, QUOTATION, PROFORMA_INVOICE -> salePurchase.setStatus(OPEN);
        }
       SalePurchase salePurchaseReturn= salePurchaseRepository.save(salePurchase);

        Statements statements =new Statements();
        Transactions transaction=new Transactions();
        ItemWiseReport itemWiseReport=new ItemWiseReport();
        PartyWiseReport partyWiseReport=new PartyWiseReport();
        StockDetails stockDetails=new StockDetails();
        switch (salePurchase.getBillType()) {
            case SALES_INVOICE -> {

                statements.setSpNo(salePurchaseReturn.getId());
                statements.setBillType(SALES_INVOICE);
                statements.setDebit(salePurchase.getTotalAmount());
                statements.setCredit(salePurchase.getBalanceAmount());
                statements.setReceivedBalance(salePurchase.getAmountReceived());
                statements.setBalanceAmount(salePurchase.getBalanceAmount());
                statements.setTotalAmount(salePurchase.getTotalAmount());

                transaction.setTransactionType(SALES_INVOICE);
                transaction.setSpNo(salePurchaseReturn.getId());
                transaction.setAmount(salePurchase.getTotalAmount());
                transaction.setStatus(salePurchase.getStatus());


                itemWiseReport.setSpNo(salePurchaseReturn.getId());


                Type inventoryListType = new TypeToken<List<Inventory>>() {
                }.getType();
                List<Inventory> inventoryList = new Gson().fromJson(salePurchase.getItems(), inventoryListType);
                if (inventoryList == null || inventoryList.isEmpty()) {
                    System.out.println("Invalid input: inventory list is null or empty");
                }

                inventoryList.forEach(x->{
                    itemWiseReport.setItemNo(x.getId());
                    itemWiseReport.setItemName(x.getItem());
                    BigDecimal salePrice = new BigDecimal(x.getSalePrice());
                    BigDecimal saleAmount = salePrice.multiply(new BigDecimal(x.getQuantity()));
                    itemWiseReport.setSaleAmount(String.valueOf(saleAmount)); // Set sale amount
                    itemWiseReport.setSaleQuantity(String.valueOf(x.getQuantity()));
                    itemWiseReport.setSaleIncDec("-");
                   /* itemWiseReport.setPurchaseAmount();
                    itemWiseReport.setPurchaseQuantity();
                    itemWiseReport.setPurchaseIncDec();*/
                });



            }
            case DEBIT_NOTE -> {

            }
            case PURCHASE_RETURN -> {

            }
        }

      Optional<Partner> partner= partnerRepository.findById(Integer.valueOf(salePurchase.getPartyId()));
        if(partner.isPresent()){
            Partner actualPartner=partner.get();
            actualPartner.setTransactionsList(Arrays.asList(transaction));
            actualPartner.setStatementsList(Arrays.asList(statements));
            actualPartner.setItemWiseReportList(Arrays.asList(itemWiseReport));
            partnerRepository.save(actualPartner);
        }

        return salePurchaseReturn;




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
