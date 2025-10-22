package com.sd51.fsneakers.features.product.controllers;

import java.util.List;

import com.sd51.fsneakers.features.product.dto.request.KichThuocRequest;
import com.sd51.fsneakers.features.product.dto.response.KichThuocResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<KichThuocResponse>> getAllKichThuoc() {
        return ResponseEntity.ok(kichThuocService.getAllKichThuoc());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<KichThuocResponse>> getAllKichThuocPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(kichThuocService.getAllKichThuocPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<KichThuocResponse>> searchKichThuoc(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(kichThuocService.searchKichThuoc(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<KichThuocResponse> createKichThuoc(@RequestBody KichThuocRequest kichThuoc) {
        return ResponseEntity.ok(kichThuocService.createKichThuoc(kichThuoc));
    }

    @PutMapping("/update/{ma}")
    public ResponseEntity<KichThuocResponse> updateKichThuoc(@PathVariable String ma,
            @RequestBody KichThuocRequest kichThuocUpdate) {
        return ResponseEntity.ok(kichThuocService.updateKichThuoc(ma, kichThuocUpdate));
    }

    @DeleteMapping("/delete/{ma}")
    public ResponseEntity<Void> deleteKichThuoc(@PathVariable String ma) {
        kichThuocService.deleteKichThuoc(ma);
        return ResponseEntity.ok().build();
    }

}
