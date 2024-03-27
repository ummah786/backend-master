package com.hesabbook.service.inventory;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.inventory.Godowon;
import com.hesabbook.repository.GodownRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GodowonService {
    @Autowired
    private GodownRepository godownRepository;

    public Godowon save(Godowon accountDetails) {
        return godownRepository.save(accountDetails);
    }

    public Godowon update(Godowon entity) {
        return godownRepository.save(entity);
    }

    public void delete(Godowon entity) {
        godownRepository.delete(entity);
    }

    public void delete(Integer id) {
        godownRepository.deleteById(id);
    }

    public Godowon find(Integer id) {
        Optional<Godowon> AccountDetailsOptional = godownRepository.findById(id);
        return AccountDetailsOptional.orElse(null);
    }

    public List<Godowon> findAll() {
        return godownRepository.findAll();
    }

    public List<Godowon> getPrimaryUserId(String id) {
        return godownRepository.findByPrimaryUserId(id);
    }


}
