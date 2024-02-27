package com.hesabbook.service;

import com.hesabbook.entity.account.User;

public interface UserService extends GenericService<User> {

    boolean authenticate(String email, String password);

    User findByEmail(String email);

}
