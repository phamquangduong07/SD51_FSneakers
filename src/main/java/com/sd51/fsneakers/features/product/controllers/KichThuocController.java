package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.entity.KichThuoc;
import com.sd51.fsneakers.features.product.services.KichThuocService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/kich-thuoc")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KichThuocController {

    KichThuocService kichThuocService;

    @GetMapping({ "", "/" })
    public List<KichThuoc> getAllKichThuoc() {
        return ResponseEntity.ok(kichThuocService.getAllKichThuoc()).getBody();
    }

    @GetMapping("/page")
    public Page<KichThuoc> getAllKichThuocPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(kichThuocService.getAllKichThuocPage(pageable)).getBody();
    }

    @GetMapping("/search")
    public Page<KichThuoc> searchKichThuoc(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return kichThuocService.searchKichThuoc(keyword, trangThai, pageable);
    }

    @PostMapping("/add")
    public KichThuoc createKichThuoc(@RequestBody KichThuoc kichThuoc) {
        return ResponseEntity.ok(kichThuocService.createKichThuoc(kichThuoc)).getBody();
    }

    @PutMapping("/update/{ma}")
    public KichThuoc updateKichThuoc(@PathVariable String ma,
            @RequestBody KichThuoc kichThuocUpdate) {
        return ResponseEntity.ok(kichThuocService.updateKichThuoc(ma, kichThuocUpdate)).getBody();
    }

    @DeleteMapping("/delete/{ma}")
    public KichThuoc deleteKichThuoc(@PathVariable String ma) {
        return ResponseEntity.ok(kichThuocService.deleteKichThuoc(ma)).getBody();
    }

}
