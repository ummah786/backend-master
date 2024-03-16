package com.hesabbook.service.inventory;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.inventory.Godowon;
import com.hesabbook.repository.GodowonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GodowonService {
    @Autowired
    private GodowonRepository godowonRepository;

    public Godowon save(Godowon accountDetails) {
        return godowonRepository.save(accountDetails);
    }

    public Godowon update(Godowon entity) {
        return godowonRepository.save(entity);
    }

    public void delete(Godowon entity) {
        godowonRepository.delete(entity);
    }

    public void delete(Integer id) {
        godowonRepository.deleteById(id);
    }

    public Godowon find(Integer id) {
        Optional<Godowon> AccountDetailsOptional = godowonRepository.findById(id);
        return AccountDetailsOptional.orElse(null);
    }

    public List<Godowon> findAll() {
        return godowonRepository.findAll();
    }

    public List<Godowon> getPrimaryUserId(String id) {
        return godowonRepository.findByPrimaryUserId(id);
    }
}
