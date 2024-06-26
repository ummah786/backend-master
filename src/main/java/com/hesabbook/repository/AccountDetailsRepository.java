package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.account.AccountDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Integer> {
    @Query(value = "select * from account_details  where primary_user_id=:id", nativeQuery = true)
    List<AccountDetails> findByPrimaryUserId(@Param("id") String id);
}
