package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.ProductKeyValues;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductKeyValuesRepository extends JpaRepository<ProductKeyValues, Integer> {
    @Query(value = "SELECT value FROM product_category WHERE kes='rack' and primary_user_id=:id", nativeQuery = true)
    List<String> findByRackKey(@Param("id") String id);

    @Query(value = "SELECT value FROM product_category WHERE kes='category' and primary_user_id=:id", nativeQuery = true)
    List<String> findByKeyCategory(@Param("id") String id);

    @Query(value = "SELECT value FROM product_category WHERE kes='company' and primary_user_id=:id", nativeQuery = true)
    List<String> findByKeyCompany(@Param("id") String id);

    @Query(value = "SELECT value FROM product_category WHERE kes='warehouse' and primary_user_id=:id", nativeQuery = true)
    List<String> findByKeyWareHouse(@Param("id") String id);

    @Query(value = "SELECT value FROM product_category WHERE kes='businessName' and primary_user_id=:id", nativeQuery = true)
    List<String> findByBusinessName(@Param("id") String id);

    @Query(value = "select * from product_category where primary_user_id=:id", nativeQuery = true)
    List<ProductKeyValues> findByPrimaryUserId(@Param("id") String id);
}
