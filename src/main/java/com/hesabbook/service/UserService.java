package com.hesabbook.service;

import java.util.List;

import com.hesabbook.entity.account.User;

import org.springframework.data.repository.query.Param;

public interface UserService extends GenericService<User> {

    User authenticate(String email, String password);

    List<User> findByEmail(String email);

    List<User> findByEmails(String email);

    List<User> findByMobileNumber(String mobileNumber);

    List<User> findByPrimaryUserId(@Param("id") String id);

}
