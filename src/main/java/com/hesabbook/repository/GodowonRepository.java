package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.inventory.Godowon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GodowonRepository extends JpaRepository<Godowon, Integer> {
    @Query(value = "select * from godowon where primary_user_id=:id", nativeQuery = true)
    List<Godowon> findByPrimaryUserId(@Param("id") String id);
}
