package com.hesabbook.repository;

import com.hesabbook.entity.account.ManageUsers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageUsersRepository extends JpaRepository<ManageUsers, Integer> {
}
