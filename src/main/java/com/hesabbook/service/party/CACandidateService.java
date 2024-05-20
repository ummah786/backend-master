package com.hesabbook.service.party;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.party.CACandidate;
import com.hesabbook.repository.CACandidateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CACandidateService {
    @Autowired
    private CACandidateRepository caCandidateRepository;

    public CACandidate save(CACandidate accountDetails) {
        return caCandidateRepository.save(accountDetails);
    }

    public CACandidate update(CACandidate entity) {
        return caCandidateRepository.save(entity);
    }

    public void delete(CACandidate entity) {
        caCandidateRepository.delete(entity);
    }

    public void delete(Integer id) {
        caCandidateRepository.deleteById(id);
    }

    public CACandidate find(Integer id) {
        Optional<CACandidate> AccountDetailsOptional = caCandidateRepository.findById(id);
        return AccountDetailsOptional.orElse(null);
    }

    public List<CACandidate> findAll() {
        return caCandidateRepository.findAll();
    }

    public List<CACandidate> findByPrimaryUserId(String id) {
        return caCandidateRepository.findByPrimaryUserId(id);
    }

}
