package com.hesabbook.repository;

import java.util.List;

import com.hesabbook.entity.party.CACandidate;
import com.hesabbook.entity.party.Partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CACandidateRepository extends JpaRepository<CACandidate, Integer> {

    @Query(value = "select * from partner where primary_user_id=:id", nativeQuery = true)
    List<CACandidate> findByPrimaryUserId(@Param("id") String id);
}
