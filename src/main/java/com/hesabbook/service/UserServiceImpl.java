package com.hesabbook.service;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.account.User;
import com.hesabbook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User find(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User authenticate(String username, String password) {
        List<User> users = userRepository.findByEmail(username);
        User usersss = new User();

        Boolean flag = false;
        if (users != null && users.size() > 0) {
            for (User user : users) {
                if (user == null) {
                    user = this.findByMobileNumber(username);
                    flag = getaBoolean(user, password, flag);
                    if (flag == null) {
                        return user;
                    } else {
                        return user;
                    }
                } else {
                    if (password.equals(user.getPassword())) {
                        return user;
                    } else {
                        return user;
                    }
                }
            }
        } else if (users != null && users.size() == 0) {
            User userss = this.findByMobileNumber(username);
            flag = getaBoolean(usersss, password, flag);
            if (flag == null) {
                return userss;
            } else {
                return userss;
            }
        }
        return usersss;
    }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Boolean getaBoolean(User user, String password, Boolean flag) {
        if (user == null) {
            return null;
        } else {
            if (password.equals(user.getTempPassword())) {
                flag = true;
            } else {
                return null;
            }
        }
        return flag;
    }

    @Override
    public List<User> findByEmails(String email) {
        return userRepository.findByEmails(email);
    }

    @Override
    public User findByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }

    @Override
    public List<User> findByPrimaryUserId(String id) {
        return userRepository.findByPrimaryUserId(id);
    }

    @Override
    public void deleteInBatch(List<User> users) {
        userRepository.deleteInBatch(users);
    }

}
