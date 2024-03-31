package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.ProductKeyValuePair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductKeyValuesRepository extends JpaRepository<ProductKeyValuePair, Integer> {
    @Query(value = "SELECT value FROM product_key_value_pair WHERE kes='rack' and primary_user_id=:id", nativeQuery = true)
    List<String> findByRackKey(@Param("id") String id);

    @Query(value = "SELECT value FROM product_key_value_pair WHERE kes='category' and primary_user_id=:id", nativeQuery = true)
    List<String> findByKeyCategory(@Param("id") String id);

    @Query(value = "SELECT value FROM product_key_value_pair WHERE kes='company' and primary_user_id=:id", nativeQuery = true)
    List<String> findByKeyCompany(@Param("id") String id);

    @Query(value = "SELECT value FROM product_key_value_pair WHERE kes='warehouse' and primary_user_id=:id", nativeQuery = true)
    List<String> findByKeyWareHouse(@Param("id") String id);

    @Query(value = "SELECT value FROM product_key_value_pair WHERE kes='businessName' ", nativeQuery = true)
    List<String> findByBusinessName();

     @Query(value = "SELECT value FROM product_key_value_pair WHERE kes='businessName' and primary_user_id=:id", nativeQuery = true)
      List<String> findByBusinessName(@Param("id") String id);

    @Query(value = "select * from product_key_value_pair where primary_user_id=:id", nativeQuery = true)
    List<ProductKeyValuePair> findByPrimaryUserId(@Param("id") String id);
}
