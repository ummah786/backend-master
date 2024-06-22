package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.account.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String email);

    @Query(value = "select * from user where email=:email", nativeQuery = true)
    List<User> findByEmails(String email);

    List<User> findByMobileNumber(String mobileNumber);

    @Query(value = "select * from user where primary_user_id=:id", nativeQuery = true)
    List<User> findByPrimaryUserId(@Param("id") String id);


    @Query(value = "select * from user where mobile_number=:id", nativeQuery = true)
    User findByMobileNumbers(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "update user set first_time_login='N' ,business_id=:businessId,primary_with_business=:primary where id=:id", nativeQuery = true)
    void updateFirstTimeLogin(@Param("id") String id, String businessId, String primary);

    @Modifying
    @Transactional
    @Query(value = "update user set is_login='Y',last_login_date=:dateValue,token=:token where id=:id", nativeQuery = true)
    void updateLoginId(@Param("id") Integer id, @Param("dateValue") String dateValue, String token);
}
