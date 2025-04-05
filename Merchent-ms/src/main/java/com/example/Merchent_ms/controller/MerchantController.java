package com.example.Merchent_ms.controller;


import com.example.Merchent_ms.model.Merchant;
import com.example.Merchent_ms.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class MerchantController {


    @Autowired
    private MerchantService merchantService;


    @GetMapping("/get-merchants")
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }

    @PostMapping("create-merchant")
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        return ResponseEntity.ok(merchantService.createMerchant(merchant));
    }

}
