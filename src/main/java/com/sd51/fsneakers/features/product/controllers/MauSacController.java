package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import com.sd51.fsneakers.features.product.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    public Page<MauSac> getAllMauSacPage(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(mauSacService.getAllMauSacPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<MauSac> searchMauSac(@RequestParam (required = false) String keyword,
                                     @RequestParam (required = false) Integer trangThai,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return  mauSacService.searchMauSac(keyword, trangThai, pageable);
    }

}
