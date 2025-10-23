package com.sd51.fsneakers.features.product.controllers;

import java.util.List;
import java.util.UUID;

import com.sd51.fsneakers.features.product.dto.request.SanPhamRequest;
import com.sd51.fsneakers.features.product.dto.response.SanPhamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sd51.fsneakers.features.product.services.SanPhamService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/v1/san-pham")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamController {

    SanPhamService sanPhamService;

    @GetMapping({ "", "/" })
    public ResponseEntity<List<SanPhamResponse>> getAllSanPham() {
        return ResponseEntity.ok(sanPhamService.getAllSanPham());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SanPhamResponse>> getAllSanPhamPage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sanPhamService.getAllSanPhamPage(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SanPhamResponse>> searchSanPham(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer trangThai,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(sanPhamService.searchSanPham(keyword, trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<SanPhamResponse> createSanPham(@RequestBody SanPhamRequest sanPham) {
        return ResponseEntity.ok(sanPhamService.createSanPham(sanPham));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SanPhamResponse> updateSanPham(@RequestBody SanPhamRequest sanPham, @PathVariable UUID id) {
        return ResponseEntity.ok(sanPhamService.updateSanPham(id, sanPham));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SanPhamResponse> deleteSanPham(@PathVariable UUID id) {
        return ResponseEntity.ok(sanPhamService.deleteSanPham(id));
    }

}
