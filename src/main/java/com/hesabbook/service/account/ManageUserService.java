package com.hesabbook.service.account;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.hesabbook.entity.account.ManageUsers;
import com.hesabbook.entity.account.User;
import com.hesabbook.repository.ManageUsersRepository;
import com.hesabbook.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageUserService {
    @Autowired
    ManageUsersRepository manageUsersRepository;
    @Autowired
    UserService userService;

    public ManageUsers save(ManageUsers ManageUsers) {
        Random random = new Random();
        if(ManageUsers.getName()!=null){
            int randomNumber = random.nextInt(90000) + 10000;
            ManageUsers.setSecondary_user_id(ManageUsers.getName().trim()+randomNumber);
        }else if(ManageUsers.getMobileNumber()!=null){
            int randomNumber = random.nextInt(900) + 100;
            ManageUsers.setSecondary_user_id(ManageUsers.getMobileNumber().trim()+randomNumber);
        }else if(ManageUsers.getEmailAddress()!=null){
            int randomNumber = random.nextInt(900) + 100;
            ManageUsers.setSecondary_user_id(ManageUsers.getMobileNumber().trim()+randomNumber);
        }else {
            int randomData = random.nextInt(90000000) + 10000000;
            ManageUsers.setSecondary_user_id(String.valueOf(randomData));
        }
        saveIntoUser(ManageUsers);
        return manageUsersRepository.save(ManageUsers);
    }

    public ManageUsers update(ManageUsers entity) {
        return manageUsersRepository.save(entity);
    }

    private void saveIntoUser(ManageUsers manageUsers) {

        User user = new User();
        user.setFirstName(manageUsers.getName());
        user.setEmail(manageUsers.getEmailAddress());
        user.setMobileNumber(manageUsers.getMobileNumber());
        user.setPrimary_user_id(manageUsers.getPrimary_user_id());
        user.setRole(manageUsers.getRole());
        user.setSecondary_user_id(manageUsers.getSecondary_user_id());
        user.setTempPassword(manageUsers.getTempPassword());
        userService.save(user);
    }

    public void delete(ManageUsers entity) {
        manageUsersRepository.delete(entity);
    }

    public void delete(Integer id) {
        manageUsersRepository.deleteById(id);
    }

    public ManageUsers find(Integer id) {
        Optional<ManageUsers> ManageUsersOptional = manageUsersRepository.findById(id);
        return ManageUsersOptional.orElse(null);
    }

    public List<ManageUsers> findAll() {
        return manageUsersRepository.findAll();
    }

    public List<ManageUsers> findByPrimaryUserId(String id) {
        return manageUsersRepository.findByPrimaryUserId(id);
    }
}
