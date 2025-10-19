package com.sd51.fsneakers.features.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sd51.fsneakers.features.product.entity.DeGiay;
import com.sd51.fsneakers.features.product.services.DeGiayService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("v1/api/de-giay")
public class DeGiayController {

    @Autowired
    private DeGiayService deGiayService;

    @GetMapping({ "", "/" })
    public List<DeGiay> getAllDeGiay() {
        return ResponseEntity.ok(deGiayService.getAllDeGiay()).getBody();
    }

    @PostMapping("/add")
    public DeGiay addDeGiay(@RequestBody DeGiay deGiay) {
        return ResponseEntity.ok(deGiayService.createDeGiay(deGiay)).getBody();
    }

    @PutMapping("/update/{ma}")
    public DeGiay updateDeGiay(@PathVariable("ma") String ma, @RequestBody DeGiay deGiay) {
        return ResponseEntity.ok(deGiayService.updateDeGiay(ma, deGiay)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public DeGiay deleteDeGiay(@PathVariable("ma") String ma) {
        return ResponseEntity.ok(deGiayService.deleteDeGiay(ma)).getBody();
    }

}
