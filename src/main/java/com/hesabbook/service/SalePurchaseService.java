package com.hesabbook.service;

import java.util.List;

import com.hesabbook.entity.salepurchase.SalePurchase;
import com.hesabbook.repository.SalePurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hesabbook.utils.CommonUtils.FULL_PAID;
import static com.hesabbook.utils.CommonUtils.PARTIAL_PAID;
import static com.hesabbook.utils.CommonUtils.UN_PAID;

@Service
public class SalePurchaseService {
    @Autowired
    private SalePurchaseRepository salePurchaseRepository;

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


    public List<SalePurchase> findALl() {
        return salePurchaseRepository.findAll();
    }

    public List<SalePurchase> findByPrimaryUserId(String id) {
        return salePurchaseRepository.findByPrimaryUserId(id);
    }

}
