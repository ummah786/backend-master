package com.hesabbook.service.party;

import java.util.List;
import java.util.Optional;

import com.hesabbook.entity.account.User;
import com.hesabbook.entity.party.Partner;
import com.hesabbook.repository.PartnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
    @Autowired
    private PartnerRepository partnerRepository;
    public Partner save(Partner entity) {
        return partnerRepository.save(entity);
    }
    public Partner update(Partner entity) {
        return partnerRepository.save(entity);
    }
    public void delete(Partner entity) {
        partnerRepository.delete(entity);
    }
    public void delete(Integer id) {
        partnerRepository.deleteById(id);
    }
    public Partner find(Integer id) {
        Optional<Partner> PartnerOptional = partnerRepository.findById(id);
        return PartnerOptional.orElse(null);
    }
    public List<Partner> findAll() {
        return partnerRepository.findAll();
    }
/*    public Partner findByEmail(String email) {
        return partnerRepository.findByEmail(email);
    }

   
    public List<Partner> findByEmails(String email) {
        return partnerRepository.findByEmails(email);
    }

   
    public Partner findByMobileNumber(String mobileNumber) {
        return partnerRepository.findByMobileNumber(mobileNumber);
    }*/
    public List<Partner> findByPrimaryPartnerId(String id) {
        return partnerRepository.findByPrimaryUserId(id);
    }
    public void deleteInBatch(List<Partner> Partners) {
        partnerRepository.deleteInBatch(Partners);
    }
}
