package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.expense.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query(value = "select * from expense where primary_user_id=:id", nativeQuery = true)
    List<Expense> findByPrimaryUserId(@Param("id") String id);
}