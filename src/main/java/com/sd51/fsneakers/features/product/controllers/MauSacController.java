package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd51.fsneakers.features.product.entity.MauSac;
import com.sd51.fsneakers.features.product.services.MauSacService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/mau-sac")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MauSacController {

    MauSacService mauSacService;

    @GetMapping({ "", "/" })
    public List<MauSac> getAllMauSac() {
        return ResponseEntity.ok(mauSacService.getAllMauSac()).getBody();
    }

    @PostMapping("/add")
    public MauSac createMauSac(@RequestBody MauSac mauSac) {
        return ResponseEntity.ok(mauSacService.createMauSac(mauSac)).getBody();
    }

    @PutMapping("/update/{ma}")
    public MauSac updateMauSac(@RequestBody MauSac mauSacUpdate, @PathVariable String ma) {
        return ResponseEntity.ok(mauSacService.updateMauSac(ma, mauSacUpdate)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public MauSac deleteMauSac(@PathVariable String ma) {
        return ResponseEntity.ok(mauSacService.deleteMauSac(ma)).getBody();
    }

}
