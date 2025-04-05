package com.example.Merchent_ms.service;


import com.example.Merchent_ms.model.Merchant;
import com.example.Merchent_ms.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants(){
        return merchantRepository.findAll();
    }

    public Merchant createMerchant(Merchant merchant){
        return merchantRepository.save(merchant);
    }
}
