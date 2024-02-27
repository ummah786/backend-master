package com.hesabbook.service.account;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.account.ManageUsers;
import com.hesabbook.repository.ManageUsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageUserService {
    @Autowired
    ManageUsersRepository manageUsersRepository;

    public ManageUsers save(ManageUsers ManageUsers) {
        return manageUsersRepository.save(ManageUsers);
    }

    public ManageUsers update(ManageUsers entity) {
        return manageUsersRepository.save(entity);
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
}
