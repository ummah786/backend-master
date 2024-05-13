package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.salepurchase.SalePurchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SalePurchaseRepository extends JpaRepository<SalePurchase, Integer> {

    @Query(value = "select * from sale_purchase where primary_user_id=:id", nativeQuery = true)
    List<SalePurchase> findByPrimaryUserId(@Param("id") String id);

    /*@Transactional
    @Modifying
    @Query("UPDATE SalePurchase e SET e.balanceAmount = :balAmountUp , e.paidAmount=:paidAmountUp  WHERE e.id = :id")
    int updateAmount(@Param("balAmountUp") String balAmountUp, @Param("paidAmountUp") String paidAmountUp, @Param("id") Integer id);
*/

}


