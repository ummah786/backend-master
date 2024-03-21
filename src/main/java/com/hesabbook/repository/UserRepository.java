package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.account.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String email);
    @Query(value = "select * from user where email=:email", nativeQuery = true)
    List<User> findByEmails(String email);

    User findByMobileNumber(String mobileNumber);

    @Query(value = "select * from user where primary_user_id=:id", nativeQuery = true)
    List<User> findByPrimaryUserId(@Param("id") String id);
}
