package com.hesabbook.service.expense;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.expense.Expense;
import com.hesabbook.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public Expense save(Expense accountDetails) {
        return expenseRepository.save(accountDetails);
    }

    public Expense update(Expense entity) {
        return expenseRepository.save(entity);
    }

    public void delete(Expense entity) {
        expenseRepository.delete(entity);
    }

    public void delete(Integer id) {
        expenseRepository.deleteById(id);
    }

    public Expense find(Integer id) {
        Optional<Expense> AccountDetailsOptional = expenseRepository.findById(id);
        return AccountDetailsOptional.orElse(null);
    }

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public List<Expense> findByPrimaryUserId(String id) {
        return expenseRepository.findByPrimaryUserId(id);
    }
}
