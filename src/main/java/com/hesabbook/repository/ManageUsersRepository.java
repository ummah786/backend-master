package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.account.ManageUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageUsersRepository extends JpaRepository<ManageUsers, Integer> {
    @Query(value = "select * from manage_users where primary_user_id=:id", nativeQuery = true)
    List<ManageUsers> findByPrimaryUserId(@Param("id") String id);

    @Query(value = "select * from manage_users where secondary_user_id=:id", nativeQuery = true)
    List<ManageUsers> getMangeUserValue(@Param("id") String id);

    @Query(value = "select * from manage_users where secondary_user_id IN (:id)", nativeQuery = true)
    List<ManageUsers> getMangeUserValueBasedOnSecondary(@Param("id") List<String> id);
}
