package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.inventory.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query(value = "select * from inventory where primary_user_id=:id", nativeQuery = true)
    List<Inventory> findByPrimaryUserId(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Inventory e SET e.totalStock = e.totalStock +:totalStock WHERE e.id = :id  or e.item=:item")
    int updateAddQuantity(@Param("totalStock") String totalStock, @Param("item") String item, @Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Inventory e SET e.totalStock = e.totalStock - :totalStock WHERE e.id = :id  or e.item=:item")
    int updateSubtractQuantity(@Param("totalStock") String totalStock, @Param("item") String item, @Param("id") Integer id);

}