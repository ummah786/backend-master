package com.hesabbook.service;

import java.util.List;

import com.hesabbook.entity.account.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserService extends GenericService<User> {

    boolean authenticate(String email, String password);

    User findByEmail(String email);

    List<User> findByEmails(String email);

    User findByMobileNumber(String mobileNumber);
    List<User> findByPrimaryUserId(@Param("id") String id);

}
