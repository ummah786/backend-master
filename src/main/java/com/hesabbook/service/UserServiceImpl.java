package com.hesabbook.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

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
                    //    user = this.findByMobileNumber(username);
                    flag = getaBoolean(user, password, flag);
                    if (flag == null) {
                    } else {
                        return user;
                    }
                } else {
                    if (password.equals(user.getPassword())) {
                        LocalDateTime dateTime = LocalDateTime.now();

                        // Format LocalDateTime as String
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedDateTime = dateTime.format(formatter);
                        int tokenLength = 150;
                        Random random = new Random();
                        StringBuilder token = new StringBuilder(tokenLength);
                        for (int i = 0; i < tokenLength; i++) {
                            int randomNumber = random.nextInt(10);
                            token.append(randomNumber);
                        }
                        String generatedToken = token.toString();
                        user.setToken(generatedToken);
                        user.setLastLoginDate(formattedDateTime);
                        User finalUser = user;
                        CompletableFuture.runAsync(() ->
                                handleUpdateLoginId(finalUser)
                        );
                        return user;
                    } else {
                        return user;
                    }
                }
            }
        } else if (users != null && users.size() == 0) {
            List<User> userss = this.findByMobileNumber(username);
            for (User user : userss) {
                flag = getaBoolean(user, password, flag);
                if (flag == null) {
                } else {
                    LocalDateTime dateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = dateTime.format(formatter);
                    int tokenLength = 150;
                    Random random = new Random();
                    StringBuilder token = new StringBuilder(tokenLength);
                    for (int i = 0; i < tokenLength; i++) {
                        int randomNumber = random.nextInt(10);
                        token.append(randomNumber);
                    }
                    String generatedToken = token.toString();
                    user.setToken(generatedToken);
                    user.setLastLoginDate(formattedDateTime);
                    User finalUser = user;
                    CompletableFuture.runAsync(() ->
                            handleUpdateLoginId(finalUser)
                    );
                    return user;
                }
            }

        }
        return usersss;
    }

    private void handleUpdateLoginId(User user) {
        userRepository.updateLoginId(user.getId(), user.getLastLoginDate());
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
    public List<User> findByMobileNumber(String mobileNumber) {
        return userRepository.findByMobileNumber(mobileNumber);
    }
    public User findByMobileNumbers(String mobileNumber) {
        return userRepository.findByMobileNumbers(mobileNumber);
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
